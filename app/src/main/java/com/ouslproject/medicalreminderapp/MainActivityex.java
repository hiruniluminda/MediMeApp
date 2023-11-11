package com.ouslproject.medicalreminderapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityex extends AppCompatActivity implements SensorEventListener {
    private Button buttonMap;
    private Button buttonWalk;
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemperatureSensoreAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainex);
//sensor part
        textview = findViewById(R.id.textView6);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensoreAvailable = true;
        }else{
            textview.setText("Temperature sensor is not available");
            isTemperatureSensoreAvailable = false;
        }

        //button click into google map
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhelloooo();
            }
        });
        //button click into walking step counter section
        buttonWalk = (Button) findViewById(R.id.buttonWalk);
        buttonWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhellooooo();
            }
        });
    }
    //linking for google map
    public void openhelloooo(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
    //linking for walking step counter
    public void openhellooooo(){
        Intent intent = new Intent(this,MainActivitywalkCount.class);
        startActivity(intent);
    }
//sensor part
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textview.setText(sensorEvent.values[0]+"ºc");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(isTemperatureSensoreAvailable){
            sensorManager.registerListener(this,tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(isTemperatureSensoreAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}