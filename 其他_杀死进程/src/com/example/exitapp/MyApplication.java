package com.example.exitapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	//������еı�������activity
	public List<Activity> activies;

	//���ֻ���ǰӦ�ó���Ľ��̴�����ʱ�� ���õķ���.
	@Override
	public void onCreate() {
		activies = new ArrayList<Activity>();
		super.onCreate();
	}
}
