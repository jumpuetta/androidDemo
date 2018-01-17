package com.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AlipayService extends Service {

	public IBinder onBind(Intent intent) {
		System.out.println("远程支付服务onBind");
		return new MyBinder();
	}
	
	private class MyBinder extends PayService.Stub{

		public void pay() {
			payService();
		}

	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("远程支付服务onCreate");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("远程支付服务onDestroy");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		System.out.println("远程支付服务onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("远程支付服务onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("远程支付服务onUnbind");
		return super.onUnbind(intent);
	}
	
	public void payService(){
		System.out.println("远程支付服务的支付服务");
	}
}
