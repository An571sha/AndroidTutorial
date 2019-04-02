package com.animesh.demoapp;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeRates extends AsyncTask<String,Void,String> {
    String url_str = "https://v3.exchangerate-api.com/bulk/0b4f1f05f026c867d946ac76/USD";
    String req_result = null;
    URL url = null;
    JsonObject jsonobj = null;
    HttpURLConnection request = null;

    @Override
    protected String doInBackground(String... strings) {

        Log.i("API REQUEST START", url_str);

        try {
            url = new URL(url_str);
            request = (HttpURLConnection) url.openConnection();
            request.connect();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (NetworkOnMainThreadException no) {
            no.printStackTrace();
        }

        Log.i("API REQUEST END", request.toString());

        try {
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) url.getContent()));
            jsonobj = root.getAsJsonObject();

        } catch (IOException ix) {
            ix.printStackTrace();
        }

        req_result = jsonobj.get("result").getAsString();

        return req_result;

    }

    protected void onPostExecute(String req_result) {
        Log.i("PRINT",req_result);
    }


}
