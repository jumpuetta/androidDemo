package com.android.testcamera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
   
	public void click1(View view){
		
		Intent intent = new Intent();
		intent.setAction("android.media.action.IMAGE_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		File file = new File(Environment.getExternalStorageDirectory()+""+System.currentTimeMillis()+".jpg");
		Uri uri = Uri.fromFile(file);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
	    startActivity(intent);
	}
	
     public void click2(View view){
 		Intent intent = new Intent();
 		intent.setAction("android.media.action.VIDEO_CAPTURE");
 		intent.addCategory("android.intent.category.DEFAULT");
 		File file = new File(Environment.getExternalStorageDirectory()+""+System.currentTimeMillis()+".mp4");
 		Uri uri = Uri.fromFile(file);
 	    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
 	    startActivityForResult(intent,0);
	}
     
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       Toast.makeText(this, "Â¼ÏñÍê±Ï", Toast.LENGTH_SHORT).show();
       super.onActivityResult(requestCode, resultCode, data);
    }
}
