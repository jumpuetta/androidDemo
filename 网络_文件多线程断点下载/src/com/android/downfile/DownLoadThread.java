package com.android.downfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class DownLoadThread extends Thread{
	private int threadid;
	private int startIndex;
	private int endIndex;
	private String urlpath;
	private RandomAccessFile file;
	public  static int running ;
	private int threadcount;
	private Handler handler;
	private ProgressBar pb;
	private static int  current = 0;
	//�ö������ڼ���
	private static Object obj = new Object();
   public DownLoadThread(int threadid, int startIndex, int endIndex,String urlpath,RandomAccessFile file,int threadcount,Handler handler,ProgressBar pb) {
		super();
		this.threadid = threadid;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.urlpath = urlpath;
		this.file = file;
		this.threadcount = threadcount;
		this.handler = handler;
		this.pb = pb;
	}

	public void run() {
    	File threadfile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+threadid+".txt") ;
    	try {
    		URL url = new URL(urlpath);
    		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    		conn.setRequestMethod("GET");
    		/**
			 * ����д�ļ��Ŀ�ʼλ��
    		 * ����Ƿ������س��ȵ��ļ�
    		 * threadfile�����ʱ��startIndex
    		 */
    		//-----------------------Ӧ�滻�ɶ����ݿ�Ĳ���------------------------------------
    		if(threadfile.exists()&&threadfile.length()>0){
    	    	FileInputStream fis = new FileInputStream(threadfile);
    		    byte[] bf = new byte[1024];
    		    int length = fis.read(bf);
    		    fis.close();
    		    //�ó���ʵ������λ��
    		    current = current + (Integer.parseInt(new String(bf,0,length)) - startIndex);
    		    startIndex=Integer.parseInt(new String(bf,0,length));
    		}
    		
    		//�ص㣺����������󲿷��ļ�  ��ָ�����ص���ʼλ��
    		conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
    		conn.setConnectTimeout(5000);
    		int code = conn.getResponseCode();
    		//206�������󲿷���Դ�ɹ�
    		if(code == 206){
    			InputStream is = conn.getInputStream();
        		file.seek(startIndex);
    			byte buffer[] = new byte[1024];
    			int len ;
    			//�ϵ������ļ������ص�������
    			int total = 0;
    			while ((len = is.read(buffer)) != -1){
    				file.write(buffer, 0, len);
    				/**
    	    		 * ����¼���ļ��У��´�����ʱ��ȡֵ�ú��1��Ϊ��ʼֵ 
    	    		 * ���ɶ���ļ���ֹ����
    	    		 */
    				RandomAccessFile seekfile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+"/"+threadid+".txt","rwd");
    				//��¼�Ѿ����ص���λ��
    				
    				total = total +len;
    				seekfile.write((startIndex + total +"").getBytes());
    				seekfile.close();
    				//���Ľ�����UI   ע����UI���������߳��������
    				synchronized (obj) {
    					current = current + len;
        				pb.setProgress(current);
        				Message msg = Message.obtain();
          				msg.what = 2;
          				msg.obj = current;
          				handler.sendMessage(msg);
					}
    			}
    			is.close();
    			file.close();
    			Message msg = Message.obtain();
  				msg.what = 1;
  				msg.obj = "�߳�"+threadid+"�������";
  				handler.sendMessage(msg);
    		}
    	}catch(Exception e){
    		Message msg = Message.obtain();
			msg.what = 1;
			msg.obj = "�߳�"+threadid+"�����쳣";
			handler.sendMessage(msg);
    	}finally{
    		running--;
    		if(running == 0){
    			//-----------------------Ӧ�滻�ɶ����ݿ�Ĳ���------------------------------------
    			for(int threadid=1;threadid<=threadcount;threadid++){
    		    	File deletefile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+threadid+".txt");
    		    	deletefile.delete();
    			}
    			Message msg = new Message();
    			msg.what = 1;
    			msg.obj = "�ļ��������";
    			handler.sendMessage(msg);
    		}
    	}
    }
}
