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
	 * 旧版本的notification
	 * 
	 * @param view
	 */
	public void click1(View view) {
		//1.获取系统通知的管理者
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//2.初始化一个notification的对象
		 Notification notification = new Notification(R.drawable.notification, "我是滚动的文本",  System.currentTimeMillis() );
 
		//3.设置notification的具体参数
		 notification.flags = Notification.FLAG_AUTO_CANCEL;
		 
		 //注意:细节
		// notification.contentView = 自定义的notification view
		 
		 
		// notification.sound = Uri.parse(uriString);
		// notification.vibrate = new long[]{100,200,100};
		 Intent intent = new Intent();
		 intent.setAction(Intent.ACTION_VIEW);
		 intent.setData(Uri.parse("http://www.baidu.com"));
		 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		 notification.setLatestEventInfo(this, "我是大的标题", "我是标题下面的内容", contentIntent);
		 //4.直接把消息给 notification的管理者
		 nm.notify(0, notification);
	}

	/**
	 * 新版notification的写法.
	 * @param view
	 */
	public void click2(View view){
		 Intent intent = new Intent();
		 intent.setAction(Intent.ACTION_VIEW);
		 intent.setData(Uri.parse("http://www.baidu.com"));
		 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		
		//1.获取系统通知的管理者
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//2.用notification工厂 创建一个notification
		 Notification noti = new Notification.Builder(this)
         .setContentTitle("我是大的标题")
         .setContentText("我是内容")
         .setSmallIcon(R.drawable.notification)
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
         .setContentIntent(contentIntent)
         .build();
		//3.把notification显示出来
		 nm.notify(1, noti);
	}
	
}
