package com.android.data;

import com.android.data.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Fragment2 extends Fragment {
	private TextView tv;
	/**
	 * ��fragment����ʼ�� Ҫ��ʾ�������ݵ�ʱ�� ����.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment2, container, false);
	    tv = (TextView)view.findViewById(R.id.tv_f2);
		return view;

	}
	
	public void setText(){
		tv.setText("����  ���޸ĵ�ү��");
	}

}
