package com.android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceive1 extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		    System.out.println("������1");
            Toast.makeText(context, "������1���ҽ��յ��˹㲥",Toast.LENGTH_SHORT).show();
	}
}

