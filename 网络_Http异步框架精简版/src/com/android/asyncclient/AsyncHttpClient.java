package com.android.asyncclient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Message;



public class AsyncHttpClient {
  public void get(final String path,final AsyncHttpResponseHandler handler){
	  new Thread(){
		  public void run(){
			  HttpClient client = new DefaultHttpClient();
			  HttpGet get = new HttpGet(path);
			  try {
				HttpResponse response = client.execute(get);
				int code = response.getStatusLine().getStatusCode();
				if(code==200){
					InputStream is = response.getEntity().getContent();
					int len;
					byte buffer[] = new byte[1024];
					StringBuilder sb = new StringBuilder("");
					while((len=is.read(buffer))!=-1){
						sb.append(new String(buffer,0,len,"utf-8"));
					}
					is.close();
					Message msg = new Message();
					msg.what = 1;
					msg.obj = sb.toString();
					handler.sendMessage(msg);
				}else{
					Message msg = new Message();
					msg.what = 2;
					msg.obj = "服务器端异常";
					handler.sendMessage(msg);
				}
			    } catch (Exception e) {
			    	Message msg = new Message();
					msg.what = 2;
					msg.obj = "连接服务器异常";
					handler.sendMessage(msg);
		   }
	     }
	  }.start();
  } 
  
  public void post(final String path,final List<NameValuePair> parameters,final AsyncHttpResponseHandler handler){
	  new Thread(){
		  public void run(){	
			  HttpClient client = new DefaultHttpClient();
			  HttpPost post = new HttpPost(path);
			  try {
				post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
				HttpResponse response = client.execute(post);
				int code = response.getStatusLine().getStatusCode();
				if(code==200){
					InputStream is = response.getEntity().getContent();
					int len;
					byte buffer[] = new byte[1024];
					StringBuilder sb = new StringBuilder("");
					while((len=is.read(buffer))!=-1){
						sb.append(new String(buffer,0,len,"utf-8"));
					}
					is.close();
					Message msg = new Message();
					msg.what = 1;
					msg.obj = sb.toString();
					handler.sendMessage(msg);
				}else{
					Message msg = new Message();
					msg.what = 2;
					msg.obj = "服务器端异常";
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				Message msg = new Message();
				msg.what = 2;
				msg.obj = "连接服务器异常";
				handler.sendMessage(msg);
			}
		}
	  }.start();
  }  
}
