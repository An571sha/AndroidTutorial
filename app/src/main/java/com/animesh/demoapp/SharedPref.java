package com.animesh.demoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.IOException;
import java.util.ArrayList;

public class SharedPref extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.sharedpref);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.animesh.demoapp", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username","firstUser");

        String username = sharedPreferences.getString("username","");

        Log.i("UserName",username);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("A");
        friends.add("B");
        friends.add("C");
        friends.add("D");
        friends.add("E");
        friends.add("F");
        friends.add("G");

        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> storedFriends = new ArrayList<String>();

        try {
            storedFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("List",storedFriends.toString());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

}
