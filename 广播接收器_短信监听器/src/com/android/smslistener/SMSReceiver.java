package com.android.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		//�ȸ趨�Ƶ�ģ�����   4.0�汾�Ĺ㲥��������Ҫ�û��Լ��򿪸�Ӧ�ò���ʵ�ּ���
		Object[] pdus =  (Object[]) intent.getExtras().get("pdus");
	    for(Object pdu: pdus){
	    	SmsMessage sms = SmsMessage.createFromPdu((byte[])pdu);
	    	String body = sms.getMessageBody();
	    	String sender = sms.getOriginatingAddress();
	    	System.out.println(sender+":"+body+":"+getResultData());
	    	//�÷�����ֹ�㲥  ���ڸù㲥���������ȼ�����ϵͳ��ϢӦ�õ�
	    	 //������ֹ�㲥���ܵ��¶�Ϣ���ص�Ч��
	    	if ("5556".equals(sender)) {
				// ���ŵ�����
				abortBroadcast();
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sender, null, "���Ѿ�ϲ���� xxx�� ,��ȥ����", null, null);
			}
	    }
	}

}
