package com.example.user.busmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 18-Apr-17.
 */

public class StockHelper extends SQLiteOpenHelper {
    private static final String DATBASE_NAME="stock.db";
    private static final Integer DATABASE_VERSION=1;

    public StockHelper(Context context) {
        super(context,DATBASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE="Create table "+ StockContract.StockEntry.TABLE_NAME+"("+ StockContract.StockEntry._Id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                StockContract.StockEntry.COLUMN_OLD_DATA+" INTEGER,     "+
                StockContract.StockEntry.COLUMN_NEW_DATA+" INTEGER NOT NULL, "+
                StockContract.StockEntry.COLUMN_COMPANY_NAME+" TEXT NOT NULL, "+
                StockContract.StockEntry.COLUMN_OWNER_NAME+" TEXT NOT NULL, "+
                StockContract.StockEntry.COLUMN_STOCK_TYPE+" INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
