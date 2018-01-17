package com.android.face;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bitmap faceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
		//该API很弱
		 FaceDetector detector = new FaceDetector(faceBitmap.getWidth(),
				    faceBitmap.getHeight(), 1); // 创建识别器
		            Face mFaces[] = new Face[1];
				    int  mNumFaces = detector.findFaces(faceBitmap, mFaces);    
				    // 识别
				    if (mNumFaces > 0) {
				         for (int i = 0; i < mNumFaces; i++) {
				         System.out.println("找到了一张脸"); 
				         mFaces[i].eyesDistance();
				         PointF point = new PointF();
				         mFaces[i].getMidPoint(point);
				         System.out.println("练的中心坐标："+point.x+""+point.y);
				     // 调用函数对人脸画面进行处理
				   }
				}
	}

}
