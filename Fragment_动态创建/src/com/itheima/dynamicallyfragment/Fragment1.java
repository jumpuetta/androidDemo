package com.itheima.dynamicallyfragment;

import android.app.Activity;
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
	
	/**
	 * ����ʱ
	 * onattach->onCreate->onCreateView->onActivityCreated->onstart->onresume->onPause
	 * ����ʱ
	 * onstop->onDestroyView->ondestory->ondetach
	 * 
	 * һ����дonCreateView �õ�UI   onDestroyView �ͷ���Դ
	 * */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, container, false);

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		System.out.println("onattach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("oncreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		System.out.println("ondestroy");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		System.out.println("onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		System.out.println("ondetach");
		super.onDetach();
	}

	@Override
	public void onResume() {
		System.out.println("onresume");
		super.onResume();
	}

	@Override
	public void onStart() {
		System.out.println("onstart");
		super.onStart();
	}

	@Override
	public void onStop() {
		System.out.println("onstop");
		super.onStop();
	}

}
