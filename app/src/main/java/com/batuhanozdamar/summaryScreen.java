package com.batuhanozdamar;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class summaryScreen extends AppCompatActivity {

    EditText name;
    Button search;
    databaseHelper dbHelper;
    manageDialog myDialog;
    secondRecyclerAdapter adapter;
    RecyclerView recyclerView;

    private GestureDetectorCompat mDetector;
    GestureDetectorCompat  touchDetector;

    TextView touch;
    Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Hiding title bar using code
        getSupportActionBar().hide();

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.editText);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setVisibility(View.INVISIBLE);
        touch = findViewById(R.id.textL);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);

        dbHelper = new databaseHelper(this);

        myDialog = new manageDialog(dbHelper, this);

        commons.data = (ArrayList<reservationInfo>)reservationDB.getAllReservation(dbHelper);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new secondRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prepareData();
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDetector = new GestureDetectorCompat(summaryScreen.this, new summaryScreen.MySecondGestureListener());

        touchDetector = new GestureDetectorCompat(summaryScreen.this, new summaryScreen.MySecondGestureListener());

        touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                touchDetector.onTouchEvent(motionEvent);
                return true;
            }
        });


    }

    public void onClick(View view){

        prepareData();

    }

    public void prepareData() {
        String key="";

        if(name.getText() != null)
            key = name.getText().toString();

        Log.d("BURADA","BURADA");

        commons.data = (ArrayList<reservationInfo>)reservationDB.findReservationItem(dbHelper, key);
        //adapter = new MyRecyclerViewAdapter(this);
        //recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged( );
        adapter.notifyItemChanged(0, commons.data.size());
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.close();
    }

    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);

        // Make sure to call the superclass implementation
        return onTouchEvent(event);
    }


    class MySecondGestureListener extends GestureDetector.SimpleOnGestureListener {


        public void onLongPress(MotionEvent motionEvent) {

            Intent intent = new Intent(summaryScreen.this, homeScreen.class);
            startActivity(intent);

        }


        public boolean onDoubleTap(MotionEvent event) {

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {

            return true;
        }

    }

    public void Activity (View view) {
        switch (view.getId()) {
            //case R.id.start:
                //startService(new Intent(getBaseContext(), MyService.class));
                //break;
            case R.id.stop:
                stopService(new Intent(getBaseContext(), MyService.class));
                break;
        }
    }




}
