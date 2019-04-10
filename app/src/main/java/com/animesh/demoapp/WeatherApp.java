package com.animesh.demoapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.InflaterInputStream;

public class WeatherApp extends AppCompatActivity {
    private List<String> spinnerArray;
    private Spinner sItems;
    private ArrayAdapter<String> adapter;
    private String myUrl;
    static TextView descriptionTextView;
    static TextView TempTextView;

    public void initHtml() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("weather")
                .appendQueryParameter("appid", "27e682964d2d1a0405b1fc6ab4d195d8")
                .appendQueryParameter("q", String.valueOf(sItems.getSelectedItem()))
                .appendQueryParameter("units", "metric");
        myUrl = builder.build().toString();

        GetHtml getHtml = new GetHtml();
        try {
            String result = getHtml.execute(myUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.weather);
        descriptionTextView = (TextView) findViewById(R.id.description);
        TempTextView = (TextView) findViewById(R.id.textView5);

        spinnerArray = new ArrayList<String>();
        spinnerArray.add("Kaunas");
        spinnerArray.add("Berlin");
        spinnerArray.add("Bern");
        spinnerArray.add("Mumbai");
        spinnerArray.add("Patna");
        spinnerArray.add("London");

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initHtml();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public static class GetHtml extends AsyncTask<String, Void, String> {

        URL url;
        String result = "";
        HttpURLConnection urlConnection = null;
        JsonObject jsonobj = null;
        String description = "";
        String temp = "";

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStream out = (InputStream) urlConnection.getContent();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                //  Log.i("API REQUEST START-data-", String.valueOf(data));


                try {
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) url.getContent()));
                    //  Log.i("ROOT", String.valueOf(root));
                    if (root instanceof JsonObject) {
                        jsonobj = root.getAsJsonObject();
                        //    Log.i("JsonObj", String.valueOf(jsonobj));
                        description = jsonobj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").toString();
                        //   Log.i("desc", String.valueOf(description));
                        temp = jsonobj.get("main").getAsJsonObject().get("temp").toString();
                    }

                } catch (IOException ix) {
                    ix.printStackTrace();
                }

                return description;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException ilg) {
                ilg.printStackTrace();
            }

            return description;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (description != null && temp != null) {
                /*descriptionTextView.setText(description);
                TempTextView.setText(temp + " "+"C");
                */

            }
        }

        }

    }


