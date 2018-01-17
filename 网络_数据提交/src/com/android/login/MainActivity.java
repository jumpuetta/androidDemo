package com.android.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.service.LoginService;
import com.android.service.LoginServiceByClient;

public class MainActivity extends Activity {
    private EditText username;
    private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.et_username);
		password = (EditText)findViewById(R.id.et_password);
	}
  
	public void loginGet(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		new Thread(new Runnable() {
			public void run() {
	        	final String message = LoginService.loginByGet(name, pwd);
	        	/**
	        	 * 该方法的线程参数会首先进行校验，若为主线程则直接执行，
	        	 * 若不是主线程则将其执行代码加到主线程后执行
	        	 */
	        	runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}).start();
	
	}
	
	public void loginPost(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		new Thread(new Runnable() {
			public void run() {
	        	final String message = LoginService.loginByPost(name, pwd);
	        	runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}).start();
	
	}
	
	public void loginClientGet(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		new Thread(new Runnable() {
			public void run() {
	        	final String message = LoginServiceByClient.loginByClientGet(name, pwd);
	        	runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}).start();
	
	}
	
	public void loginClientPost(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		new Thread(new Runnable() {
			public void run() {
	        	final String message = LoginServiceByClient.loginByClientPost(name, pwd);
	        	runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}).start();
	
	}
}
