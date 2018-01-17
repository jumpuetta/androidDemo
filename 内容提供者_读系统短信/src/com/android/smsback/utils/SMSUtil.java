package com.android.smsback.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import com.android.smsback.domain.SmsInfo;

public class SMSUtil {
  public static void  backSMS(List<SmsInfo> smsInfos,Context context){
	  XmlSerializer xs = Xml.newSerializer();
		File file = new File(Environment.getExternalStorageDirectory(),"smsback.xml");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			xs.setOutput(fos, "utf_8");
			xs.startDocument("utf-8",true);
			xs.startTag(null,"smss");
			for (SmsInfo smsInfo : smsInfos) {
				  xs.startTag(null,"sms");
				  xs.attribute(null,"id",smsInfo.getId()+"");
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
			Toast.makeText(context,"备份成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context,"备份失败", Toast.LENGTH_SHORT).show();
		}
  }
}
