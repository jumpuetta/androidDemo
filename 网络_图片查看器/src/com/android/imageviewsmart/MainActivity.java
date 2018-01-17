package com.android.imageviewsmart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {
    private SmartImageView siv ;
    private EditText imagepath; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		siv = (SmartImageView)findViewById(R.id.imageview);
		imagepath = (EditText)findViewById(R.id.imagepath);
	}
	public void loadImage(View view){
		siv.setImageUrl(imagepath.getText().toString().trim(), R.drawable.ic_launcher, R.drawable.ic_launcher);
	}

}
