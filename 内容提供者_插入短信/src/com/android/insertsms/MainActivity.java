package com.android.insertsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			ContentResolver resolver = getContentResolver();
		    Uri uri = Uri.parse("content://sms/");
		    ContentValues values = new ContentValues();
		    values.put("address","95533");
		    values.put("type", 1);
		    values.put("date",System.currentTimeMillis());
		    values.put("body", "尊敬的蒋先生，您尾号为65424的建行卡，收入人民币1000000000，活期余额为2000000");
		    resolver.insert(uri, values);
}
}
