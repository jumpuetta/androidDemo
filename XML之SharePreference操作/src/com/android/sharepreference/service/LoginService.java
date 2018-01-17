package com.android.sharepreference.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.test.AndroidTestCase;

public class LoginService extends AndroidTestCase{
   public static void saveUserInfo(Context context,String username, String password){
	   SharedPreferences spf = context.getSharedPreferences("config",Context.MODE_PRIVATE);
      //�õ�һ��spf�ı༭��
	   Editor editor = spf.edit();
	   editor.putString("username",username);
	   editor.putString("password",password);
	   //�������ݿ������  ��֤����ͬʱ�ύ
	   editor.commit();
   }
   
   public static Map<String,String> getUserInfo(Context context){
	   SharedPreferences spf = context.getSharedPreferences("config",Context.MODE_PRIVATE);
	   //config�ļ�Ŀ¼Ϊ/data/data/����/shared_prefs/config.xml
	   Map<String,String> map = new HashMap<String, String>();
	   map.put("username",spf.getString("username",""));
	   map.put("password",spf.getString("password",""));
	   return map;
   }
   
  
}
