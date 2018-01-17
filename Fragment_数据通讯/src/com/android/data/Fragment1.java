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
 * ������С��activity activity��Ҫ���嵥�ļ�����
 * 
 * @author Administrator fragment �����ڲ����ļ�����
 * 
 */
@SuppressLint("NewApi")
public class Fragment1 extends Fragment {

	/**
	 * ��fragment����ʼ�� Ҫ��ʾ�������ݵ�ʱ�� ����.
	 */
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment1, container, false);
		Button bt = (Button)view.findViewById(R.id.bt1);
				
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//�õ���ǰfragment�����ص�activity
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
