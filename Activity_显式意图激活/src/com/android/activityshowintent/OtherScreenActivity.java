package com.android.activityshowintent;

import android.app.Activity;
import android.os.Bundle;

public class OtherScreenActivity extends Activity {
   @Override
   /**
    * ��дActivity��onCreate()��������ʼ��UI
    * android�Ĵ����֮һ   ��Ҫ��androidManifest.xml�嵥�ļ���������
    * ��.xml�����ļ�ת��ΪView����Ҳ�ǵ��õ�ϵͳ����
    */
   
    protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_two);
   }
}
