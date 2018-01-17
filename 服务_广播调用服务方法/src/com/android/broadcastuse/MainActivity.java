package com.android.broadcastuse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this,AnService.class);
		startService(intent);
	}

	public void click(View view){
		Intent intent = new Intent();
		intent.setAction("com.android.useservice");
		sendBroadcast(intent);
	}
}
