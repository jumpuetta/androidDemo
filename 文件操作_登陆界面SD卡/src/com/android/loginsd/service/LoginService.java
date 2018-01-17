package com.android.loginsd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.widget.Toast;

public class LoginService extends AndroidTestCase{
   public static boolean saveUserInfo(Context context,String username, String password,int mod){
	   
       try {
    	   FileOutputStream fos = null;
    	  // File file = new File("/data/data/com.android.login/userinfo.txt");
		   //File file = new File(context.getFilesDir(),"userInfo.txt");
    	   //context.getFilesDir()��õ�Ŀ¼Ϊ/data/data/����/files
		   //context.getCacheDir()��ȡĿ¼Ϊ����Ŀ¼/data/data/����/cache
		   //context.openFileInput(name);
		   //context.openFileOutput(name, mode);
    	   if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
    		   //��ȡ�ⲿ�豸Ŀ¼
    		   String dir = Environment.getExternalStorageDirectory().toString();  
    	       File file = new File(dir+"/userinfo.txt");
    	       fos = new FileOutputStream(file);
		       fos.write((username+"&"+password).getBytes());
		       fos.close();
		       return true;
		   }else{
			   Toast.makeText(context,"SD��������",Toast.LENGTH_SHORT).show();
			   return false;
		   }
	       } catch (Exception e) {
		     e.printStackTrace();
	       }
       return false;
   }
   
   public static Map<String,String> getUserInfo(Context context){
	  
	   try {
		   File file = new File(Environment.getExternalStorageDirectory().toString(),"userinfo.txt");
		  
		   FileInputStream fis = new FileInputStream(file);
			  
		   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		   String str[] = br.readLine().split("&");
		   Map<String,String> map = new HashMap<String, String>();
		   map.put("username",str[0]);
		   map.put("password",str[1]);
		   br.close();
		   return map;
	   } catch (Exception e) {
		e.printStackTrace();
	}
	   return null;
   }
   
  
}
