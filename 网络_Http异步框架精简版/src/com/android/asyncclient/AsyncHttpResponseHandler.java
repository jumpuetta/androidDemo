package com.android.asyncclient;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class AsyncHttpResponseHandler extends Handler{
        public void doFailure(String content) {

		}
        
        public void doSuccess(String content) {

		}
        
        public void handleMessage(Message msg) {
        	switch (msg.what) {
			case 1:
				doSuccess(msg.obj.toString());
				break;
			case 2:
				doFailure(msg.obj.toString());
				break;
			default:
				break;
			}
        }
}
