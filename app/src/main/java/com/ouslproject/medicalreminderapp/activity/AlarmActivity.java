package com.ouslproject.medicalreminderapp.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ouslproject.medicalreminderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
//for displaying an alarm notification to the user when a reminder is triggered
public class AlarmActivity extends BaseActivity {
//bind view fields
    private static AlarmActivity inst;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.timeAndData)
    TextView timeAndData;
    @BindView(R.id.closeButton)
    Button closeButton;
    MediaPlayer mediaPlayer;

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);//to bind the views to the corresponding fields
        //starting notification sound
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notification);
        mediaPlayer.start();
//display title,description and date and time
        if(getIntent().getExtras() != null) {
            title.setText(getIntent().getStringExtra("TITLE"));
            description.setText(getIntent().getStringExtra("DESC"));
            timeAndData.setText(getIntent().getStringExtra("DATE") + ", " + getIntent().getStringExtra("TIME"));
        }
//load image from  drawable to alarm notification box
        Glide.with(getApplicationContext()).load(R.drawable.alert).into(imageView);
        closeButton.setOnClickListener(view -> finish());

    }
//used ondestroy method for mediaPlayer object is released to free up system resources
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
