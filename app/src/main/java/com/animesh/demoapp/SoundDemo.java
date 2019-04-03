package com.animesh.demoapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class SoundDemo extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager audio ;
    private int maxVol;
    private int curVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sounddemo);
        mMediaPlayer = MediaPlayer.create(this, R.raw.bell);
        audio =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        maxVol = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curVol = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        final SeekBar volumneControl =(SeekBar) findViewById(R.id.seekBar);
        volumneControl.setMax(maxVol);
        volumneControl.setProgress(curVol);

        volumneControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int p, boolean b) {
                Log.i("SeekBar Value", Integer.toString(p));
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,p,0);
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
