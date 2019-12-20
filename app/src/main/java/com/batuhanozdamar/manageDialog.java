package com.batuhanozdamar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static com.batuhanozdamar.detailedInfo.*;
import static java.lang.Integer.parseInt;

public class manageDialog {

    Dialog customDialog;
    Activity mainActivity;
    databaseHelper dbHelper;
    public GestureDetectorCompat mDetector1;
    GestureDetectorCompat  touchDetector;
    MediaPlayer explosion;

    String id;
    boolean flag;


    public manageDialog(databaseHelper dbHelper, Activity mainActivity){
        this.dbHelper = dbHelper;
        this.mainActivity = mainActivity;

    }

    public void displayDialog(String key){

        final EditText name,surname,address,phone;
        Button close,confirm;
        TextView touch,brand,price;
        ImageView car;
        Intent intent;

        customDialog = new Dialog(mainActivity);
        customDialog.setContentView(R.layout.activity_detailed);

        dbHelper = new databaseHelper(mainActivity);
        Log.d("DATABASE", "OK");

        name =  customDialog.findViewById(R.id.editName);
        surname = customDialog.findViewById(R.id.editSurname);
        address = customDialog.findViewById(R.id.editAdress);
        phone =  customDialog.findViewById(R.id.editPhone);
        close =customDialog.findViewById(R.id.buttonClose);
        confirm = customDialog.findViewById(R.id.button2);
        touch = customDialog.findViewById(R.id.textViewTap);
        car = customDialog.findViewById(R.id.imageView2);
        brand= customDialog.findViewById(R.id.textView5);
        price = customDialog.findViewById(R.id.textView6);



        if(key.equals("BMW 3 Series")){
            car.setImageResource(R.drawable.bmw3);
            brand.setText("Bmw 3 Series");
            price.setText("€ 60/day");
            id="1";
        }else if(key.equals("BMW 5 Series")){
            car.setImageResource(R.drawable.bmw5);
            brand.setText("Bmw 5 Series");
            price.setText("€ 90/day");
            id="2";
        }else if(key.equals("Yamaha MT-07")) {
            car.setImageResource(R.drawable.mt07);
            brand.setText("Yamaha MT-07");
            price.setText("€ 40/day");
            id="3";
        }else{
            car.setImageResource(R.drawable.delight);
            brand.setText("Yamaha Delight");
            price.setText("€ 20/day");
            id="4";
        }



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneN = phone.getText().toString();
                String nameN = name.getText().toString();
                String addressN = address.getText().toString();
                String surnameN = surname.getText().toString();

                if(phoneN.equals("") || nameN.equals("") || surnameN.equals("") || addressN.equals("")){
                    //Error Message
                    Toast.makeText(mainActivity.getApplicationContext(), "Please Enter All Details", Toast.LENGTH_LONG).show();

                    //Sound File
                    explosion = MediaPlayer.create(mainActivity, R.raw.error);
                    explosion.start();


                } else if(flag==true){

                    Toast.makeText(mainActivity.getApplicationContext(), "Vehicle is Reserved!\nPlease Select Another One!", Toast.LENGTH_LONG).show();
                }

                else {

                    reservationDB.insertReservationItem(dbHelper, nameN, surnameN, addressN, phoneN);
                    flag=true;
                    Toast.makeText(mainActivity.getApplicationContext(), "Reservation Complete!", Toast.LENGTH_LONG).show();
                }
            }
        });


        mDetector1 = new GestureDetectorCompat(mainActivity, new manageDialog.MyGestureListener());

        touchDetector = new GestureDetectorCompat(mainActivity, new manageDialog.MyGestureListener());

        touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                touchDetector.onTouchEvent(motionEvent);
                return true;
            }
        });



        customDialog.show();
    }


    public boolean onTouchEvent(MotionEvent event) {
        mDetector1.onTouchEvent(event);

        // Make sure to call the superclass implementation
        return onTouchEvent(event);
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


        public void onLongPress(MotionEvent motionEvent) {


        }


        public boolean onDoubleTap(MotionEvent event) {

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {

            Intent intent = new Intent(mainActivity, summaryScreen.class);
            mainActivity.startActivity(intent);

            return true;
        }

    }


}