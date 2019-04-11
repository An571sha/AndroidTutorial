package com.animesh.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.TreeSet;

public class EditActivity extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        editText = (EditText) findViewById(R.id.editNote);
        Intent intent = getIntent();

        final int noteId = intent.getIntExtra("noteId",-1);
        if (noteId != -1){
            editText.setText(NotesApp.notesArrayList.get(noteId));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NotesApp.notesArrayList.set(noteId,String.valueOf(charSequence));
                NotesApp.arrayAdapter.notifyDataSetChanged();
             //   NotesApp.entryArrayList.set(noteId,String.valueOf(charSequence));
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.animesh.demoapp",0);
                TreeSet<String> treeSet = new TreeSet<>(NotesApp.notesArrayList);
                sharedPreferences.edit().putStringSet("notes",treeSet).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }


/*    public void onBackPressed() {
        Intent intent = new Intent();
        Log.i("EntryBefore", editText.getText().toString());
        intent.putExtra("Entry", editText.getText().toString());
        String s = intent.getStringExtra("Entry");
        Log.i("EntryBefore", s);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }*/
}
