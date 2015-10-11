package com.example.b102_12.ar_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import CalculUTMK.CalculUTMK;
import Dijkstra.Dijkstra;
import Point.DoublePoint;
import Point.FloatPoint;
import Scanner.CSVScanner;
import nodedata.NodeData;
import waypoint.Cafe;
import waypoint.ConvenienceStore;
import waypoint.Medical;
import waypoint.Restaurant;

public class StartActivity extends Activity {

    PublicDataClass pdc;
    Dijkstra dijkstra;

    /* Location GPS */

    float currentX;
    float currentY;

    private String locationProvider = null;

    private LocationManager locationManager;

    CalculUTMK calutmk;

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startBtn = (Button)findViewById(R.id.idstart);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(StartActivity.this, GridActivity.class);
            startActivity(intent);
            }
        });
        startBtn.setEnabled(false);

        pdc = new PublicDataClass();
        //노드 편의점 의료 식당 카페
        getNode();
        getConvenience();
        getMedical();
        getRestaurant();
        getCafe();

        dijkstra = new Dijkstra(pdc.getDataList());
        calutmk = new CalculUTMK();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER) == true) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            locationProvider = LocationManager.GPS_PROVIDER;
        }
        LocationListener locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                currentX = (float)location.getLongitude();//경도 X
                currentY = (float)location.getLatitude(); //위도 Y
                DoublePoint fp = calutmk.getXY(currentX, currentY);
                pdc.setCurrentXY(fp);
                startBtn.setEnabled(true);
                Log.i("START","PDC SUCCESS");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        //GPS받기 끝


    }
    private void getNode() {
        try {
            //노드!!
            InputStream is = getResources().openRawResource(R.raw.csfiles);
            CSVScanner csvScan = new CSVScanner(is, 1);
            ArrayList<NodeData> dataList = new ArrayList<NodeData>();
            dataList = csvScan.getNodeData();
            pdc.setDataList(dataList);
            Log.i("Activity", "dataListSize" + dataList.size());
            csvScan = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getConvenience() {
        try {
            //편의점!!
            InputStream is = getResources().openRawResource(R.raw.convi);
            CSVScanner csvScan = new CSVScanner(is, 2);
            ArrayList<ConvenienceStore> convenienceList = new ArrayList<ConvenienceStore>();
            convenienceList = csvScan.getConvenienceArray();
            pdc.setConvenienceList(convenienceList);
            Log.i("Activity", "convenienceListSize" + convenienceList.size());
            csvScan = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getMedical() {
        try {
            //의료!!
            InputStream is = getResources().openRawResource(R.raw.medical);
            CSVScanner csvScan = new CSVScanner(is, 3);
            ArrayList<Medical> medicalList = new ArrayList<Medical>();
            medicalList = csvScan.getMedicalArray();
            pdc.setMedicalList(medicalList);
            Log.i("Activity", "medicalListSize" + medicalList.size());
            csvScan = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getRestaurant() {
        try {
            //식당!!
            InputStream is = getResources().openRawResource(R.raw.restaurant);
            CSVScanner csvScan = new CSVScanner(is, 4);
            ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
            restaurantList = csvScan.getRestaurantArray();
            pdc.setRestaurantList(restaurantList);
            Log.i("Activity", "restaurantListSize" + restaurantList.size());
            csvScan = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getCafe() {
        //카페!!
        try {
            InputStream is = getResources().openRawResource(R.raw.cafe);
            CSVScanner csvScan = new CSVScanner(is, 5);
            ArrayList<Cafe> cafeList = new ArrayList<Cafe>();
            cafeList = csvScan.getCafeArray();
            pdc.setCafeList(cafeList);
            Log.i("Activity", "cafeListSize" + cafeList.size());
            csvScan = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
