package com.android.videoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "���ŵ�����....", 1).show();
	}

}
