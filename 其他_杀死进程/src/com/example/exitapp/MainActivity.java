package com.example.exitapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	//没开启一个activity 都把他的引用存起来
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.add(this);
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
    	//每开启一个activity 都把他的引用存起来
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.remove(this);
    	
    	super.onDestroy();
    }
    
    
    public void click(View view){
    	//可能不安全.
    	//安全的退出  首先要安全的把每一个activity都给关闭了. 
    	//获取当前应用的id    android.os.Process.myPid()
    	android.os.Process.killProcess(android.os.Process.myPid());
    	
    	ActivityManager manager= (ActivityManager)getSystemService(ACTIVITY_SERVICE);
    	manager.killBackgroundProcesses("com.android.browser");
    }
    public void click2(View v){
    	Intent intent = new Intent(this,Activity01.class);
    	startActivity(intent);
    }
}
