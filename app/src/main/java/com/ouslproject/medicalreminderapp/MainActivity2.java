 package com.ouslproject.medicalreminderapp;

 import android.os.Bundle;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

 import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
 import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
 import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

 public class MainActivity2 extends AppCompatActivity {
     private YouTubePlayerView youTubePlayerView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main2);
//add first youtube video
         youTubePlayerView = findViewById(R.id.activity_main_youtubePlayerView);
         getLifecycle().addObserver(youTubePlayerView);

         youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
             @Override
             public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                 String videoId = "gYsf8weF6MA";
                 youTubePlayer.loadVideo(videoId, 0);
             }
         });

//add second youtube video
         youTubePlayerView = findViewById(R.id.activity_main_youtubePlayerView2);
         getLifecycle().addObserver(youTubePlayerView);

         youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
             @Override
             public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                 String videoId = "IT94xC35u6k";
                 youTubePlayer.loadVideo(videoId, 0);
             }
         });

     }
 }
