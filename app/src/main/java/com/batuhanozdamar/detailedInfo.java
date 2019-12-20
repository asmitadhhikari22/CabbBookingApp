package com.batuhanozdamar;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class detailedInfo extends AppCompatActivity {

    Intent intent;
    ImageView image;
    TextView brand,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        // Hiding title bar using code
        getSupportActionBar().hide();

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        image=findViewById(R.id.imageView2);
        brand=findViewById(R.id.textView5);
        price=findViewById(R.id.textView6);

        intent = this.getIntent();

        Bundle b = intent.getExtras();
        String value = b.getString("brand");

        if(value.equals("BMW 3 Series")){
            image.setImageResource(R.drawable.bmw3);
            brand.setText("Bmw 3 Series");
            price.setText("$ 60/day");
        }else{
            image.setImageResource(R.drawable.bmw5);
            brand.setText("Bmw 5 Series");
            price.setText("$ 90/day");
        }
    }


}

