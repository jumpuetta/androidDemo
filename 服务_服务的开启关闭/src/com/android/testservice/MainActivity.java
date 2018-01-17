package com.android.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	private BinderService myBinder;
	private MyConn conn;
	private boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
    @Override
    protected void onDestroy() {
   	  // TODO Auto-generated method stub
	   super.onDestroy();
	   if(flag && conn != null){
    	   unbindService(conn);
    	   flag = false;
    	}
    }
    public void start(View view){
    	Intent intent = new Intent(this,TestService.class);
		/**
		 * 开启服务  用API开始服务  其对象为系统new出来的
		 * startService方法若服务已被开启则会调用onStartCommand()——onStart()
		 *                若服务没有被开启则会调用onCreate——onStartCommand()——onStart()
		 * startService开启的服务在Activity关闭时  服务不会关闭但会解绑
		 * startService开启的服务在解除绑定时不会被销毁           
		 */
    	
		startService(intent);
    }
    
    public void stop(View view){
    	Intent intent = new Intent(this,TestService.class);
		//停止服务
		stopService(intent);
    }
    
    public void bind(View view){
    	Intent intent = new Intent(this,TestService.class);
		/**
		 * 绑定服务
		 * intent  激活服务的意图
		 * conn  服务代理人 用来与服务建立连接  不能为null
		 * BIND_AUTO_CREATE 若服务不曾在则会先开启该服务
		 * bindService方法若服务已被开启则会调用onBind()
		 *            若服务没有被开启则会调用onCreate——onBind()
		 * bindService开启服务在Activity关闭时  服务也会别解绑并关闭
		 * bindService因为绑定而开启的服务在解除绑定时同时也会被销毁
		 *            处于绑定状态的服务在解绑前不能被stopService销毁，若服务绑定时调用stopService方法不会立即生效，但它会等到unbindService方法调用后立即生效
		 */
    	conn =  new MyConn();
		flag = bindService(intent,conn, BIND_AUTO_CREATE );
    }
    
    public void unbind(View view){
    	/**
    	 * 若服务未被绑定而解除绑定会报异常
    	 * */
    	if(flag && conn != null){
    	   unbindService(conn);
    	   flag = false;
    	}
    }
    
	private class MyConn implements ServiceConnection{

		// 在服务被成功绑定的时候 调用的方法
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("春哥把代理人返回回来了...");
			//步骤3: 服务返回的ibinder对象 会被传递给 MyConn 的回调方法
			myBinder  = (BinderService) service;
			//该对象与服务中onBind()返回的IBinder对象为同一对象
		}
		// 在服务失去绑定的时候 调用的方法 只有程序异常 终止,
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
    public void change(View view){
    	// 由于系统框架在创建服务的时候 会创建与之对应的上下文,
        // 下面的代码 是直接new对象 .
    	// CungeService service = new CungeService();
        // service.changeSing("月亮之上");
        //步骤5: 利用ibinder 对象 间接的调用了服务里面的方法
    	myBinder.binderChange("hello");
    }
}
