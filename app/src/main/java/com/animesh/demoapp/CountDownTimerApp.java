package com.animesh.demoapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CountDownTimerApp extends AppCompatActivity {
    private EditText timerView;
    private SeekBar timerSeekBar;
    private Long milliSec;
    private Long millisUntilFinishedVar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);
        timerView = (EditText) findViewById(R.id.timerView);
        timerSeekBar = (SeekBar) findViewById(R.id.TimerSeek);

    }


    public void start(View view) throws ParseException{
        String string = timerView.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss" ,Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        milliSec = dateFormat.parse(string).getTime();
        Log.i("Time", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(milliSec)));
        Log.i("TimeZone", String.valueOf(dateFormat));
        timer((milliSec),timerView);
        timerSeekBar.setMax(milliSec.intValue());
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void timer(long millisec, final TextView tv) {

        new CountDownTimer(millisec, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);

                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);

                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));

                timerSeekBar.setProgress((milliSec.intValue() - (int)millisUntilFinished));
            }

            public void onFinish() {
                tv.setText("00:00");
            }
        }.start();
    }
}
