package com.batuhanozdamar;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class reservationDB {

    public static final String TABLE_NAME="reservation";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_SURNAME = "surname";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_ADDRESS= "address";
    //public static final String FIELD_FLAG= "flag";
    //public static final String FIELD_ID= "id";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+
            TABLE_NAME+" ("+FIELD_NAME+" text, "+FIELD_SURNAME+" text, "+
            FIELD_PHONE+" number,"+FIELD_ADDRESS+" text);";

    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;


    public static List<reservationInfo> getAllReservation(databaseHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
        List<reservationInfo> data=new ArrayList<>();
        reservationInfo anItem = null;

        while (cursor.moveToNext()) {
           String name = cursor.getString(0);
           String surname = cursor.getString(1);
           //String flag= cursor.getString(2);
           //String id= cursor.getString(3);
           String phone = cursor.getString(2);
           String address = cursor.getString(3);


           anItem= new reservationInfo(name,surname,phone,address);
           data.add(anItem);
        }

        return data;
    }

    public static List<reservationInfo> findReservationItem(databaseHelper db, String key) {
        String where = FIELD_NAME + " like '%" + key + "%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME, null, where);
        List<reservationInfo> data = new ArrayList<>();
        reservationInfo anItem = null;

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String surname = cursor.getString(1);
            //String flag= cursor.getString(2);
            //String id= cursor.getString(3);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);

            anItem= new reservationInfo(name,surname,phone,address);
            data.add(anItem);
        }

        return data;
    }

    public static long insertReservationItem(databaseHelper db, String name, String surname, String address,String phone){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_SURNAME, surname);
        //contentValues.put(FIELD_FLAG, flag);
        // contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_PHONE, phone);
        contentValues.put(FIELD_ADDRESS, address);

        long res = db.insert(TABLE_NAME,contentValues);

        return res;
    }

    public static boolean updateReservationItem(databaseHelper db,String name, String surname,String address,String phone){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_SURNAME, surname);
        // contentValues.put(FIELD_FLAG, flag);
        //contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_PHONE, phone);
        contentValues.put(FIELD_ADDRESS, address);


        String where = FIELD_NAME +" = "+ name;

        boolean res = db.update(TABLE_NAME,contentValues,where);

        return res;
    }

    public static boolean deleteReservationItem(databaseHelper db, String name){
        String where = FIELD_NAME +" = "+ name;

        boolean res = db.delete(TABLE_NAME,where);

        return res;
    }
}
