package com.android.fragmentoptions;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class Fragment4 extends Fragment {
	/**
	 * ��fragment����ʼ�� Ҫ��ʾ�������ݵ�ʱ�� ����.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment4, container, false);

	}

}
