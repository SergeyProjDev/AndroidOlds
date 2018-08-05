package com.sergey.quickstart;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    double ax,ay,az;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        tv = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    TextView tv;
    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged (SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
            tv.setText("X-asix" + "\n" + ax + "\n\n" + "Y-asix" + "\n" + ay + "\n\n"+ "Z-asix = " + "\n" + az);
        }
    }

}
