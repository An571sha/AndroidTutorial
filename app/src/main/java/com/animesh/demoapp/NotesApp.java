package com.animesh.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class NotesApp extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> notesArrayList;
    ArrayList<String> entryArrayList;
    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sharedPreferences = this.getSharedPreferences("com.animesh.demoApp", 0);

        EditActivity editActivity = new EditActivity();
        entryArrayList =new ArrayList<>();

        listView = (ListView) findViewById(R.id.notesList);
        notesArrayList = new ArrayList<String>();
        notesArrayList.add("Example Note");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notesArrayList);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getApplicationContext(), EditActivity.class);
                startActivityForResult(intent,1);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        if (item.getItemId() == R.id.addNotes) {
            notesArrayList.add("New Entry");
            listView.setAdapter(arrayAdapter);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                entryArrayList.add("Entry");
                String entry = data.getStringExtra("Entry");
                entryArrayList.add(entry);
                Log.i("Entry", entryArrayList.toString());
                try {
                    sharedPreferences.edit().putString("entryArrayList", ObjectSerializer.serialize(entryArrayList)).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
