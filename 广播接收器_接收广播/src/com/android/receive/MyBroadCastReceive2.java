package com.android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceive2 extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
	    	 System.out.println("������2");
            Toast.makeText(context, "������2���ҽ��յ��˹㲥",Toast.LENGTH_SHORT).show();
	        abortBroadcast();
	}

}
