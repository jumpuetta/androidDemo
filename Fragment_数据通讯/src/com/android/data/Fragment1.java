package com.android.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 类似于小的activity activity需要在清单文件配置
 * 
 * @author Administrator fragment 可以在布局文件配置
 * 
 */
@SuppressLint("NewApi")
public class Fragment1 extends Fragment {

	/**
	 * 当fragment被初始化 要显示他的内容的时候 调用.
	 */
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment1, container, false);
		Button bt = (Button)view.findViewById(R.id.bt1);
				
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//得到当前fragment所挂载的activity
				Activity activity = getActivity();
				Fragment2 f2 = (Fragment2)activity.getFragmentManager().findFragmentById(R.id.fragment2);
				f2.setText();
			}
		});
		return view;
 
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

}
