package com.animesh.demoapp;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class SoundDemo extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager audio ;
    private int maxVol;
    private int curVol;
    private SeekBar volumneControl;
    private SeekBar musicControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sounddemo);
        mMediaPlayer = MediaPlayer.create(this, R.raw.bell);

        audio =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        maxVol = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curVol = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumneControl = findViewById(R.id.seekBar);
        volumneControl.setMax(maxVol);
        volumneControl.setProgress(curVol);

        volumneControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int p, boolean b) {
                Log.i("SeekBar Value", Integer.toString(p));
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,p,AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicControl.setProgress(mMediaPlayer.getCurrentPosition());
            }
        },0, 1000);


        musicControl = (SeekBar) findViewById(R.id.musicSeek);
        musicControl.setMax(mMediaPlayer.getDuration());
        musicControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mMediaPlayer.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void play(View view){

        mMediaPlayer.start();
    }

    public void pause(View view){

        mMediaPlayer.pause();
    }





}
