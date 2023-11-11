package com.ouslproject.medicalreminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ouslproject.medicalreminderapp.activity.MainActivity3;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton2;
    private ImageButton imageButton;
    private ImageButton imageButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imageButton = (ImageButton) findViewById(R.id.imageButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openmultiplayer();
//            }
//        });
//    }
//
//    public void openmultiplayer(){
//        Intent intent = new Intent(this, multiplayer.class);
//        startActivity(intent);
//    }}
//button click task into multimedia section
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhello();
            }
        });
//button click task into medication reminder section
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhelloo();
            }
        });
//button click task into exercise section
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhelloooo();
            }
        });

    }
//linking multimedia section
public void openhello(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        }
//linking medical reminder section
    public void openhelloo(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
//linking exercise section
    public void openhelloooo(){
        Intent intent = new Intent(this, MainActivityex.class);
        startActivity(intent);
    }
}



//        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, multiplayer.class);
//                startActivity(intent);
////                finish();
//            }
//
//        });

//    }}

//        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent intent = new Intent(SplashScreen.this, AdminActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

//    }
//}


//public class SplashScreen extends AppCompatActivity {
//
//
//}