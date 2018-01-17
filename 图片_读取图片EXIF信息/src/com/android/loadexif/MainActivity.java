package com.android.loadexif;

import java.io.IOException;

import android.app.Activity;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tv_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_info = (TextView)findViewById(R.id.tv_info);
		
	}
	

	public void loadInfo(View view){
		//真时一个类  非接口
		try {
			ExifInterface exif = new ExifInterface("/sdcard/IMG_1769.JPG");
			String date = exif.getAttribute(ExifInterface.TAG_DATETIME);
			String mode = exif.getAttribute(ExifInterface.TAG_MODEL);
			String info = "拍摄日期："+date+"\n"+"相机类型："+mode;
			tv_info.setText(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
