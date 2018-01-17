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
		// ֡Ƭ�η���Ĺ�����
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		if (height > width) {// ��ֱ��Ļ
			//android.R.id.content����ǰ�Ľ���  ��ϵͳ�����
			fragmentTransaction.replace(android.R.id.content, new Fragment1());
		} else {// ˮƽ����Ļ
			fragmentTransaction.replace(android.R.id.content, new Fragment2());
		}
		fragmentTransaction.commit();
	}

}
