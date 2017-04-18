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
import com.example.user.busmanager.data.StockContract;
import com.example.user.busmanager.data.StockHelper;

import static android.R.attr.type;
import static com.example.user.busmanager.R.id.fare;

/**
 * Created by user on 15-Apr-17.
 */

public class
EditorClass2 extends AppCompatActivity {

    StockHelper DbHlper;
    private Spinner typeSpinner;
    private EditText old_dataEditText;
    private EditText new_dataEditText;
    private EditText company_nameEditText;
    private EditText owner_nameEditText;

    private int mType=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity2);

        typeSpinner=(Spinner)findViewById(R.id.Stock_Type);

        DbHlper=new StockHelper(this);

        old_dataEditText=(EditText)findViewById(R.id.old_data);
        new_dataEditText=(EditText)findViewById(R.id.new_data);
        company_nameEditText=(EditText)findViewById(R.id.company_name);
        owner_nameEditText=(EditText)findViewById(R.id.owner_name);

        setUpSpinner();

    }

    private void setUpSpinner() {
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.stock_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeSpinner.setAdapter(typeAdapter);



        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection1 = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection1)) {
                    if (selection1.equals(("BSE"))) {
                        mType = StockContract.StockEntry.TYPE_BSE;
                    } else if (selection1.equals("NSE")) {
                        mType = StockContract.StockEntry.TYPE_NSE;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                mType = 0; // Unknown
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_save:
                insertStock();
                finish();
                return true;

            case R.id.delete:
                NavUtils.navigateUpFromSameTask(this);

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


    private void insertStock() {

        SQLiteDatabase db=DbHlper.getWritableDatabase();



        String old_dataString=old_dataEditText.getText().toString();

        int old_data=Integer.parseInt(old_dataString);

        String new_dataString=new_dataEditText.getText().toString();
        int new_data=Integer.parseInt(new_dataString);

        String company_nameString=company_nameEditText.getText().toString();

        String owner_nameString=owner_nameEditText.getText().toString();

        ContentValues values=new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_OLD_DATA,old_data);
        values.put(StockContract.StockEntry.COLUMN_NEW_DATA,new_data);
        values.put(StockContract.StockEntry.COLUMN_COMPANY_NAME,company_nameString);
        values.put(StockContract.StockEntry.COLUMN_OWNER_NAME,owner_nameString);
        values.put(StockContract.StockEntry.COLUMN_STOCK_TYPE,mType);

        long newRowId=db.insert(StockContract.StockEntry.TABLE_NAME,null,values);

        if(newRowId!=-1){
            Toast.makeText(this,"Stock saved with Row Id:"+newRowId,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Error in saving Stock data",Toast.LENGTH_LONG).show();
        }
    }

}
