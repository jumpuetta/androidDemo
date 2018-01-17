package com.android.broadcastuse;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class AnService extends Service{
    private MyBroadCastReceiver receiver;
    public IBinder onBind(Intent intent) {
	return null;
   }
    
    public void onCreate() {
    	super.onCreate();
    	//注册广播接收者  唯一可以代码祖册的组件
    	IntentFilter filter = new IntentFilter();
     	filter.addAction("com.android.useservice");
    	receiver = new MyBroadCastReceiver();
    	registerReceiver(receiver, filter);
    }
    
    public void onDestroy() {
    	unregisterReceiver(receiver);
    	receiver = null;
    	super.onDestroy();
    }
    
    
   public void method(){
	   Toast.makeText(getApplicationContext(), "服务里面的方法被调用了", Toast.LENGTH_SHORT).show();
   }
   //若想利用清单文件注册内部类广播接收者   需将该内部类定义为static
   public  class MyBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			method();
		}

	}
}
