package com.example.superheroes;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity  {

    VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_splash);
        videoView = (VideoView) findViewById(R.id.videoView);*/

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.marvelintro);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(mp -> startNextActivity());

        videoView.start();
    }

    private void startNextActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}