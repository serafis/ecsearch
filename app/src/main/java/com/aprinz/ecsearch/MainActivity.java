package com.aprinz.ecsearch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.aprinz.ecsearch.db.DbHelper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

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
        File file = new File(dir, "/test4.csv");

        Set<ErrorCode> ecs = Extractor.extract(file);
        DbHelper dbHelper = new DbHelper(getApplicationContext());

        dbHelper.addRecords(ecs);
    }

    public void findEntry(View view) {
        DbHelper dbHelper = new DbHelper(getApplicationContext());

        EditText mEdit = (EditText)findViewById(R.id.search_string);

        ErrorCode ec = dbHelper.findRecord(mEdit.getText().toString());

    }
}
