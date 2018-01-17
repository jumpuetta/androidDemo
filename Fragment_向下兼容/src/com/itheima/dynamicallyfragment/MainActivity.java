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
		// ֡Ƭ�η���Ĺ�����
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		if (height > width) {// ��ֱ��Ļ
			fragmentTransaction.replace(android.R.id.content, new Fragment1());
		} else {// ˮƽ����Ļ
			fragmentTransaction.replace(android.R.id.content, new Fragment2());
		}
		fragmentTransaction.commit();
	}

}
