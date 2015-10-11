package com.example.b102_12.ar_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Znfod on 2015-04-15.
 */
public class CustomButtonView extends LinearLayout {
    TextView nametext;
    TextView distancetext;
    ImageButton imageButton;
    String[] star = new String[6];
    String[] phones = new String[10];
    String[] name = new String[10];
    Dialog dialog;
    ImageView iv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    public CustomButtonView(final Context context , final int number, final String placeName) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customviewlayout, this, true);
        star[0] = "☆☆☆☆☆";
        star[1] = "★☆☆☆☆";
        star[2] = "★★☆☆☆";
        star[3] = "★★★☆☆";
        star[4] = "★★★★☆";
        star[5] = "★★★★★";
        phones[0] = "02-747-8071";
        phones[1] = "";
        phones[2] = "";
        phones[3] = "02-743-3623";
        phones[4] = "";
        phones[5] = "";
        phones[6] = "";
        phones[7] = "02-766-8757";
        phones[8] = "02-3675-3921";
        phones[9] = "02-744-7767";
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        iv = (ImageView)dialog.findViewById(R.id.dia_img);
        tv1 = (TextView)dialog.findViewById(R.id.dia_name);
        tv2 = (TextView)dialog.findViewById(R.id.dia_phone);
        tv3 = (TextView)dialog.findViewById(R.id.dia_infor);

        nametext = (TextView)findViewById(R.id.custom_name);
        distancetext =  (TextView)findViewById(R.id.custom_distance);
        imageButton =  (ImageButton)findViewById(R.id.custom_image);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기에 다이얼로그 정리

                tv1.setText(placeName);

                dialog.show();

            }
        });

    }
    public void setNametext(String name) {
        nametext.setText(name);
    }
    public void setDistancetext(String distance) {
        distancetext.setText(distance);
    }
    public void setImage(String name, int number) {
        if(name.equals("CU성북한성대점")) {
            tv2.setText(phones[9]);
        } else if(name.equals("세븐일레븐 성북한성대역점")) {
            tv2.setText(phones[0]);
        }else if(name.equals("GS25 동소문중앙점")) {
            tv2.setText(phones[3]);
        }else if(name.equals("세븐일레븐 한성대사랑점")) {
            tv2.setText(phones[7]);
        }else if(name.equals("GS25한성대점")) {
            tv2.setText(phones[8]);
        }

        if(number == 0) {

        }else if(number == 1) {

        }
        else if(number == 2) {
            String str = name.substring(0,2);
            Log.i("CustomView ","str >> "+"["+str+"]");
            if(str.equals("CU") ) {
                imageButton.setImageResource(R.drawable.culogo);
                iv.setImageResource(R.drawable.culogo);

                tv3.setText(star[4]);
            } else if(str.equals("GS")) {
                imageButton.setImageResource(R.drawable.gs25logo);
                iv.setImageResource(R.drawable.gs25logo);
                tv3.setText(star[4]);
            } else if(str.equals("세븐")) {
                imageButton.setImageResource(R.drawable.sevenlogo);
                iv.setImageResource(R.drawable.sevenlogo);
                tv3.setText(star[2]);
            } else {

            }
        }
        else if(number == 3) {
            String str = name.substring(0,1);
            if(str.equals("할리")) {
                imageButton.setImageResource(R.drawable.hollyslogo);
            }
        }

    }
    //이미지 설정

/*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        int height = 0;

        if(widthMode == MeasureSpec.UNSPECIFIED) { //Mode가 설정되지 않았을 경우. 소스상에서 직접 넣었을때
            width = widthMeasureSpec;
            Log.i("custom view","UNSPECIFIED");
        }
        if(widthMode == MeasureSpec.AT_MOST){ //사이즈가 정해지지 않았을 때
            width = 50;
            Log.i("custom view","AT_MOST");
        }
        if(widthMode == MeasureSpec.EXACTLY){ //fill_parent나 match_parent로 설정이 될 경우
            width = MeasureSpec.getSize(widthMeasureSpec);
            Log.i("custom view","EXACTLY");
        }

        if(heightMode == MeasureSpec.UNSPECIFIED) {//Mode가 설정되지 않았을 경우. 소스상에서 직접 넣었을때
            height = heightMeasureSpec;
            Log.i("custom view","UNSPECIFIED");
        }
        if(heightMode == MeasureSpec.AT_MOST){ //사이즈가 정해지지 않았을 때
            height = 50;
            Log.i("custom view","AT_MOST");
        }
        if(heightMode == MeasureSpec.EXACTLY) {//fill_parent나 match_parent로 설정이 될 경우
            height = MeasureSpec.getSize(heightMeasureSpec);
            Log.i("custom view","EXACTLY");
        }
        nametext.measure(width, height/5);
        distancetext.measure(width, height/5);
        imageButton.measure(width, (height*3)/5);

        Log.i("custom view","widthheight" + width + " / " + height);
        setMeasuredDimension(width, height);

    }
    */
}
