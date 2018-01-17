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
    	//ע��㲥������  Ψһ���Դ����������
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
	   Toast.makeText(getApplicationContext(), "��������ķ�����������", Toast.LENGTH_SHORT).show();
   }
   //���������嵥�ļ�ע���ڲ���㲥������   �轫���ڲ��ඨ��Ϊstatic
   public  class MyBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			method();
		}

	}
}
