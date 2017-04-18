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
import com.example.user.busmanager.data.StockContract;
import com.example.user.busmanager.data.StockHelper;

public class MainActivity2 extends AppCompatActivity {


    StockHelper DbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity2.this, EditorClass2.class);
                startActivity(i);
            }
        });

        DbHelper = new StockHelper(this);
        displayDatabaseInfo();


    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = DbHelper.getReadableDatabase();

        String[] project = {
                StockContract.StockEntry.COLUMN_OLD_DATA,
                StockContract.StockEntry.COLUMN_NEW_DATA,
                StockContract.StockEntry.COLUMN_COMPANY_NAME,
                StockContract.StockEntry.COLUMN_OWNER_NAME,
                StockContract.StockEntry.COLUMN_STOCK_TYPE,
        };

        Cursor cursor = db.query(StockContract.StockEntry.TABLE_NAME,
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
            displayView.setText("Number of rows in stock database table: " + cursor.getCount() + " stock\n\n");
            displayView.append(
                    StockContract.StockEntry.COLUMN_OLD_DATA + " - " + StockContract.StockEntry.COLUMN_NEW_DATA + " - " +
                            StockContract.StockEntry.COLUMN_COMPANY_NAME + " - " + StockContract.StockEntry.COLUMN_OWNER_NAME + " - " +
                            StockContract.StockEntry.COLUMN_STOCK_TYPE + "\n");

            int old_dataColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_OLD_DATA);
            int new_dataColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_NEW_DATA);
            int company_nameColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_COMPANY_NAME);
            int owner_nameColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_OWNER_NAME);
            int typeColumnIndex = cursor.getColumnIndex(StockContract.StockEntry.COLUMN_STOCK_TYPE);

            while (cursor.moveToNext()) {
                String currentOld_data = cursor.getString(old_dataColumnIndex);
                String currentNew_data = cursor.getString(new_dataColumnIndex);
                String currentCompany_name = cursor.getString(company_nameColumnIndex);
                String cuurentOwner_name = cursor.getString(owner_nameColumnIndex);
                int cuurentType = cursor.getInt(typeColumnIndex);

                displayView.append("\n" +
                        currentOld_data + " - " + currentNew_data + " - " + currentCompany_name + " - " + cuurentOwner_name +
                        " - " + cuurentType + "\n");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catlog2, menu);
        return true;
    }

    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.delete:
                delete();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void delete() {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        db.delete(StockContract.StockEntry.TABLE_NAME,null,null);
    }

}
