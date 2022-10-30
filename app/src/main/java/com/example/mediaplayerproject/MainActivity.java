package com.example.mediaplayerproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import javax.sql.CommonDataSource;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    MediaPlayer mp;
    SeekBar sb;
    AudioManager am;

    ImageView iv;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(this);
        mp = MediaPlayer.create(this, R.raw.rickroll);
        mp.start();


        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(100);
        sb.setProgress(100);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, max / 2, 0);
        sb.setOnSeekBarChangeListener(this);


        sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(this);
        iv = (ImageView) findViewById(R.id.imageView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        float alpha = (float)progress/100;
        iv.setAlpha(100);
        //iv.setAlpha(alpha);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            mp.start();
            iv.setVisibility(View.VISIBLE);
        } else {
            mp.pause();
            iv.setVisibility(View.INVISIBLE);
        }

    }
}
