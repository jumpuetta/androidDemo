package com.android.smsback;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.android.smsback.domain.SmsInfo;
import com.android.smsback.utils.SMSUtil;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

public void readSMS(View view){
	Uri uri = Uri.parse("content://sms/");
	ContentResolver cr = getContentResolver();
	Cursor cursor = cr.query(uri,new String[]{"address","date","type","body"}, null,null,null);
	List<SmsInfo> list = new ArrayList<SmsInfo>();
	while(cursor.moveToNext()){
		String address = cursor.getString(0);
		long date = cursor.getLong(1);
		int type = cursor.getInt(2);
		String body = cursor.getString(3);
		SmsInfo sms = new SmsInfo(date, type, body, address);
		list.add(sms);
	}
	SMSUtil.backSMS(list,this);
}
}
