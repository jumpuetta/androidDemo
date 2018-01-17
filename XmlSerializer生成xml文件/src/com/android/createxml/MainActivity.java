package com.android.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.android.createxml.domain.SmsInfo;

public class MainActivity extends Activity {
    private List<SmsInfo> smsInfos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    smsInfos = new ArrayList<SmsInfo>();
	    Random random = new Random();
	    String number = "13500000001";
	    for( int i=0;i<10;i++){
	    	smsInfos.add(new SmsInfo(System.currentTimeMillis(),random.nextInt(2)+1,"短信内容"+i,number+1,i));
	    }
	    
	}
	
    public void backSms1(View view){
    	//假设已经获取到了所有短信
    	StringBuilder sb = new StringBuilder();
    	sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    	sb.append("<smss>");
    	for (SmsInfo smsInfo : smsInfos) {
			sb.append("<sms>");
			  sb.append("<data>");
			  sb.append(smsInfo.getDate());
			  sb.append("</data>");
			  sb.append("<type>");
			  sb.append(smsInfo.getType());
			  sb.append("</type>");
			  sb.append("<body>");
			  sb.append(smsInfo.getBody());
			  sb.append("</body>");
			  sb.append("<adress>");
			  sb.append(smsInfo.getAddress());
			  sb.append("</adress>");
			sb.append("</sms>");
		}
    	sb.append("</smss>");
    	File file = new File(Environment.getExternalStorageDirectory(),"smsback.xml");
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
		 	fos.write(sb.toString().getBytes());
		 	fos.close();
		 	Toast.makeText(this,"备份成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this,"备份失败", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
    }
    
    public void backSms2(View view){
		XmlSerializer xs = Xml.newSerializer();
		File file = new File(Environment.getExternalStorageDirectory(),"smsback2.xml");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			xs.setOutput(fos, "utf_8");
			xs.startDocument("utf-8",true);
			xs.startTag(null,"smss");
			for (SmsInfo smsInfo : smsInfos) {
				xs.startTag(null,"sms");
				  xs.attribute(null,"id",Integer.toString(smsInfo.getId()));
				  xs.startTag(null,"date"); 
				  xs.text(smsInfo.getDate()+"");
				  xs.endTag(null,"date");
				  xs.startTag(null,"type"); 
				  xs.text(smsInfo.getType()+"");
				  xs.endTag(null,"type");
				  xs.startTag(null,"body"); 
				  xs.text(smsInfo.getBody());
				  xs.endTag(null,"body");
				  xs.startTag(null,"adress"); 
				  xs.text(smsInfo.getAddress());
				  xs.endTag(null,"adress");
				xs.endTag(null,"sms");
			}
			xs.endTag(null,"smss");
			xs.endDocument();
			fos.close();
			Toast.makeText(this,"备份成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this,"备份失败", Toast.LENGTH_SHORT).show();
		}
	}

	
}
