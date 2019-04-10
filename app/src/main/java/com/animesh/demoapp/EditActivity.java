package com.animesh.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        editText = (EditText) findViewById(R.id.editNote);


    }


    public void onBackPressed() {
        Intent intent = new Intent();
        Log.i("EntryBefore", editText.getText().toString());
        intent.putExtra("Entry", editText.getText().toString());
        String s = intent.getStringExtra("Entry");
        Log.i("EntryBefore", s);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
