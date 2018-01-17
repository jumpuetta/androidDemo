package com.android.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
	//���й㲥�¼�������ʱ�� �ͻ�ִ��onrecevie����
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
           //��ȡ�����¼�ͨ��sendBroadcast()���������������ݣ���ǰ��Ϊ����ĵ绰����
           String number = getResultData();
           //���ĺ���ΪIP����
           SharedPreferences sp = context.getSharedPreferences("ipnumber", Context.MODE_PRIVATE);
           String ip = sp.getString("ipnumber", "");
           String ip_number = ip + number;
           //���ý������Ϊ�µ����ݣ������ݻ��滻���������¼�ʹ�õ�����
           setResultData(ip_number);
           //�þ���벻����ֹϵͳ��绰����Ϊϵͳ���õ���
           //sendOrderedBroadcast(intent, null, new FinalRecevier(), null, 0, null, null);
           abortBroadcast();
	}

}
