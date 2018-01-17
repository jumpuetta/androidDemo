package com.android.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
	//当有广播事件产生的时候 就会执行onrecevie方法
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
           //获取监听事件通过sendBroadcast()方法发出来的数据，当前即为拨打的电话号码
           String number = getResultData();
           //更改号码为IP号码
           SharedPreferences sp = context.getSharedPreferences("ipnumber", Context.MODE_PRIVATE);
           String ip = sp.getString("ipnumber", "");
           String ip_number = ip + number;
           //设置结果数据为新的数据，该数据会替换掉所监听事件使用的数据
           setResultData(ip_number);
           //该句代码不能终止系统打电话，因为系统调用的是
           //sendOrderedBroadcast(intent, null, new FinalRecevier(), null, 0, null, null);
           abortBroadcast();
	}

}
