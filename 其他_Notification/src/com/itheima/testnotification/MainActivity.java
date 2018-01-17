package com.itheima.testnotification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

@SuppressLint("NewApi") public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * �ɰ汾��notification
	 * 
	 * @param view
	 */
	public void click1(View view) {
		//1.��ȡϵͳ֪ͨ�Ĺ�����
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//2.��ʼ��һ��notification�Ķ���
		 Notification notification = new Notification(R.drawable.notification, "���ǹ������ı�",  System.currentTimeMillis() );
 
		//3.����notification�ľ������
		 notification.flags = Notification.FLAG_AUTO_CANCEL;
		 
		 //ע��:ϸ��
		// notification.contentView = �Զ����notification view
		 
		 
		// notification.sound = Uri.parse(uriString);
		// notification.vibrate = new long[]{100,200,100};
		 Intent intent = new Intent();
		 intent.setAction(Intent.ACTION_VIEW);
		 intent.setData(Uri.parse("http://www.baidu.com"));
		 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		 notification.setLatestEventInfo(this, "���Ǵ�ı���", "���Ǳ������������", contentIntent);
		 //4.ֱ�Ӱ���Ϣ�� notification�Ĺ�����
		 nm.notify(0, notification);
	}

	/**
	 * �°�notification��д��.
	 * @param view
	 */
	public void click2(View view){
		 Intent intent = new Intent();
		 intent.setAction(Intent.ACTION_VIEW);
		 intent.setData(Uri.parse("http://www.baidu.com"));
		 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		
		//1.��ȡϵͳ֪ͨ�Ĺ�����
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//2.��notification���� ����һ��notification
		 Notification noti = new Notification.Builder(this)
         .setContentTitle("���Ǵ�ı���")
         .setContentText("��������")
         .setSmallIcon(R.drawable.notification)
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
         .setContentIntent(contentIntent)
         .build();
		//3.��notification��ʾ����
		 nm.notify(1, noti);
	}
	
}
