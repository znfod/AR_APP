package com.example.b102_12.ar_app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;


public class TabHostActivity extends TabActivity {
    PublicDataClass pdc;

    int number;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 0);
        position = intent.getIntExtra("position", 0);
        Log.i("tabhost", "number position >> " + number + "/" + position);

        final TabHost tabHost = getTabHost();

        Intent cameraIntent = new Intent(this, CameraActivity.class);
        cameraIntent.putExtra("number",number);
        cameraIntent.putExtra("position",position);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("camera").setContent(cameraIntent));

        Intent naverMapIntent = new Intent(this, NaverMapActivity.class);
        naverMapIntent.putExtra("number",number);
        naverMapIntent.putExtra("position",position);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("map").setContent(naverMapIntent));

        setContentView(tabHost);
        tabHost.setCurrentTab(1);

    }


}
