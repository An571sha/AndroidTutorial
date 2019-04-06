package com.animesh.demoapp;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private ExchangeRates exchangeRates;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText) findViewById(R.id.input);
        textView = (TextView) findViewById(R.id.output);
    }


    public void show(View view) throws IOException {
        exchangeRates = new ExchangeRates();
        exchangeRates.execute("https://v3.exchangerate-api.com/bulk/0b4f1f05f026c867d946ac76/USD");
//        Log.i("PRINT_EXCHANGE_RATE",exchangeRates.req_result);


    }

    public void onRadioButtonClicked(View view) throws IOException {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.dirham:
                if(checked){
                    editText.setText("AUD",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.pound:
                if(checked){
                    editText.setText("GBP",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.euros:
                if(checked){
                    editText.setText("EUR",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.franken:
                if(checked){
                    editText.setText("CHF",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.dollars:
                if(checked){
                    editText.setText("USD",TextView.BufferType.EDITABLE);
                }
                break;
            case R.id.rupee:
                if(checked){
                    editText.setText("INR",TextView.BufferType.EDITABLE);
                }
                break;
        }


    }



    public class ExchangeRates extends AsyncTask<String,Void,String> {
        String url_str = "https://v3.exchangerate-api.com/bulk/0b4f1f05f026c867d946ac76"+"/"+ editText.getText();

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

            req_result = jsonobj.get("rates").toString();
            String[] parts = req_result.split(",");
           // req_result = parts[1].trim();

            return req_result;

        }

        protected void onPostExecute(String req_result) {
            textView.setText(exchangeRates.req_result, TextView.BufferType.EDITABLE);
        }


    }


}
