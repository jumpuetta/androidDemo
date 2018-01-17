package com.android.login.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.android.login.util.ContextUtil;

import android.content.Context;
import android.test.AndroidTestCase;

public class LoginService extends AndroidTestCase{
   public static boolean saveUserInfo(Context context,String username, String password,int mod){
	   
       try {
    	   FileOutputStream fos = null;
    	  // File file = new File("/data/data/com.android.login/userinfo.txt");
		   //File file = new File(context.getFilesDir(),"userInfo.txt");
    	   //context.getFilesDir()获得的目录为/data/data/包名/files
		   //context.getCacheDir()获取目录为缓存目录/data/data/包名/cache
		   //context.openFileInput(name);获得的目录为/data/data/包名/files/name的文件的操作流
		   //context.openFileOutput(name, mode);
    	   switch (mod) {
		case 0:
			fos = context.openFileOutput("private.txt", Context.MODE_PRIVATE);
			break;
		case 1:
			fos = context.openFileOutput("reable.txt", Context.MODE_WORLD_READABLE);
			break;
		case 2:
			fos = context.openFileOutput("writeable.txt", Context.MODE_WORLD_WRITEABLE);
			break;
		case 3:
			fos = context.openFileOutput("public.txt", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
			break;

		default:
			break;
		}
		   fos.write((username+"&"+password).getBytes());
		   fos.close();
		   return true;
	       } catch (Exception e) {
		     e.printStackTrace();
	       }
       return false;
   }
   
   public static Map<String,String> getUserInfo(Context context){
	  
	   try {
		   File file = new File(context.getFilesDir(),"public.txt");
		   FileInputStream isr = new FileInputStream(file);
		   BufferedReader br = new BufferedReader(new InputStreamReader(isr));
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
