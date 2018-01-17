package com.android.taskstack;

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

	public void click(View view ){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
    public void click1(View view ){
    	Intent intent = new Intent(this,Activity01.class);
		startActivity(intent);
	}
    public void click2(View view ){
    	Intent intent = new Intent(this,Activity02.class);
		startActivity(intent);
    }

}
