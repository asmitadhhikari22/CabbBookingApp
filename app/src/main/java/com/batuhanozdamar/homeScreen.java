package com.batuhanozdamar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class homeScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    recyclerViewAdapter adapter;
    Button button,getRates;
    ArrayList<carInfo> recycleList = new ArrayList<carInfo>();
    LinearLayoutManager layoutManager;
    TextView rate,info;


    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Hiding title bar using code
        getSupportActionBar().hide();

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startService(new Intent(getBaseContext(), MyService.class));


        rate = findViewById(R.id.textCurrency);
        rate.setVisibility(View.INVISIBLE);
        info=findViewById(R.id.textView3);

        getRates=findViewById(R.id.button3);
        button = findViewById(R.id.button);

        prepareData();

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        adapter = new recyclerViewAdapter(this, recycleList);
        recyclerView.setAdapter(adapter);


    }


    public void prepareData(){
        recycleList.add(new carInfo(R.drawable.bmw3,"BMW 3 Series","Seats: 5 Seat","KM: 35.000","Engine: 2.0L","€ 60/day"));
        recycleList.add(new carInfo(R.drawable.bmw5,"BMW 5 Series","Seats: 5 Seat","KM: 60.000","Engine: 3.0L","€ 90/day"));
        recycleList.add(new carInfo(R.drawable.mt07,"Yamaha MT-07","Seats: 2 Seat","KM: 5.000","Engine: 790cc","€ 40/day"));
        recycleList.add(new carInfo(R.drawable.delight,"Yamaha Delight","Seats: 2 Seat","KM: 10.000","Engine: 125cc","€ 20/day"));

    }


    public void onClick(View view) {
        Intent intent = new Intent(homeScreen.this, summaryScreen.class);
        startActivity(intent);
    }

    //JSON API
    public void getRates(View view) {

        DownloadData downloadData = new DownloadData();

        try {

            String url = "http://data.fixer.io/api/latest?access_key=eabc3c19b08ffdf78ee95d4a28045f7a";

            downloadData.execute(url);
            rate.setVisibility(View.VISIBLE);
            info.setVisibility(View.INVISIBLE);

        } catch (Exception e) {

        }


    }


    private class DownloadData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //System.out.println("alınan data:" + s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                //System.out.println("base:" + base);

                String rates = jsonObject.getString("rates");


                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                rate.setText("TRY : " + turkishlira);


            } catch (Exception e) {

            }


        }
    }



}