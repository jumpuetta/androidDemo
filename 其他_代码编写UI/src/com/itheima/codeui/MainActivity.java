package com.itheima.codeui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView sv = new ScrollView(this);
		int width = getWindowManager().getDefaultDisplay().getWidth();
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		for (int i = 1; i <= 10; i++) {
			Button bt = new Button(this);
			bt.setText("ÎÒÊÇ°´Å¥"+i);
			ll.addView(bt, width/2, -2);
		}
		sv.addView(ll);
		setContentView(sv);
	}
}
