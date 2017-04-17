package com.example.user.busmanager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.busmanager.data.BusContract.BusEntry;
import com.example.user.busmanager.data.BusHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,EditorClass.class);
                startActivity(i);
            }
        });

        displayDatabaseInfo();
        BusHelper DbHelper=new BusHelper(this);
    }

    private void displayDatabaseInfo() {

        BusHelper mDbHelper=new BusHelper(this);
        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String[] project={
                BusEntry.COLUMN_BUS_BUSNUMBER,
                BusEntry.COLUMN_BUS_FARE,
                BusEntry.COLUMN_BUS_ARRIVAL,
                BusEntry.COLUMN_BUS_DESTINATION,
                BusEntry.COLUMN_BUS_TYPE,
                BusEntry.COLUMN_BUS_AGENCY
        };

        Cursor cursor=db.query(BusEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_bus);
            displayView.setText("Number of rows in bus database table: " + cursor.getCount()+" bus\n\n");
            displayView.append(
                    BusEntry.COLUMN_BUS_BUSNUMBER+" - "+BusEntry.COLUMN_BUS_FARE+" - "+
                    BusEntry.COLUMN_BUS_ARRIVAL+" - "+BusEntry.COLUMN_BUS_DESTINATION+" - "+
                    BusEntry.COLUMN_BUS_TYPE+" - "+BusEntry.COLUMN_BUS_AGENCY+"\n");

            int numberColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_BUSNUMBER);
            int FareColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_FARE);
            int arrivalColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_ARRIVAL);
            int destinationColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_DESTINATION);
            int typeColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_TYPE);
            int agencyColumnIndex=cursor.getColumnIndex(BusEntry.COLUMN_BUS_AGENCY);

            while(cursor.moveToNext()){
                String currentNumber=cursor.getString(numberColumnIndex);
                String currentFare=cursor.getString(FareColumnIndex);
                String currentArrival=cursor.getString(arrivalColumnIndex);
                String cuurentDestination=cursor.getString(destinationColumnIndex);
                int cuurentType=cursor.getInt(typeColumnIndex);
                int cuurentAgency=cursor.getInt(agencyColumnIndex);

                displayView.append("\n"+
                        currentNumber+" - "+currentFare+" - "+currentArrival+" - "+cuurentDestination+
                        " - "+cuurentType+" - "+cuurentAgency+"\n");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catlog_menu,menu);
        return true;
    }

    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.delete:
                return true;
           }
        return super.onOptionsItemSelected(item);
    }

}
