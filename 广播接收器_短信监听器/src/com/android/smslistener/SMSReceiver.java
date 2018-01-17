package com.android.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		//谷歌定制的模版代码   4.0版本的广播接收者需要用户自己打开该应用才能实现监听
		Object[] pdus =  (Object[]) intent.getExtras().get("pdus");
	    for(Object pdu: pdus){
	    	SmsMessage sms = SmsMessage.createFromPdu((byte[])pdu);
	    	String body = sms.getMessageBody();
	    	String sender = sms.getOriginatingAddress();
	    	System.out.println(sender+":"+body+":"+getResultData());
	    	//该方法终止广播  由于该广播接收者优先级高于系统短息应用的
	    	 //所以终止广播后能导致短息拦截的效果
	    	if ("5556".equals(sender)) {
				// 短信的拦截
				abortBroadcast();
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sender, null, "我已经喜欢上 xxx了 ,你去死吧", null, null);
			}
	    }
	}

}
