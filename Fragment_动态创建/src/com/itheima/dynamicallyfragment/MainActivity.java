package com.itheima.dynamicallyfragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WindowManager wm = getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		// 帧片段服务的管理者
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		if (height > width) {// 竖直屏幕
			//android.R.id.content代表当前的界面  是系统定义的
			fragmentTransaction.replace(android.R.id.content, new Fragment1());
		} else {// 水平的屏幕
			fragmentTransaction.replace(android.R.id.content, new Fragment2());
		}
		fragmentTransaction.commit();
	}

}
