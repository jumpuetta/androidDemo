package com.android.changesize;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView own;
    private ImageView change1;
    private ImageView change2;
    private ImageView change3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		own = (ImageView)findViewById(R.id.own);
		change1 = (ImageView)findViewById(R.id.change1);
		change2 = (ImageView)findViewById(R.id.change2);
		change3 = (ImageView)findViewById(R.id.change3);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap1.getWidth()*2,bitmap1.getHeight()*2,bitmap1.getConfig());
		
		Canvas canvas1 = new Canvas(bitmap2);
		Paint paint1 = new Paint();
		paint1.setColor(Color.BLACK);
		Matrix matrix1 = new Matrix();
//		matrix1.setValues(new float[]{
//				2,0,0,
//				0,2,0,
//				0,0,2
//		});
		//等价于
		matrix1.setScale(2, 2);
		canvas1.drawBitmap(bitmap1, matrix1, paint1);
		
        Bitmap bitmap3 = Bitmap.createBitmap(bitmap1.getWidth()*2,bitmap1.getHeight()*2,bitmap1.getConfig());
		
		Canvas canvas2 = new Canvas(bitmap3);
		Paint paint2 = new Paint();
		paint2.setColor(Color.BLACK);
		Matrix matrix2 = new Matrix();
		//设置旋转角度
		matrix2.setRotate(30,bitmap1.getWidth()/2,bitmap1.getHeight()/2);
		//设置消除锯齿
		//matrix2.setScale(2, 2);  旋转和放大缩小只能同时进行一个
		paint2.setAntiAlias(true);
		
		canvas2.drawBitmap(bitmap1, matrix2, paint2);
		
		 Bitmap bitmap4 = Bitmap.createBitmap(bitmap1.getWidth()*2,bitmap1.getHeight()*2,bitmap1.getConfig());
			
		Canvas canvas3 = new Canvas(bitmap4);
		Paint paint3 = new Paint();
		paint3.setColor(Color.BLACK);
		//设置图片的合成模式
		//paint3.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.MULTIPLY));
		Matrix matrix3 = new Matrix();
		//水平垂直移动十个像素
		matrix3.setTranslate(10, 10);
		matrix3.postTranslate(10, 10);
		canvas3.drawBitmap(bitmap1, matrix3, paint3);
		//canvas3.drawBitmap(bitmap3, matrix3, paint3);
		
		
		own.setImageBitmap(bitmap1);
		change1.setImageBitmap(bitmap2);
		change2.setImageBitmap(bitmap3);
		change3.setImageBitmap(bitmap4);
	}


}
