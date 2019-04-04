package com.animesh.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

public class TimesTable extends AppCompatActivity {

    private SeekBar numberControl;
    private String[] numbers = {"1","2","3","4","5","6","7","8","9","10"};
    ArrayAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timestable);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,numbers);
        listView = findViewById(R.id.numberView);
        listView.setAdapter(adapter);
        numberControl = (SeekBar) findViewById(R.id.numberSeek);
        numberControl.setMax(10);
        numberControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > 0) {
                    Log.i("SeekBar", Integer.toString(i));
                    for (int j = 0; j < numbers.length; j++) {
                        numbers[j] = Integer.toString(j * i);
                    }
                    listView.setAdapter(adapter);

                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
