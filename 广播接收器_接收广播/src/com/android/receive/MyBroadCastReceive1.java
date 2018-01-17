package com.android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceive1 extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		    System.out.println("接受者1");
            Toast.makeText(context, "接受者1：我接收到了广播",Toast.LENGTH_SHORT).show();
	}
}

