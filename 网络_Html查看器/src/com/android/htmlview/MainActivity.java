package com.android.htmlview;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
  private TextView htmlview;
  private EditText htmlpath;
  private Handler handler;
  private final static int ERROR_UI = 0 ;
  private final static int CHANGE_UI = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		htmlview = (TextView)findViewById(R.id.htmlview);
		htmlpath = (EditText)findViewById(R.id.htmlpath);
		handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGE_UI:
				htmlview.setText(msg.obj.toString());
				break;
			case ERROR_UI:
				Toast.makeText(MainActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		  }
		};
	}
    public void loadHtml(View view){
    	final String path = htmlpath.getText().toString().trim();
    	if(TextUtils.isEmpty(path)){
    		Toast.makeText(this,"路径不能为空",Toast.LENGTH_SHORT).show();
    	}else{
    		new Thread(new Runnable() {
				public void run() {
					try {
						URL url = new URL(path);
						HttpURLConnection conn = (HttpURLConnection)url.openConnection();
						conn.setConnectTimeout(2000);
						conn.setReadTimeout(10000);
						conn.setRequestMethod("GET");
						conn.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0");
						int code = conn.getResponseCode();
						if(code == 200){
							InputStream is = conn.getInputStream();
							byte buffer[] = new byte[1024];
							int len;
							StringBuilder sb = new StringBuilder("");
							while ((len=is.read(buffer))!=-1){
								//实际需求中先对返回的页面解析出其字符编码，然后按页面字符编码进行
								sb.append(new String(buffer,0,len,"gb2312"));
							}
							is.close();
							Message msg = new Message();
							msg.what = CHANGE_UI;
							msg.obj=sb;
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						Message msg = new Message();
						msg.what = ERROR_UI;
						msg.obj="连接服务器失败";
						handler.sendMessage(msg);
					}
				}
			}).start();
    		
    	}
    }
}
