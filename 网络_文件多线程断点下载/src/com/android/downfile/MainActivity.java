package com.android.downfile;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
   private EditText et_filepath;
   private ProgressBar pb_progress;
   private TextView tv_progress;
   private Handler handler;
   private static int threadcount = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_filepath = (EditText)findViewById(R.id.ev_filepath);
		pb_progress = (ProgressBar)findViewById(R.id.pb_progress);
		tv_progress = (TextView)findViewById(R.id.tv_progress);
		handler = new Handler(){
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					Toast.makeText(MainActivity.this,msg.obj.toString(), Toast.LENGTH_SHORT).show();
					break;
				case 2:
					tv_progress.setText("���ؽ��ȣ�"+msg.obj.toString()+"/"+pb_progress.getMax());
					break;
				default:
					break;
				}
					
			}
		};
	}
    public void downFile(View view) {
     String path = et_filepath.getText().toString().trim();
     downByThread(path, threadcount, "",handler,pb_progress);
           
	}
    
    public void downByThread(final String neturl,final int threadcount,String localpath,final Handler handler,final ProgressBar pb){
          if(TextUtils.isEmpty(neturl)){
       	   Toast.makeText(this,"�ļ�·������Ϊ��",Toast.LENGTH_SHORT).show();
          }
           
    	 new Thread(){
      	   public void run() {
      		   try {
      				URL url = new URL(neturl);
      				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      				conn.setRequestMethod("GET");
      				conn.setConnectTimeout(5000);
      				int code = conn.getResponseCode();
      				if(code == 200){
      				InputStream is = conn.getInputStream();
      				//���迪�������̣߳���ȡÿ���߳��������ݵĴ�С
      				int length = conn.getContentLength();
      				//���ý��������ֵ
      				pb.setMax(length);
      				//�ڿͷ��˱��ش���һ���������ļ���Сһ������ʱ�ļ�
      				String fileName = neturl.substring(neturl.lastIndexOf("/")+1);
      				
      				/**
      				 * �ж��Ƿ���ͬ���ļ�
      				 * */
      				File old = new File(Environment.getExternalStorageDirectory().getPath()+"/"+fileName);
      				RandomAccessFile newfile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+"/"+fileName,"rwd");
      				//���ñ����ļ���С
      				newfile.setLength(length);
      				newfile.close();
      				
      				int blockSize = length / threadcount;
      				//���������߳���ĿΪthreadcount
      				DownLoadThread.running = threadcount;
      				for(int threadid = 1;threadid<=threadcount;threadid++ ){
      					//�߳����صĿ�ʼλ��
      					int startIndex = (threadid-1) * blockSize;
      					int endIndex = threadid * blockSize - 1;
      					if(threadid == threadcount){
      						endIndex = length ;
      					}
      					RandomAccessFile writefile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+"/"+fileName,"rwd");
      					new DownLoadThread(threadid, startIndex, endIndex, neturl, writefile,threadcount,handler,pb_progress).start();
      				 }
      				}
      			} catch (Exception e) {
      				Message msg = Message.obtain();
      				msg.what = 1;
      				msg.obj = "���ӷ���������";
      				handler.sendMessage(msg);
      			}
      		   
      	   }
         }.start();
    }
}
