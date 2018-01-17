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
    //������Ҹ�UI
    private final static int CHANGE_UI = 1;
    private final static int ERROR_UI = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imagepath = (EditText)findViewById(R.id.imagepath);
		imageview = (ImageView)findViewById(R.id.imageview);
		//1.�����߳����洴��Handler��Ϣ������
		handler = new Handler(){
			//��looper��ѵ����Ϣʱϵͳ����handleMessage��������Ϣ���д���
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
        	Toast.makeText(this,"ͼƬ·��Ϊ��", Toast.LENGTH_SHORT).show();
        }else{
        	new Thread(new Runnable() {
				public void run() {

		        	//���ӷ�����  ����GET����ʽ��ȡͼƬ
		        	try {
						URL url = new URL(path);
						//����URL����http����
						HttpURLConnection connection = (HttpURLConnection)url.openConnection();
						//��������ʽ
						connection.setRequestMethod("GET");
						//�������ӳ�ʱʱ��
						connection.setConnectTimeout(10000);
						//���ö�ȡ��ʱʱ��
						connection.setReadTimeout(10000);
						/**������������
						 * Accept,Accept-Laguage,User_Agent,UA-CPU,
						 * Accept-Encoding,Host�ȵ�
						 */
						connection.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0");
						//��ȡ���������ص���Ӧ��
						connection.connect();
						int code = connection.getResponseCode();
						if(code == 200){
							//��ȡ����������
							InputStream is = connection.getInputStream();
							//���������������������ת��Ϊλͼ
							Bitmap bitmap = BitmapFactory.decodeStream(is);
							/**
				        	 * ˭������UI˭�����޸���
				        	 * ���߳���ΪUI�߳�
				        	 * ֻ�����̲߳��ܶ������ɵ�UI���и��ģ������������̵߳Ĳ�������
				        	 * ��ʱ���߳�Ӧ�ø������߳�һ����Ϣ�����Ҹ���UI  ���ݣ�bitmap
				        	 * */
							is.close();
							Message msg = new Message();
							msg.what = CHANGE_UI;
							msg.obj = bitmap;
							//����Ϣ�������������Ϣ
							handler.sendMessage(msg);
						}else{
							Message msg = new Message();
							msg.what = ERROR_UI;
							msg.obj = "ͼƬ�Ѿ����Ƴ�";
							handler.sendMessage(msg);
						}
		        	} catch (Exception e) {
						Message msg = new Message();
						msg.what = ERROR_UI;
						msg.obj = "��ȡͼƬʧ��";
						handler.sendMessage(msg);
					}
				}
			}).start();
        	
        }
	}

}
