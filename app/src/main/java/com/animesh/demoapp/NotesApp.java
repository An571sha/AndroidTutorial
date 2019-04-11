package com.animesh.demoapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import java.util.TreeSet;

public class NotesApp extends AppCompatActivity {

    ListView listView;
    static  ArrayAdapter<String> arrayAdapter;
    static ArrayList<String> notesArrayList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.notesList);
        notesArrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notesArrayList);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.animesh.demoApp", 0);
        TreeSet<String> treeSet =(TreeSet<String>) sharedPreferences.getStringSet("notes",null);
        if(treeSet == null){
            notesArrayList.add("Example Note");
        }else{
            notesArrayList = new ArrayList<>(treeSet);
        }

        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("noteId",i);
                startActivityForResult(intent,1);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                if (i != -1) {
                    AlertDialog.Builder delete_entry = new AlertDialog.Builder(NotesApp.this)
                            .setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?");

                    delete_entry.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            notesArrayList.remove(i);
                            listView.invalidateViews();
                            arrayAdapter.notifyDataSetChanged();
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.animesh.demoapp",0);
                            TreeSet<String> treeSet = new TreeSet<>(NotesApp.notesArrayList);
                            sharedPreferences.edit().putStringSet("notes",treeSet).apply();
                        }
                    })

                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    return true;

                }
                return false;
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

/*    @Override
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
    }*/
}
