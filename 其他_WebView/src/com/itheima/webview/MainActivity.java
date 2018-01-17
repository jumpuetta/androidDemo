package com.itheima.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {
	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wv = (WebView) findViewById(R.id.wv);
		WebSettings settings = wv.getSettings();
		settings.setPluginsEnabled(true);
	}

	public void click(View view) {
		wv.loadUrl("/sdcard/index.html");
	}
}
