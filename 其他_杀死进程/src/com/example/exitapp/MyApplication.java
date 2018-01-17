package com.example.exitapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	//存放所有的被开启的activity
	public List<Activity> activies;

	//在手机当前应用程序的进程创建的时候 调用的方法.
	@Override
	public void onCreate() {
		activies = new ArrayList<Activity>();
		super.onCreate();
	}
}
