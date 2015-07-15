package com.aprinz.ecsearch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aprinz.ecsearch.db.DbHelper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends Activity {

    private static final String TAG = "ECSearch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case R.id.action_import:
                importStuff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void importStuff() {

        File dir = Environment.getExternalStorageDirectory();
        File yourFile = new File(dir, "/test2.csv");

        DbHelper dbHelper = new DbHelper(getApplicationContext());

        CSVReader reader = null;
        String[] nextLine;
        try {
            reader = new CSVReader(new FileReader(yourFile), ';');
            while ((nextLine = reader.readNext()) != null) {
                Log.d(TAG, nextLine[0] + " " + nextLine[2]);
                ErrorCode ec = new ErrorCode(nextLine[1], nextLine[2]);
                dbHelper.addRecord(ec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
