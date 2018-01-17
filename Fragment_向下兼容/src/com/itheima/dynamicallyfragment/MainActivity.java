package com.itheima.dynamicallyfragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WindowManager wm = getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		// 帧片段服务的管理者
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		if (height > width) {// 竖直屏幕
			fragmentTransaction.replace(android.R.id.content, new Fragment1());
		} else {// 水平的屏幕
			fragmentTransaction.replace(android.R.id.content, new Fragment2());
		}
		fragmentTransaction.commit();
	}

}
