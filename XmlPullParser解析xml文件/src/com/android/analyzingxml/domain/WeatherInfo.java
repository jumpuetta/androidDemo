package com.android.analyzingxml.domain;

public class WeatherInfo {
	private String id;
    private String name;
    private String temp;
    private String weather;
    private String pm;
    private String wind;
    public WeatherInfo() {
	}
	public WeatherInfo(String id,String name, String temp, String weather, String pm,
			String wind) {
		super();
		this.id = id;
		this.name = name;
		this.temp = temp;
		this.weather = weather;
		this.pm = pm;
		this.wind = wind;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
   @Override
public String toString() {
	return "城市ID:"+id+"名称:"+name+"天气:"+weather+"风速:"+wind+"PM指数:"+pm+"温度:"+temp;
}
}
