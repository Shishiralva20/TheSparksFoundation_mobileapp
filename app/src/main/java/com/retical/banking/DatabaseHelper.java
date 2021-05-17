package com.retical.banking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Shishir',7855.78,'shishir.@gmail.com','XXXXXXXXXXXX4321','ABC10987654')");
        db.execSQL("insert into user_table values(8765432109,'Shashank',366.75,'shashank.@gmail.com','XXXXXXXXXXXX3214','BCA98765432')");
        db.execSQL("insert into user_table values(7654321098,'Rajesh',9845.50,'rajesh.@gmail.com','XXXXXXXXXXXX2143','CAB21098765')");
        db.execSQL("insert into user_table values(6543210987,'Suresh',1898.23,'suresh.@gmail.com','XXXXXXXXXXXX1432','ABC76543210')");
        db.execSQL("insert into user_table values(5432109876,'Tejas',4300.56,'tejas.@gmail.com','XXXXXXXXXXXX1234','BAC32109876')");
        db.execSQL("insert into user_table values(4321098765,'Avinash',755.80,'avinash.@gmail.com','XXXXXXXXXXXX1423','CAB54321098')");
        db.execSQL("insert into user_table values(3210987654,'Puneeth',3567.90,'puneeth.@gmail.com','XXXXXXXXXXXX3412','ABC43210987')");
        db.execSQL("insert into user_table values(2109876543,'Sham',7634.67,'sham.@gmail.com','XXXXXXXXXXXX3124','BCA53196345')");
        db.execSQL("insert into user_table values(1098765432,'Rohit',6789.43,'rohit.@gmail.com','XXXXXXXXXXXX4123','CAB54389675')");
        db.execSQL("insert into user_table values(0987654321,'Sachin',567.89,'sachin.@gmail.com','XXXXXXXXXXXX3421','ABC45678901')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
