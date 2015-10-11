package com.example.b102_12.ar_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class GridActivity extends Activity {
    ImageButton subwayBtn;
    ImageButton conveBtn;
    ImageButton cafeBtn;
    ImageButton medicalBtn;
    ImageButton foodBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        subwayBtn = (ImageButton)findViewById(R.id.subwaybtn);
        subwayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("number",1);
                startActivity(intent);

            }
        });

        conveBtn = (ImageButton)findViewById(R.id.convebtn);
        conveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("number",2);
                startActivity(intent);

            }
        });

        cafeBtn = (ImageButton)findViewById(R.id.cafebtn);
        cafeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("number",3);
                startActivity(intent);

            }
        });

        medicalBtn = (ImageButton)findViewById(R.id.medicbtn);
        medicalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("number",4);
                startActivity(intent);

            }
        });

        foodBtn = (ImageButton)findViewById(R.id.foodbtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putExtra("number",5);
                startActivity(intent);

            }
        });

    }

}
