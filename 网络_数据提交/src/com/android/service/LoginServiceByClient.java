package com.android.service;

import java.io.InputStream;
import java.net.URLEncoder;
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

public class LoginServiceByClient {
    public  static String loginByClientPost(String name , String pwd){
				try {
					String path = "http://10.129.67.177:8080/login/login";
					//1.�������
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(path);
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
					parameters.add(new BasicNameValuePair("username",name));
					parameters.add(new BasicNameValuePair("password",pwd));
					//������Ҫ�ύ������ʵ��
					post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
					HttpResponse response = client.execute(post);
					//�õ����ص���Ӧ��
					int code = response.getStatusLine().getStatusCode();
					if(code==200){
						//�õ�������
						InputStream is = response.getEntity().getContent();
						int len;
						byte buffer[] = new byte[1024];
						StringBuilder sb = new StringBuilder("");
						while((len=is.read(buffer))!=-1){
							sb.append(new String(buffer,0,len,"utf-8"));
						}
						is.close();
						return sb.toString();
					}else{
						return "�������";
					}
				} catch (Exception e) {
					return "���ӷ���������";
				}
    }
    
    public  static String loginByClientGet(String name , String pwd){
    	//1.�������
    	HttpClient client = new DefaultHttpClient();
		try {
			String  path = "http://10.129.67.177:8080/login/login?username="+URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8");
			HttpGet get = new HttpGet(path);
			HttpResponse response = client.execute(get);
			//�õ����ص���Ӧ��
			int code = response.getStatusLine().getStatusCode();
			if(code==200){
				//�õ�������
				InputStream is = response.getEntity().getContent();
				int len;
				byte buffer[] = new byte[1024];
				StringBuilder sb = new StringBuilder("");
				while((len=is.read(buffer))!=-1){
					sb.append(new String(buffer,0,len,"utf-8"));
				}
				is.close();
				return sb.toString();
			}else{
				return "�������";
			}
		} catch (Exception e) {
			return "����·������";
		}
    }
}
