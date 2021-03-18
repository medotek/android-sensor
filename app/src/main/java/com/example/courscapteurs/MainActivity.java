package com.example.courscapteurs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    ListView lv;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Sensor> deviceSensors;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void getSensorList(View v) {
        lv = (ListView) findViewById (R.id.listView1);
        List<Sensor> deviceSensors;
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        lv.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  deviceSensors));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //verify if a sensor(a specific sensor) is available
    public void getAvailableSensorList(View v) {
        lv = (ListView) findViewById (R.id.listView1);
        lv.setAdapter(null);
        List<Sensor> deviceSensors;
        if (sensorManager != null) {
            boolean AccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).isWakeUpSensor();
            if(AccelerometerSensor) {
                String test = "AccelerometerSensor is unavailable";
                lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Collections.singletonList(test)));
            } else {
                String test = "AccelerometerSensor is available";
                lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Collections.singletonList(test)));
            }
        }

    }
}