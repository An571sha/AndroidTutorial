package com.animesh.demoapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    private android.os.CountDownTimer countDownTimer;
    private static final String FORMAT = "%02d:%02d";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);
        timerView = (EditText) findViewById(R.id.timerView);
    }


    public void start(View view) throws ParseException{
        String string = timerView.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        double min = dateFormat.parse(string).getTime()/1000.0/60;


        // Toast.makeText(getApplicationContext(), (int) date.getTime(), Toast.LENGTH_SHORT).show();
        Log.i("Time", String.valueOf(min));

        new CountDownTimer(dateFormat.parse(string).getTime() , 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf((millisUntilFinished/1000)));
              /*  timerView.setText(""+ String.format(FORMAT, TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished/1000),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished/1000)));*/


            }

            public void onFinish() {
                timerView.setText("done!");
            }

        }.start();

    }
}
