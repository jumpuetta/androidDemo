package com.android.imageview;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText imagepath ;
    private ImageView imageview;
    private Handler handler;
    //代表帮我改UI
    private final static int CHANGE_UI = 1;
    private final static int ERROR_UI = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imagepath = (EditText)findViewById(R.id.imagepath);
		imageview = (ImageView)findViewById(R.id.imageview);
		//1.在主线程里面创建Handler消息处理器
		handler = new Handler(){
			//当looper轮训到消息时系统调用handleMessage方法对消息进行处理
             public void handleMessage(Message msg) {
             switch (msg.what) {
				case CHANGE_UI:
					 imageview.setImageBitmap((Bitmap)msg.obj);
					break;
				case ERROR_UI:
					Toast.makeText(MainActivity.this,msg.obj.toString(), Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
            	
			}
		};
	}

	 public void loadImage(View view) {
		 final String path = imagepath.getText().toString().trim();
         if(TextUtils.isEmpty(path)){
        	Toast.makeText(this,"图片路径为空", Toast.LENGTH_SHORT).show();
        }else{
        	new Thread(new Runnable() {
				public void run() {

		        	//连接服务器  采用GET请求方式获取图片
		        	try {
						URL url = new URL(path);
						//根据URL发送http请求
						HttpURLConnection connection = (HttpURLConnection)url.openConnection();
						//设置请求方式
						connection.setRequestMethod("GET");
						//设置连接超时时间
						connection.setConnectTimeout(10000);
						//设置读取超时时间
						connection.setReadTimeout(10000);
						/**设置请求属性
						 * Accept,Accept-Laguage,User_Agent,UA-CPU,
						 * Accept-Encoding,Host等等
						 */
						connection.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0");
						//获取服务器返回的响应码
						connection.connect();
						int code = connection.getResponseCode();
						if(code == 200){
							//获取网络输入流
							InputStream is = connection.getInputStream();
							//将网络输入流里面的数据转换为位图
							Bitmap bitmap = BitmapFactory.decodeStream(is);
							/**
				        	 * 谁创建的UI谁才能修改它
				        	 * 主线程又为UI线程
				        	 * 只有主线程才能对其生成的UI进行更改，这样避免了线程的并发问题
				        	 * 此时子线程应该告诉主线程一个消息：帮我更新UI  内容：bitmap
				        	 * */
							is.close();
							Message msg = new Message();
							msg.what = CHANGE_UI;
							msg.obj = bitmap;
							//向消息队列里面添加消息
							handler.sendMessage(msg);
						}else{
							Message msg = new Message();
							msg.what = ERROR_UI;
							msg.obj = "图片已经被移除";
							handler.sendMessage(msg);
						}
		        	} catch (Exception e) {
						Message msg = new Message();
						msg.what = ERROR_UI;
						msg.obj = "获取图片失败";
						handler.sendMessage(msg);
					}
				}
			}).start();
        	
        }
	}

}
