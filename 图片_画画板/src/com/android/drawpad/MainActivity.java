package com.android.drawpad;


import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ImageView imageView ;
    private Bitmap  baseBitmap;
    //������
    private Canvas canvas;
    //������
    private Paint paint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView)findViewById(R.id.iv_image);
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		//����һ�����Ա��޸ĵĿհ׵�Bitmap
		baseBitmap = Bitmap.createBitmap(320, 370, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(baseBitmap);
		canvas.drawColor(Color.WHITE);
		//��ȡ�û���ָ����Ļ���ƶ��Ĺ켣
		imageView.setOnTouchListener(new OnTouchListener() {
			//�����ָ��ʼ������
			int start_x;
			int start_y;
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				//��һ�δ���
				case MotionEvent.ACTION_DOWN:
					start_x = (int)event.getX();
					start_y = (int)event.getY();
					break;
                //��ָ����Ļ���ƶ�
                case MotionEvent.ACTION_MOVE:
                	//���ϻ�ȡ��ȡ�µ�����  getX,getY��ȡ��ָ�ڿؼ��ϵ�����  getRawX,getRawY��ȡ��ָ����Ļ�ϵ�����
					int new_x = (int)event.getX();
					int new_y = (int)event.getY();
					//����һ��ֱ��
					canvas.drawLine(start_x, start_y, new_x, new_y, paint);
					//���ϸ��³�ʼλ��
					start_x = (int)event.getX();
					start_y = (int)event.getY();
					imageView.setImageBitmap(baseBitmap);
					break;
			    //��ָ�ƿ�ʱ
                case MotionEvent.ACTION_UP:
					
					break;
				default:
					break;
				}
				return true;
			}
		});
	}
      
	public void saveImage(View view){
		try {
			File file = new File("/sdcard/"+System.currentTimeMillis()+".jpg");
			FileOutputStream fos = new FileOutputStream(file);;
			
			baseBitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.close();
			Toast.makeText(this, "ͼƬ����ɹ�", Toast.LENGTH_SHORT).show();
			//���͹㲥ý�屻������   ģ��SD���������ˣ�֪ͨϵͳ����ý��
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "ͼƬ����ʧ��", Toast.LENGTH_SHORT).show();
		}
		
		
	}

}
