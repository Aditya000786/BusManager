package com.example.user.busmanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.busmanager.data.BusContract.BusEntry;
import com.example.user.busmanager.data.BusHelper;

import static android.R.attr.type;

/**
 * Created by user on 15-Apr-17.
 */

public class
EditorClass extends AppCompatActivity {

    BusHelper DbHlper;
    private Spinner typeSpinner;
    private Spinner agencySpinner;
    private EditText numberEditText;
    private EditText fareEditText;
    private EditText arrivalEditText;
    private EditText destinationEditText;

    private int mType=0;
    private int mAgency=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        typeSpinner=(Spinner)findViewById(R.id.Bus_Type);
        agencySpinner=(Spinner)findViewById(R.id.Bus_Agency);

        DbHlper=new BusHelper(this);

        numberEditText=(EditText)findViewById(R.id.number);
        fareEditText=(EditText)findViewById(R.id.fare);
        arrivalEditText=(EditText)findViewById(R.id.arrival);
        destinationEditText=(EditText)findViewById(R.id.destination);

        setUpSpinner();

    }

    private void setUpSpinner() {
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.bus_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeSpinner.setAdapter(typeAdapter);


        ArrayAdapter agencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.bus_agency, android.R.layout.simple_spinner_item);
        agencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        agencySpinner.setAdapter(agencyAdapter);


        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection1 = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection1)) {
                    if (selection1.equals(("A.C"))) {
                        mType = BusEntry.TYPE_AC;
                    } else if (selection1.equals("NON A.C")) {
                        mType = BusEntry.TYPE_NONAC;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                mType = 0; // Unknown
            }
        });

        agencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection2 = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection2)) {
                    if (selection2.equals("Government")) {
                        mAgency = BusEntry.AGENCY_GOVERNMENT;
                    } else {
                        mAgency = BusEntry.AGENCY_PRIVATE;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                mAgency = 0; // Unknown
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_save:
                insertBus();
                finish();
                return true;

            case R.id.delete:
                //delete();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
     }

    private void insertBus() {

        SQLiteDatabase db=DbHlper.getWritableDatabase();

        String numberString=numberEditText.getText().toString();
        int number=Integer.parseInt(numberString);

        String fareString=fareEditText.getText().toString();
        int fare=Integer.parseInt(fareString);

        String arrivalString=arrivalEditText.getText().toString();

        String destinationString=destinationEditText.getText().toString();

        ContentValues values=new ContentValues();
        values.put(BusEntry.COLUMN_BUS_BUSNUMBER,number);
        values.put(BusEntry.COLUMN_BUS_FARE,fare);
        values.put(BusEntry.COLUMN_BUS_ARRIVAL,arrivalString);
        values.put(BusEntry.COLUMN_BUS_DESTINATION,destinationString);
        values.put(BusEntry.COLUMN_BUS_TYPE,mType);
        values.put(BusEntry.COLUMN_BUS_AGENCY,mAgency);

        long newRowId=db.insert(BusEntry.TABLE_NAME,null,values);

        if(newRowId!=-1){
            Toast.makeText(this,"Bus saved with Row Id:"+newRowId,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Error in saving Bus data",Toast.LENGTH_LONG).show();
        }
    }
}
