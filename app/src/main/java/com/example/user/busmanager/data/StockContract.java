package com.example.user.busmanager.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 18-Apr-17.
 */

public class StockContract {
    private StockContract(){}

    public static final String CONTENT_AUTHORITY="com.example.user.busmanager";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_BUS="bus";

    public static class StockEntry implements BaseColumns {
        public final static String _Id=BaseColumns._ID;
        public final static String COLUMN_COMPANY_NAME="Company";
        public final static String COLUMN_OWNER_NAME="Owner";
        public final static String COLUMN_OLD_DATA="Old";
        public final static String COLUMN_NEW_DATA="New";
        public final static String COLUMN_STOCK_TYPE="type";

        public final static int TYPE_BSE=0;
        public final static int TYPE_NSE=1;

        public final static String TABLE_NAME="STOCK";
    }
}
