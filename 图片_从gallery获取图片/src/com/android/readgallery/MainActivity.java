package com.android.readgallery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
    public void selectImage(View view){
    	//激活系统图库  选择一张图片
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_PICK);
    	intent.setType("image/*");
    	startActivityForResult(intent,0);
    	
//       //Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/IMG_1769.JPG");
//       //imageview.setImageBitmap(bitmap);
//    	
//    	BitmapFactory.Options options = new Options();
//    	
//    	//不去真的申请内存空间来解析图片，只是拿到图片的头部信息
//    	options.inJustDecodeBounds = true;
//    	Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/1.jpg",options);
//    	
//    	//获取图片的真实宽高
//    	int imageWidth = options.outWidth;
//    	int imageHeight = options.outHeight;
//
//    	int scaleX = imageWidth / windowWidth;
//    	int scaleY = imageHeight / windowHeight;
//    	
//    	int scale = scaleX >= scaleY ? scaleX : scaleX ;
//        if(scale < 1)
//        	scale = 1;
//    	//真的解析图片  false
//    	options.inJustDecodeBounds = false;
//    	options.inSampleSize = scale;
//    	Bitmap smallbitmap = BitmapFactory.decodeFile("/sdcard/1.jpg",options);
//    	imageview.setImageBitmap(smallbitmap);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	//获取选择图片的Uri路径
    	Uri uri = data.getData();
    	imageview.setImageURI(uri);
         //获取图片的缩约图  此处不需要
    	//Bitmap bitmap = data.getParcelableExtra("data");
    }
}
