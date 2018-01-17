package com.itheima.dynamicallyfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类似于小的activity activity需要在清单文件配置
 * 
 * @author Administrator fragment 可以在布局文件配置
 * 
 */
public class Fragment1 extends Fragment {

	/**
	 * 当fragment被初始化 要显示他的内容的时候 调用.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, container, false);

	}

}
