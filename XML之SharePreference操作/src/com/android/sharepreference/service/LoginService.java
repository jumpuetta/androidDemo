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
      //得到一个spf的编辑器
	   Editor editor = spf.edit();
	   editor.putString("username",username);
	   editor.putString("password",password);
	   //类似数据库的事物  保证数据同时提交
	   editor.commit();
   }
   
   public static Map<String,String> getUserInfo(Context context){
	   SharedPreferences spf = context.getSharedPreferences("config",Context.MODE_PRIVATE);
	   //config文件目录为/data/data/包名/shared_prefs/config.xml
	   Map<String,String> map = new HashMap<String, String>();
	   map.put("username",spf.getString("username",""));
	   map.put("password",spf.getString("password",""));
	   return map;
   }
   
  
}
