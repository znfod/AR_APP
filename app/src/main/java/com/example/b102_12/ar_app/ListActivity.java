package com.example.b102_12.ar_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import Point.DoublePoint;
import Point.FloatPoint;
import waypoint.Cafe;
import waypoint.ConvenienceStore;
import waypoint.Medical;
import waypoint.Restaurant;


public class ListActivity extends Activity {
    PublicDataClass pdc;
    ArrayList<Cafe> cafeList;
    ArrayList<Medical> MedicalList;
    ArrayList<Restaurant> RestaurantList;
    ArrayList<ConvenienceStore> ConvenienceList;

    ListView listView;
    TextListAdapter adapter;

    DoublePoint currentPoint;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 0); //뒤에는 기본값
        Log.i("List", "NUMBER >> " + number);

        pdc = new PublicDataClass();
        currentPoint = pdc.getCurrentXY();

        if (number == 1) {
            //지하철

        } else if (number == 2) {
            //편의점
            ConvenienceList = new ArrayList<ConvenienceStore>();
            ConvenienceList = pdc.getConvenienceList();
            adapter = new TextListAdapter(this);
            for (int i = 0; i < ConvenienceList.size(); i++) {
                double x = currentPoint.getX() - ConvenienceList.get(i).getX();
                double y = currentPoint.getY() - ConvenienceList.get(i).getY();
                float temp = (float) Math.sqrt((x * x) + (y * y));
                int tempMiter = (int) temp;

                String miter;
                if(tempMiter > 1000) {
                    miter = (tempMiter/1000) + "KM";
                } else {
                    miter = tempMiter + "M";
                }
                adapter.addItem(new IconTextItem(ConvenienceList.get(i).getName(), miter));
            }
        } else if (number == 3) {
            //카페
            cafeList = new ArrayList<Cafe>();
            cafeList = pdc.getCafeList();
            adapter = new TextListAdapter(this);
            for (int i = 0; i < cafeList.size(); i++) {
                double x = currentPoint.getX() - cafeList.get(i).getX();
                double y = currentPoint.getY() - cafeList.get(i).getY();
                float temp = (float) Math.sqrt((x * x) + (y * y));
                int tempMiter = (int) temp;
                String miter;
                if(tempMiter > 1000) {
                    miter = (tempMiter/1000) + "KM";
                } else {
                    miter = tempMiter + "M";
                }
                adapter.addItem(new IconTextItem(cafeList.get(i).getName(), miter));

            }
        } else if (number == 4) {
            //의료
            MedicalList = new ArrayList<Medical>();
            MedicalList = pdc.getMedicalList();
            adapter = new TextListAdapter(this);
            for (int i = 0; i < MedicalList.size(); i++) {
                double x = currentPoint.getX() - MedicalList.get(i).getX();
                double y = currentPoint.getY() - MedicalList.get(i).getY();
                float temp = (float) Math.sqrt((x * x) + (y * y));
                int tempMiter = (int) temp;
                String miter;
                if(tempMiter > 1000) {
                    miter = (tempMiter/1000) + "KM";
                } else {
                    miter = tempMiter + "M";
                }
                adapter.addItem(new IconTextItem(MedicalList.get(i).getName(), miter));
            }
        } else if (number == 5) {
            //음식
            RestaurantList = new ArrayList<Restaurant>();
            RestaurantList = pdc.getRestaurantList();
            adapter = new TextListAdapter(this);
            for (int i = 0; i < RestaurantList.size(); i++) {
                double x = currentPoint.getX() - RestaurantList.get(i).getX();
                double y = currentPoint.getY() - RestaurantList.get(i).getY();
                float temp = (float) Math.sqrt((x * x) + (y * y));
                int tempMiter = (int) temp;
                String miter;
                if(tempMiter > 1000) {
                    miter = (tempMiter/1000) + "KM";
                } else {
                    miter = tempMiter + "M";
                }
                adapter.addItem(new IconTextItem(RestaurantList.get(i).getName(), miter));
            }
        } else {

        }
        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mItemClickListener);

    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long l_position) {
            Intent intent = new Intent(ListActivity.this, TabHostActivity.class);
            intent.putExtra("number", number);
            intent.putExtra("position", position);
            startActivity(intent);

        }
    };


}
