package com.example.exitapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Activity01 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//每开启一个activity 都把他的引用存起来
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.add(this);
		TextView tv = new TextView(this);
		tv.setText("我是activity01,点击我打开02");
		tv.setTextSize(30);
		tv.setTextColor(Color.RED);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Activity01.this, Activity02.class);
				startActivity(i);

			}
		});

		setContentView(tv);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.item1) {
			MyApplication myapp = (MyApplication) getApplication();
			for(Activity a : myapp.activies){
				a.finish();
			}
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		return super.onOptionsItemSelected(item);
	}
    @Override
    protected void onDestroy() {
    	//没开启一个activity 都把他的引用存起来
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.remove(this);
    	
    	super.onDestroy();
    }
    
}
