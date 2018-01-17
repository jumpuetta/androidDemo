package com.android.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		image = (ImageView)findViewById(R.id.image);
	}

	public void play(View view){
		image.setVisibility(View.INVISIBLE);
	}
	
	public void stop(View view){
		image.setVisibility(View.VISIBLE);
	}

}
