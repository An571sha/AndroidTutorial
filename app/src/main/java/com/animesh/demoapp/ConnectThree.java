package com.animesh.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class ConnectThree extends AppCompatActivity {
    ImageView board;
    ImageView blockOne;
    ImageView blockTwo;
    ImageView blockThree;
    ImageView blockFour;
    ImageView blockFive;
    ImageView blockSix;
    ImageView blockSeven;
    ImageView blockEight;
    ImageView blockNine;
    int activePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connectthree);
         board = (ImageView) findViewById(R.id.board);
         blockOne = (ImageView) findViewById(R.id.red1);
         blockTwo = (ImageView) findViewById(R.id.red2);
         blockThree = (ImageView) findViewById(R.id.red3);
         blockFour = (ImageView) findViewById(R.id.red4);
         blockFive = (ImageView) findViewById(R.id.red5);
         blockSix = (ImageView) findViewById(R.id.red6);
         blockSeven = (ImageView) findViewById(R.id.red7);
         blockEight = (ImageView) findViewById(R.id.red8);
         blockNine = (ImageView) findViewById(R.id.red9);
         activePlayer  = 0;
    }

    public void redClick(View view) {


        ImageView redView = (ImageView) view;

        if(activePlayer == 0){

            redView.setImageResource(R.drawable.yellow);
            redView.setEnabled(false);
            final Toast toast1 = Toast.makeText(getApplicationContext(),"Player 2",Toast.LENGTH_SHORT);
            toast1.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast1.cancel();
                }
            }, 500);

            activePlayer = 1;

        }else if (activePlayer == 1){

            redView.setImageResource(R.drawable.red);
            redView.setEnabled(false);
            final Toast toast2 = Toast.makeText(getApplicationContext(),"Player 1 ",Toast.LENGTH_SHORT);
            toast2.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast2.cancel();
                }
            }, 500);
            activePlayer = 0;
        }
    }


}
