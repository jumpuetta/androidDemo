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
       /**�����ݹ۲��߹۲쵽���ݿ�����ݱ仯ʱ   �Զ�ִ��
        * �۲쵽��Ϣ����������һ�����ݿ���Ϣ�仯ʱ
        * */
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Uri uri = Uri.parse("content://sms/");
			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(uri,new String[]{"address","date","type","body"}, null,null,null);
			cursor.moveToFirst();
			Toast.makeText(MainActivity.this,"�����ˣ�"+cursor.getString(0)+"\n���ݣ�"+cursor.getString(3), Toast.LENGTH_LONG).show();
		}
		
}
	private class MyPersonObserver extends ContentObserver {
		public MyPersonObserver(Handler handler) {
			super(handler);
		}
       /**�����ݹ۲��߹۲쵽���ݿ�����ݱ仯ʱ   �Զ�ִ��
        * �۲쵽��Ϣ����������һ�����ݿ���Ϣ�仯ʱ
        * */
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this,"person���ݿⷢ���仯", Toast.LENGTH_LONG).show();
		}
		
}
}
