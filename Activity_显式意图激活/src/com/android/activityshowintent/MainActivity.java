package com.android.activityshowintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
    public void click(View view){
//	   Intent intent = new Intent();
//	   intent.setClassName(this, "com.android.activityshowintent.OtherScreenActivity");
//     startActivity(intent); 
       Intent intent = new Intent(this, OtherScreenActivity.class);
       startActivity(intent); 
    }
    
    public void clicksysapp(View view){
     //可通过logcat得到cmp=com.android.gallery/com.android.camera.GalleryPicker
       Intent intent = new Intent();
	   intent.setClassName("com.android.gallery", "com.android.camera.GalleryPicker");
       startActivity(intent); 
    }

}
