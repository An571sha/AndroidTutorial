package com.animesh.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    ExchangeRates exchangeRates = new ExchangeRates();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ExchangeRates();
    }


    public void onRadioButtonClicked(View view) throws IOException {

        EditText editText =(EditText) findViewById(R.id.input);
        EditText textView =(EditText) findViewById(R.id.output);
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.yen:
                if(checked){
                  //  textView.setText(exchangeRates.req_result.substring(0,15), TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.pound:
                if(checked){
                    textView.setText("pound",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.euros:
                if(checked){
                    textView.setText("euros",TextView.BufferType.EDITABLE);
                }
                break;
        }


    }

}
