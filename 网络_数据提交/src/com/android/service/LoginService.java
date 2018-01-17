package com.android.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginService {
    public  static String loginByPost(String name , String pwd){
    	String path = "http://10.129.67.177:8080/login/login";
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("POST");
					String data = "username="+URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", data.length()+"");
					
					//告诉conn是否允许往服务器写数据
					conn.setDoOutput(true);
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					OutputStream os = conn.getOutputStream();
					os.write(data.getBytes());
					int code = conn.getResponseCode();
					if(code==200){
						InputStream is = conn.getInputStream();
						int len;
						byte buffer[] = new byte[1024];
						StringBuilder sb = new StringBuilder("");
						while((len=is.read(buffer))!=-1){
							sb.append(new String(buffer,0,len,"utf-8"));
						}
						is.close();
						return sb.toString();
					}else{
						return "请求错误";
					}
				} catch (Exception e) {
					return "连接服务器错误";
				}
    }
    
    public  static String loginByGet(String name , String pwd){
    	
				try {
					String path ="http://10.129.67.177:8080/login/login?username="+URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8") ;
					//将字符集进行编码
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					int code = conn.getResponseCode();
					if(code==200){
						InputStream is = conn.getInputStream();
						int len;
						byte buffer[] = new byte[1024];
						StringBuilder sb = new StringBuilder("");
						while((len=is.read(buffer))!=-1){
							sb.append(new String(buffer,0,len,"utf-8"));
						}
						is.close();
						return sb.toString();
					}else{
						return "请求错误";
					}
				} catch (Exception e) {
					return "连接服务器错误";
				}
    }
}
