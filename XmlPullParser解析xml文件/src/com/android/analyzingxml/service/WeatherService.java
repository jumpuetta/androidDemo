package com.android.analyzingxml.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.util.Xml;

import com.android.analyzingxml.domain.WeatherInfo;

public class WeatherService extends Activity{
    public static  List<WeatherInfo> getWeatherInfos(InputStream fis){
		XmlPullParser xpp = Xml.newPullParser();
		try {
			xpp.setInput(fis,"GBK");
			List<WeatherInfo> list = null;
			WeatherInfo wi = null;
			int type = xpp.getEventType();
			while(type!=xpp.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
					 if("infos".equals(xpp.getName()))
						 list = new ArrayList<WeatherInfo>();
					 else if("city".equals(xpp.getName())){
						 wi = new WeatherInfo();
					     String id = xpp.getAttributeValue(0);
					     wi.setId(id);
					 }else if("temp".equals(xpp.getName())){
						 wi.setTemp(xpp.nextText());
					 }else if("wind".equals(xpp.getName())){
						 wi.setWind(xpp.nextText());
					 }else if("weather".equals(xpp.getName())){
						 wi.setWeather(xpp.nextText());
					 }else if("pm".equals(xpp.getName())){
						 wi.setPm(xpp.nextText());
					 }else if("name".equals(xpp.getName())){
						 wi.setName(xpp.nextText());
					 }
					break;

				case XmlPullParser.END_TAG:
					if("city".equals(xpp.getName())){
					     list.add(wi);
					     wi = null;
					}
					break;
			    default :
			        break;
				}
				type = xpp.next();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    	
    }
}
