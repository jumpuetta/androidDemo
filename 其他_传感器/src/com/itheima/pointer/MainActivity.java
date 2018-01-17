package com.itheima.pointer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private SensorManager sensorManager;
	private MyListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// ��ȡϵͳ�ķ��򴫸���
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		// sensorManager.getOrientation(R, values)

		listener = new MyListener();
		sensorManager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	private class MyListener implements SensorEventListener {
		float endAngle = 0;
		
		//���������ݷ����仯ʱ����
		@Override
		public void onSensorChanged(SensorEvent event) {
			float  startAngle = event.values[0];
			System.out.println("�Ƕ�:"+startAngle);
			RotateAnimation ra = new RotateAnimation(-endAngle, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			iv.startAnimation(ra);
			endAngle = startAngle; //��ת��  �ĽǶ�
		}
        
		//�����������ȷ����仯ʱ����
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

	}

	@Override
	protected void onDestroy() {
		//��Ӧ�ùر�ʱ�����ٴ������ļ�����
		sensorManager.unregisterListener(listener);
		listener = null;
		super.onDestroy();
	}
}
