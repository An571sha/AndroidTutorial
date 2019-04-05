package com.animesh.demoapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BrainTeaserApp extends AppCompatActivity implements View.OnClickListener{
    int firstRandNumber = 0;
    int secondRandomNumber = 0;
    int randomResult1 = 0;
    int randomResult2 = 0;
    int randomResult3 = 0;
    int max = 30;
    int min = 10;
    int sum = 0;
    TextView topMiddle;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    TextView topLeft;
    TextView topRight;
    Button button;
    TextView score;
    List<Integer> resultOrder;
    CountDownTimer countDownTimer;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brainteaser);
        generateRandom();
        topMiddle = (TextView) findViewById(R.id.topMiddle);
        topRight = (TextView) findViewById(R.id.topRight);
        topLeft = (TextView) findViewById(R.id.topLeft);
        score = (TextView)findViewById(R.id.score);
        button = (Button) findViewById(R.id.play);
        button.setOnClickListener(this);
        option1 = (TextView) findViewById(R.id.option1);
        option1.setOnClickListener(this);
        option2 = (TextView) findViewById(R.id.option2);
        option2.setOnClickListener(this);
        option3 = (TextView) findViewById(R.id.option3);
        option3.setOnClickListener(this);
        option4 = (TextView) findViewById(R.id.option4);
        option4.setOnClickListener(this);
        topMiddle.setText(new StringBuilder().append(firstRandNumber).append(" + ").append(secondRandomNumber).toString());
        resultOrder = new ArrayList<Integer>();
        fillResultOrderList();
        fillOptionTextView();
        timer(600000);
        topRight.setText("Your Score");

    }

    @Override
    public void onClick(View view) {
        option1.setSelected(true);
        option2.setSelected(true);
        option3.setSelected(true);
        option4.setSelected(true);

        if (option1.isSelected() && option1.getText() == String.valueOf(sum)) {
            counter++;
            topRight.setText(String.valueOf(counter));
            score.setText("Right");
            resultOrder.clear();
            intialiseTheThreeMethods();

        } else if(option2.isSelected() && option2.getText() == String.valueOf(sum)) {
            counter++;
            topRight.setText(String.valueOf(counter));
            score.setText("Right");
            resultOrder.clear();
            intialiseTheThreeMethods();

        }else if(option3.isSelected() && option3.getText() == String.valueOf(sum)) {
            counter++;
            topRight.setText(String.valueOf(counter));
            score.setText("Right");
            resultOrder.clear();
            intialiseTheThreeMethods();

        }else if(option4.isSelected() && option4.getText() == String.valueOf(sum)) {
            counter++;
            topRight.setText(String.valueOf(counter));
            score.setText("Right");
            resultOrder.clear();
            intialiseTheThreeMethods();
        }else{

            score.setText("Wrong");
        }
    }




      /*  switch (view.getId()) {
            case R.id.option1:
                resultOrder.clear();
                intialiseTheThreeMethods();
                if(option1.getText() == String.valueOf(sum)){

                }
                Log.i("sum", String.valueOf(sum));
                select();

                break;

            case R.id.option2:
                resultOrder.clear();
                intialiseTheThreeMethods();
                Log.i("sum", String.valueOf(sum));
                select();
                // }
                break;

            case R.id.option3:
                resultOrder.clear();
                intialiseTheThreeMethods();
                Log.i("sum", String.valueOf(sum));
                select();

                //  }
                break;

            case R.id.option4:
                resultOrder.clear();
                intialiseTheThreeMethods();
                Log.i("sum", String.valueOf(sum));
                select();

                //   }
                break;

            case R.id.button:
                resultOrder.clear();
                intialiseTheThreeMethods();
                timer(60000);
                counter = 0;
        }*/


    public void fillResultOrderList() {
        resultOrder.add(sum);
        resultOrder.add(randomResult1);
        resultOrder.add(randomResult2);
        resultOrder.add(randomResult3);

        Collections.shuffle(resultOrder);


    }


    public void fillOptionTextView(){

        option1.setText(resultOrder.get(0).toString());
        option2.setText(resultOrder.get(1).toString());
        option3.setText(resultOrder.get(2).toString());
        option4.setText(resultOrder.get(3).toString());



    }


    public void timer(long millisec){
        countDownTimer = new CountDownTimer(millisec, 1000) {
            @Override
            public void onTick(long l) {

                int seconds = (int) (l / 1000);
                topLeft.setText(String.valueOf(seconds) +" "+"s");

            }
            @Override
            public void onFinish() {
                topLeft.setText("0");
                topMiddle.setText("Press Restart");
                option1.setText("Game Over");
                option2.setText("Game Over");
                option3.setText("Game Over");
                option4.setText("Game Over");

            }
        }.start();
    }

    public void generateRandom(){

        firstRandNumber = new Random().nextInt((max-min)+1) +min;
        secondRandomNumber = new Random().nextInt((max-min)+1) +min;
        randomResult1 = (int) (Math.random() * ((max - min) + 10) + min);
        randomResult2 = (int) (Math.random() * ((max - min) + 2) + min);
        randomResult3 = (int) (Math.random() * ((max - min) + 8) + min);
        sum = firstRandNumber + secondRandomNumber;


    }


    public void intialiseTheThreeMethods(){
        generateRandom();
        fillResultOrderList();
        fillOptionTextView();
        topMiddle.setText(new StringBuilder().append(firstRandNumber).append(" + ").append(secondRandomNumber).toString());
    }


    public void select(){

    }


}
