package com.android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceive3 extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		    System.out.println("������3");
            Toast.makeText(context, "������3���ҽ��յ��˹㲥",Toast.LENGTH_SHORT).show();
	}

}
