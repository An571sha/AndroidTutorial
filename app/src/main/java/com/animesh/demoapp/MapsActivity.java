package com.animesh.demoapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    GoogleMap mMap;
    LocationManager myLocation;
    LocationListener locationListener;
    double longitude;
    double latitude;
    double altitude;
    Geocoder geocoder;
    List<Address> addressArrayList;
    TextView latitudeTextview;
    TextView longitudeTextview;
    TextView altitudeTextview;
    TextView addressTextView;
    TextView weatherTextView;
    Marker marker;
    String myUrl;
    String weatherDescription;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            myLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0, locationListener);
            myLocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 0, locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitudeTextview = (TextView) findViewById(R.id.latitude);
        longitudeTextview = (TextView) findViewById(R.id.longitude);
        altitudeTextview = (TextView) findViewById(R.id.altitude);
        addressTextView = (TextView) findViewById(R.id.address);
        weatherTextView = (TextView) findViewById(R.id.weather);



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        myLocation = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        mMap.setOnMapLongClickListener(this);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                updateLocation(location);
                Log.i("Location", location.toString());

                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    addressArrayList = geocoder.getFromLocation(latitude,longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(addressArrayList.size() > 0){
                   Log.i("Adress",addressArrayList.get(0).toString());
                   updateTextView(latitude,longitude);
                   setWeatherForcast(latitude,longitude);
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            mMap.clear();
            myLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 100, locationListener);
            myLocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 100, locationListener);
            Location lastKnownLocation = myLocation.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updateLocation(lastKnownLocation);



        }
    }

    public void updateTextView(double latitude, double longitude){
        if(latitude != 0 ){
          //  Log.i("Location",String.valueOf(latitude) );
            latitudeTextview.setText(String.valueOf("Latitude:"+ latitude));
        }else{
            latitudeTextview.setText(String.valueOf("No data"));
        }

        if(longitude !=0) {
          //  Log.i("Location",String.valueOf(longitude) );
            longitudeTextview.setText(String.valueOf("longitude:"+longitude));
        }else{
            longitudeTextview.setText(String.valueOf("No data"));

        }

        if(altitude !=0){
            altitudeTextview.setText(String.valueOf("altitude:"+ altitude));

        }else{
            altitudeTextview.setText(String.valueOf("No data"));

        }

        if(addressArrayList.get(0).getAddressLine(0) != null){
            addressTextView.setText(new StringBuilder().append(addressArrayList.get(0)
                    .getAddressLine(0))
                    .append("\n").toString());
        }

    }


    public void updateLocation(Location location){
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        altitude = location.getAltitude();
        LatLng delhi = new LatLng(latitude,longitude);
        marker = mMap.addMarker(new MarkerOptions().position(delhi).title("My location").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi));

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title("My location").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        try {
            addressArrayList = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addressArrayList.size() > 0){
            Log.i("Adress",addressArrayList.get(0).toString());
            updateTextView(latLng.latitude,latLng.longitude);
        }

        setWeatherForcast(latLng.latitude,latLng.longitude);

    }

    public void setWeatherForcast(double latitude, double longitude){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("weather")
                .appendQueryParameter("appid", "27e682964d2d1a0405b1fc6ab4d195d8")
                .appendQueryParameter("lat",String.valueOf(latitude))
                .appendQueryParameter("lon",String.valueOf(longitude))
                .appendQueryParameter("units", "metric");
        myUrl = builder.build().toString();
        WeatherApp.GetHtml getWeather = new WeatherApp.GetHtml();
        try {
            weatherDescription = getWeather.execute(myUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("Description",weatherDescription);
        weatherTextView.setText(weatherDescription);


    }
}
