package com.android.send;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FinalRecevier extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("����final��receiver");
		 Toast.makeText(context, "����final��receiver",Toast.LENGTH_SHORT).show();
	}

}
