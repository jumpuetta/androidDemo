package com.android.observer;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			ContentResolver resolver = getContentResolver();
		    Uri smsuri = Uri.parse("content://sms/");
		    Uri personuri = Uri.parse("content://notify");
		    resolver.registerContentObserver(smsuri, true, new MySMSObserver(new Handler()));
		    resolver.registerContentObserver(personuri, true, new MyPersonObserver(new Handler()));
            
	}
	private class MySMSObserver extends ContentObserver {

		public MySMSObserver(Handler handler) {
			super(handler);
		}
       /**当内容观察者观察到数据库的内容变化时   自动执行
        * 观察到消息邮箱里面有一条数据库信息变化时
        * */
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Uri uri = Uri.parse("content://sms/");
			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(uri,new String[]{"address","date","type","body"}, null,null,null);
			cursor.moveToFirst();
			Toast.makeText(MainActivity.this,"发件人："+cursor.getString(0)+"\n内容："+cursor.getString(3), Toast.LENGTH_LONG).show();
		}
		
}
	private class MyPersonObserver extends ContentObserver {
		public MyPersonObserver(Handler handler) {
			super(handler);
		}
       /**当内容观察者观察到数据库的内容变化时   自动执行
        * 观察到消息邮箱里面有一条数据库信息变化时
        * */
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this,"person数据库发生变化", Toast.LENGTH_LONG).show();
		}
		
}
}
