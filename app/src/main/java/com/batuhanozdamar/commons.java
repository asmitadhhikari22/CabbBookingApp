package com.batuhanozdamar;

import android.database.Cursor;

import java.util.ArrayList;

public class commons {

    public static reservationInfo curentReservation;
    public static Cursor cursor;
    public static int curentItemIndex=0;
    public static ArrayList<reservationInfo> data;


    public static ArrayList<reservationInfo> getData() {
        return data;
    }

    public static void setData(ArrayList<reservationInfo> data) {
        commons.data = data;
    }

    public static reservationInfo getCurentReservation() {
        return curentReservation;
    }

    public static void setCurentReservation(reservationInfo curentReservation) {
        commons.curentReservation = curentReservation;
    }
}
