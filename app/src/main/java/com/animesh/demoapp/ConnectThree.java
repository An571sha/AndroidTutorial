package com.animesh.demoapp;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
    ImageView redView;
    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;
    Bitmap bitmap4;
    Bitmap bitmap5;
    Bitmap bitmap6;
    Bitmap bitmap7;
    Bitmap bitmap8;
    Bitmap bitmap9;
    AlertDialog.Builder dlgAlert;

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
        play(view);
      /*  if(blockOne.equals(blockTwo) && blockOne.equals(blockThree)){
            Toast.makeText(getApplicationContext(),"Player has won",Toast.LENGTH_SHORT).show();
        }*/
        if((blockOne.getDrawable() != null && blockTwo.getDrawable() != null && blockThree.getDrawable() !=null)) {
            bitmap1 = ((BitmapDrawable) blockOne.getDrawable()).getBitmap();
            bitmap2 = ((BitmapDrawable) blockTwo.getDrawable()).getBitmap();
            bitmap3 = ((BitmapDrawable) blockThree.getDrawable()).getBitmap();

            if (bitmap1 == bitmap2 && bitmap1 == bitmap3) {
                Toast.makeText(getApplicationContext(), "Player has won", Toast.LENGTH_SHORT).show();
                dgAlert();
            }

        } else if((blockFour.getDrawable() != null && blockFive.getDrawable() != null && blockSix.getDrawable() !=null)) {
            bitmap4 = ((BitmapDrawable) blockFour.getDrawable()).getBitmap();
            bitmap5 = ((BitmapDrawable) blockFive.getDrawable()).getBitmap();
            bitmap6 = ((BitmapDrawable) blockSix.getDrawable()).getBitmap();

            if (bitmap4 == bitmap5 && bitmap4 == bitmap6) {
                Toast.makeText(getApplicationContext(), "Player has won", Toast.LENGTH_SHORT).show();
                dgAlert();
            }
        } else if( (blockSeven.getDrawable() != null && blockEight.getDrawable() != null && blockNine.getDrawable() !=null)){
            bitmap7 = ((BitmapDrawable) blockSeven.getDrawable()).getBitmap();
            bitmap8 = ((BitmapDrawable) blockEight.getDrawable()).getBitmap();
            bitmap9 = ((BitmapDrawable) blockNine.getDrawable()).getBitmap();

            if (bitmap7 == bitmap8 && bitmap7 == bitmap9 ) {
                Toast.makeText(getApplicationContext(),"Player has won",Toast.LENGTH_SHORT).show();
                dgAlert();
            }
        }
    }

    public void dgAlert(){
        Toast.makeText(getApplicationContext(), "Player has won", Toast.LENGTH_SHORT).show();
        dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Press ok to restart");
        dlgAlert.setTitle("3 in Row");
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }



    public void play(View view){
        redView = (ImageView) view;

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
