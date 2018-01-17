package com.android.serviceuser;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.android.service.PayService;

public class MainActivity extends Activity {
    private Intent intent ;
    private PayService payService;
	@Override
	/**
	 * 
       aidl: android interface defination language android接口定义语言.
                 解决进程间接口定义 接口通讯的问题.
       IPC : inter process communication 进程间通讯
	 * */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intent = new Intent();
		intent.setAction("android.intent.alipay");
	}
    public void bind(View view){
    	bindService(intent, new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				payService =  PayService.Stub.asInterface(service);
			}
		}, BIND_AUTO_CREATE);
    }
    
    
    
    public void pay(View view){
    	try {
			payService.pay();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}
