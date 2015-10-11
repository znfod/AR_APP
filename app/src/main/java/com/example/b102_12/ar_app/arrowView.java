package com.example.b102_12.ar_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by B102-12 on 2015-06-01.
 */
public class arrowView extends LinearLayout {

    ImageView arrowiv;
    TextView arrowtv;

    public arrowView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.arrowlayout, this, true);
        arrowiv = (ImageView)findViewById(R.id.arrowimg);
        arrowtv = (TextView)findViewById(R.id.arrowdis);



    }

}
