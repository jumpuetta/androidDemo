package com.android.analyzingxml;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import com.android.analyzingxml.domain.WeatherInfo;
import com.android.analyzingxml.service.WeatherService;

public class MainActivity extends Activity {
    private TextView tvshowweather;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvshowweather = (TextView)findViewById(R.id.tv_shoeweather);
		File file = new File(Environment.getExternalStorageDirectory(),"weather.xml");
		try {
			FileInputStream fis = new  FileInputStream(file);
			List<WeatherInfo> list = WeatherService.getWeatherInfos(fis);
			//List<WeatherInfo> list = WeatherService.getWeatherInfos(MainActivity.class.getClassLoader().getResourceAsStream("weather.xml"));
			tvshowweather.setText(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this,"xmlΩ‚Œˆ ß∞‹£°£°",0).show();
		}
	}


}
