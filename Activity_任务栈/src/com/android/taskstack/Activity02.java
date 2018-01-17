package com.android.taskstack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Activity02 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity02);
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
