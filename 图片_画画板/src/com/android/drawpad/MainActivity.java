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
    //画布类
    private Canvas canvas;
    //画笔类
    private Paint paint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView)findViewById(R.id.iv_image);
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		//创建一个可以被修改的空白的Bitmap
		baseBitmap = Bitmap.createBitmap(320, 370, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(baseBitmap);
		canvas.drawColor(Color.WHITE);
		//获取用户手指在屏幕上移动的轨迹
		imageView.setOnTouchListener(new OnTouchListener() {
			//存放手指开始的坐标
			int start_x;
			int start_y;
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				//第一次触摸
				case MotionEvent.ACTION_DOWN:
					start_x = (int)event.getX();
					start_y = (int)event.getY();
					break;
                //手指在屏幕上移动
                case MotionEvent.ACTION_MOVE:
                	//不断获取获取新的坐标  getX,getY获取手指在控件上的坐标  getRawX,getRawY获取手指在屏幕上的坐标
					int new_x = (int)event.getX();
					int new_y = (int)event.getY();
					//划出一条直线
					canvas.drawLine(start_x, start_y, new_x, new_y, paint);
					//不断更新初始位置
					start_x = (int)event.getX();
					start_y = (int)event.getY();
					imageView.setImageBitmap(baseBitmap);
					break;
			    //手指移开时
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
			Toast.makeText(this, "图片保存成功", Toast.LENGTH_SHORT).show();
			//发送广播媒体被更新了   模拟SD卡被挂载了，通知系统更新媒体
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "图片保存失败", Toast.LENGTH_SHORT).show();
		}
		
		
	}

}
