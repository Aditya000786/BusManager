package com.example.user.busmanager.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 16-Apr-17.
 */

public final class BusContract  {

    private BusContract(){}

    public static final String CONTENT_AUTHORITY="com.example.user.busmanager";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_BUS="bus";

    public static class BusEntry implements BaseColumns{
        public final static String _Id=BaseColumns._ID;
        public final static String COLUMN_BUS_ARRIVAL="arrival";
        public final static String COLUMN_BUS_DESTINATION="destination";
        public final static String COLUMN_BUS_BUSNUMBER="number";
        public final static String COLUMN_BUS_FARE="fare";
        public final static String COLUMN_BUS_TYPE="type";
        public final static String COLUMN_BUS_AGENCY="agency";

        public final static int TYPE_AC=0;
        public final static int TYPE_NONAC=1;
        public final static int AGENCY_GOVERNMENT=0;
        public final static int AGENCY_PRIVATE=1;

        public final static String TABLE_NAME="bus";
    }
}
