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
		    values.put("body", "�𾴵Ľ���������β��Ϊ65424�Ľ��п������������1000000000���������Ϊ2000000");
		    resolver.insert(uri, values);
}
}
