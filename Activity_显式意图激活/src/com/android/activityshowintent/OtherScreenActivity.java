package com.android.activityshowintent;

import android.app.Activity;
import android.os.Bundle;

public class OtherScreenActivity extends Activity {
   @Override
   /**
    * 重写Activity的onCreate()方法，初始化UI
    * android四大组件之一   需要在androidManifest.xml清单文件里面配置
    * 将.xml布局文件转换为View对象也是调用的系统服务
    */
   
    protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_two);
   }
}
