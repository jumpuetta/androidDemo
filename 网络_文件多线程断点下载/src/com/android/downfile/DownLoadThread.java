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
	//该对象用于加锁
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
			 * 设置写文件的开始位置
    		 * 检查是否有下载长度的文件
    		 * threadfile存放真时的startIndex
    		 */
    		//-----------------------应替换成对数据库的操作------------------------------------
    		if(threadfile.exists()&&threadfile.length()>0){
    	    	FileInputStream fis = new FileInputStream(threadfile);
    		    byte[] bf = new byte[1024];
    		    int length = fis.read(bf);
    		    fis.close();
    		    //得出真实的下载位置
    		    current = current + (Integer.parseInt(new String(bf,0,length)) - startIndex);
    		    startIndex=Integer.parseInt(new String(bf,0,length));
    		}
    		
    		//重点：向服务器请求部分文件  并指定下载的起始位置
    		conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
    		conn.setConnectTimeout(5000);
    		int code = conn.getResponseCode();
    		//206代表请求部分资源成公
    		if(code == 206){
    			InputStream is = conn.getInputStream();
        		file.seek(startIndex);
    			byte buffer[] = new byte[1024];
    			int len ;
    			//断点续传文件已下载的数据量
    			int total = 0;
    			while ((len = is.read(buffer)) != -1){
    				file.write(buffer, 0, len);
    				/**
    	    		 * 将记录到文件中，下次下载时读取值让后加1作为初始值 
    	    		 * 生成多个文件防止并发
    	    		 */
    				RandomAccessFile seekfile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+"/"+threadid+".txt","rwd");
    				//记录已经下载到的位置
    				
    				total = total +len;
    				seekfile.write((startIndex + total +"").getBytes());
    				seekfile.close();
    				//更改进度条UI   注：该UI可以在子线程里面更改
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
  				msg.obj = "线程"+threadid+"下载完成";
  				handler.sendMessage(msg);
    		}
    	}catch(Exception e){
    		Message msg = Message.obtain();
			msg.what = 1;
			msg.obj = "线程"+threadid+"下载异常";
			handler.sendMessage(msg);
    	}finally{
    		running--;
    		if(running == 0){
    			//-----------------------应替换成对数据库的操作------------------------------------
    			for(int threadid=1;threadid<=threadcount;threadid++){
    		    	File deletefile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+threadid+".txt");
    		    	deletefile.delete();
    			}
    			Message msg = new Message();
    			msg.what = 1;
    			msg.obj = "文件下载完成";
    			handler.sendMessage(msg);
    		}
    	}
    }
}
