package com.android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceive2 extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
	    	 System.out.println("接受者2");
            Toast.makeText(context, "接受者2：我接收到了广播",Toast.LENGTH_SHORT).show();
	        abortBroadcast();
	}

}
