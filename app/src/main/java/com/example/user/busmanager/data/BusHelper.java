package com.example.user.busmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import  com.example.user.busmanager.data.BusContract.BusEntry;
/**
 * Created by user on 16-Apr-17.
 */

public class BusHelper extends SQLiteOpenHelper {
    private static final String DATBASE_NAME="bus.db";
    private static final Integer DATABASE_VERSION=1;

    public BusHelper(Context context) {
        super(context,DATBASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE="Create table "+BusEntry.TABLE_NAME+"("+BusEntry._Id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                BusEntry.COLUMN_BUS_BUSNUMBER+" INTEGER,     "+
                BusEntry.COLUMN_BUS_FARE+" INTEGER NOT NULL, "+
                BusEntry.COLUMN_BUS_ARRIVAL+" TEXT NOT NULL, "+
                BusEntry.COLUMN_BUS_DESTINATION+" TEXT NOT NULL, "+
                BusEntry.COLUMN_BUS_TYPE+" INTEGER NOT NULL, "+
                BusEntry.COLUMN_BUS_AGENCY+" INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
