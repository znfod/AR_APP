package com.example.b102_12.ar_app;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Znfod on 2015-05-11.
 */
public class IconTextView extends LinearLayout {

    TextView placeNameText;
    TextView distanceText;
    Button listButton;
    public IconTextView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wtlistitem, this, true);

        placeNameText = (TextView)findViewById(R.id.textplacename);

        distanceText = (TextView)findViewById(R.id.textdistance);

    }
    public IconTextView(Context context, IconTextItem aItem) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wtlistitem, this, true);

        placeNameText = (TextView)findViewById(R.id.textplacename);
        placeNameText.setText(aItem.getData(0));

        distanceText = (TextView)findViewById(R.id.textdistance);
        distanceText.setText(aItem.getData(1));

    }
    public void setText(int index, String data) {
        if(index == 0) {
            placeNameText.setText(data);
        } else if(index == 1) {
            distanceText.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
