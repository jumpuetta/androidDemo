package com.android.fragmentoptions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ������С��activity activity��Ҫ���嵥�ļ�����
 * 
 * @author Administrator fragment �����ڲ����ļ�����
 * 
 */
public class Fragment1 extends Fragment {

	/**
	 * ��fragment����ʼ�� Ҫ��ʾ�������ݵ�ʱ�� ����.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, container, false);

	}

}
