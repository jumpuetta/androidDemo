package com.android.loadimage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageview;
    private int windowWidth;
    private int windowHeight;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageview = (ImageView)findViewById(R.id.iv_image);
		WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);

//		Point point = new Point();
//		manager.getDefaultDisplay().getSize(point);
//		width = point.x;
//		height = point.y;
		
		windowWidth = manager.getDefaultDisplay().getWidth();
		windowHeight = manager.getDefaultDisplay().getHeight();
		
	}
    public void loadImage(View view){
       //Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/IMG_1769.JPG");
       //imageview.setImageBitmap(bitmap);
    	
    	BitmapFactory.Options options = new Options();
    	
    	//��ȥ��������ڴ�ռ�������ͼƬ��ֻ���õ�ͼƬ��ͷ����Ϣ
    	options.inJustDecodeBounds = true;
    	Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/IMG_1769.JPG",options);
    	
    	//��ȡͼƬ����ʵ���
    	int imageWidth = options.outWidth;
    	int imageHeight = options.outHeight;

    	int scaleX = imageWidth / windowWidth;
    	int scaleY = imageHeight / windowHeight;
    	
    	int scale = scaleX >= scaleY ? scaleX : scaleX ;
        if(scale < 1)
        	scale = 1;
    	//��Ľ���ͼƬ  false
    	options.inJustDecodeBounds = false;
    	options.inSampleSize = scale;
    	Bitmap smallbitmap = BitmapFactory.decodeFile("/sdcard/IMG_1769.JPG",options);
    	imageview.setImageBitmap(smallbitmap);
    }
}
