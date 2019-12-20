package com.batuhanozdamar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivities;
import static android.support.v4.content.ContextCompat.startActivity;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyRecyclerViewItemHolder> {

    private Context context;
    private ArrayList<carInfo> recyclerItemValues;
    databaseHelper dbHelper;
    manageDialog myDialog;
    String brand;

    //Custom Dialog
    Dialog customDialog;
    Button btnDialogDone;
    TextView nameOfHouse;
    TextView houseWords;
    ImageView houseLogo;
    carInfo currentSelectedHouse;


    public recyclerViewAdapter(Context context, ArrayList<carInfo> values) {
        this.context = context;
        this.recyclerItemValues = values;
    }


    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycle_card, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final carInfo item = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.brand.setText(item.getBrand());
        myRecyclerViewItemHolder.img.setImageResource(item.getImageId());
        myRecyclerViewItemHolder.engine.setText(item.getEngine());
        myRecyclerViewItemHolder.km.setText(item.getKm());
        myRecyclerViewItemHolder.price.setText(item.getPrice());
        myRecyclerViewItemHolder.seat.setText(item.getSeat());


        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Clicked Car is: " + item.getBrand(), Toast.LENGTH_LONG).show();
                brand = item.getBrand();

                makeAndShowDialogBox(item.getBrand(),item.getImageId());

                /*//Send values
                Intent intent = new Intent(context, manageDialog.class);
                String brand = item.getBrand();

                Bundle b = new Bundle();
                b.putString("brand",brand);
                intent.putExtras(b);
                //context.startActivity(intent);*/
                //Toast.makeText(context.getApplicationContext(),brand,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }


    class MyRecyclerViewItemHolder extends RecyclerView.ViewHolder {

        TextView brand,engine,km,price,seat;
        ImageView img;
        LinearLayout parentLayout;

        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.brand);
            img = itemView.findViewById(R.id.imageView);
            engine = itemView.findViewById(R.id.engine);
            km = itemView.findViewById(R.id.km);
            price = itemView.findViewById(R.id.price);
            seat = itemView.findViewById(R.id.seat);
            parentLayout = itemView.findViewById(R.id.recycleCard);
        }
    }


    //Dialog Box
    public void makeAndShowDialogBox(String name, int image) {

        final AlertDialog.Builder mDialogBox = new AlertDialog.Builder(context);
        final carInfo item = null;

        // set message, title, and icon
        mDialogBox.setTitle(name);
        mDialogBox.setIcon(image);
        mDialogBox.setMessage("You have to over 21 years old in order to rent this car!");

        // Set three option buttons
        mDialogBox.setPositiveButton("ACCEPT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //ok butonuna tıklayınca aktivite açılacak

                        //Send values
                        dbHelper = new databaseHelper(context);
                        myDialog = new manageDialog(dbHelper, (homeScreen) context);
                        myDialog.displayDialog(brand);
                    }
                });

        mDialogBox.setNegativeButton("REJECT",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

        mDialogBox.create();
        mDialogBox.show();

    }



}

