package com.android.sharepreference;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.sharepreference.service.LoginService;

public class MainActivity extends Activity {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_remeber;
    private RadioGroup rg;
    private int mod;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		et_username = (EditText)findViewById(R.id.etusername);
		et_password = (EditText)findViewById(R.id.etpassword);
		cb_remeber = (CheckBox)findViewById(R.id.cbremeber);
		rg = (RadioGroup)findViewById(R.id.rg);
		//检查是否有保存的用户数据
		Map<String,String> map = LoginService.getUserInfo(this);
		if(map!=null){
			et_username.setText(map.get("username"));
			et_password.setText(map.get("password"));
		}
	}

	public void login(View view) {
		String name = et_username.getText().toString().trim();
        String pwd = et_password.getText().toString().trim(); 
    		LoginService.saveUserInfo(this,name, pwd);
    		Toast.makeText(this, "文件保存成功！", Toast.LENGTH_SHORT).show();	
        	if(name.equals("jiangpeng")&&pwd.equals("123")){
        	 Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();	
        	 setContentView(R.layout.activity_main);
        	}else{
        	 Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();		
        	}
	}
}
