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
					tv_progress.setText("下载进度："+msg.obj.toString()+"/"+pb_progress.getMax());
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
       	   Toast.makeText(this,"文件路径不能为空",Toast.LENGTH_SHORT).show();
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
      				//假设开启三个线程，获取每个线程下载数据的大小
      				int length = conn.getContentLength();
      				//设置进度条最大值
      				pb.setMax(length);
      				//在客服端本地创建一个和下载文件大小一样的临时文件
      				String fileName = neturl.substring(neturl.lastIndexOf("/")+1);
      				
      				/**
      				 * 判断是否有同名文件
      				 * */
      				File old = new File(Environment.getExternalStorageDirectory().getPath()+"/"+fileName);
      				RandomAccessFile newfile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+"/"+fileName,"rwd");
      				//设置本地文件大小
      				newfile.setLength(length);
      				newfile.close();
      				
      				int blockSize = length / threadcount;
      				//设置运行线程数目为threadcount
      				DownLoadThread.running = threadcount;
      				for(int threadid = 1;threadid<=threadcount;threadid++ ){
      					//线程下载的开始位置
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
      				msg.obj = "连接服务器有误";
      				handler.sendMessage(msg);
      			}
      		   
      	   }
         }.start();
    }
}
