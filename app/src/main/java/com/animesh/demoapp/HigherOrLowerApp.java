package com.animesh.demoapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Random;


public class HigherOrLowerApp extends AppCompatActivity {
    private int randomNumber;
    private String input;
    private int number;


    public void button(View view){
        EditText editText = (EditText) findViewById(R.id.textView3);
        input = editText.getText().toString();
        number = Integer.parseInt(input);
            if(number > randomNumber){
                Toast.makeText(getApplicationContext(),"Go lower",Toast.LENGTH_SHORT).show();
            }if(number < randomNumber) {
                Toast.makeText(getApplicationContext(), "Go Higher", Toast.LENGTH_SHORT).show();
            }if(number == randomNumber ){
                Toast.makeText(getApplicationContext(), "YAY You Found it", Toast.LENGTH_SHORT).show();
            }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.higherorlower);
        randomNumber = new Random().nextInt(20) + 1;


    }


}
