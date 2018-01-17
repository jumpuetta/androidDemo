package com.android.fragmentoptions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener{
    private  TextView  tv_new1;
    private  TextView  tv_new2;
    private  TextView  tv_new3;
    private  TextView  tv_new4;
    private  Fragment f1,f2,f3,f4;
    private FragmentManager fm;
    private FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_new1 = (TextView)findViewById(R.id.tv_new1);
		tv_new2 = (TextView)findViewById(R.id.tv_new2);
		tv_new3 = (TextView)findViewById(R.id.tv_new3);
		tv_new4 = (TextView)findViewById(R.id.tv_new4);
		
		tv_new1.setOnClickListener(this);
		tv_new2.setOnClickListener(this);
		tv_new3.setOnClickListener(this);
		tv_new4.setOnClickListener(this);
		
		f1 = new Fragment1();
		f2 = new Fragment2();
		f3 = new Fragment3();
		f4 = new Fragment4();
		
		fm = getFragmentManager();
		
		//初始化界面
		ft = fm.beginTransaction();
		ft.replace(R.id.old,f1);//替换的是容器里面的内容     containerViewId为容器的Id
		ft.commit();
	}


	@Override
	public void onClick(View v) {
		ft = fm.beginTransaction();
		switch (v.getId()) {
		case R.id.tv_new1:
			ft.replace(R.id.old,f1);
			break;
        case R.id.tv_new2:
    		ft.replace(R.id.old,f2);
			break;
        case R.id.tv_new3:
    		ft.replace(R.id.old,f3);
	        break;
        case R.id.tv_new4:
    		ft.replace(R.id.old,f4);
	        break;
		default:
			break;
		}	
		ft.commit();
	}

}
