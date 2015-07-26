package com.aprinz.ecsearch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aprinz.ecsearch.db.DbHelper;
import com.aprinz.ecsearch.extractor.Extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnceStuff();
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

    private void bsImport() {
        Set<ErrorCode> ecs = Extractor.extract(loadAssetTextAsString(getApplicationContext(), "dbinit.csv"));
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        dbHelper.addRecords(ecs);
    }

    private void importStuff() {
        try {
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir, "test4.csv");

            if (file.canRead()) {
                Set<ErrorCode> ecs = Extractor.extract(file);
                DbHelper dbHelper = new DbHelper(getApplicationContext());
                dbHelper.addRecords(ecs);
                showToast("Import successfull!");
            } else {
                throw new Exception("Cannot read file");
            }
        } catch (Exception ex) {
            showToast("Import failed!");
            Log.e(TAG, "Import Failed!", ex);
        }
    }

    public void findEntry(View view) {

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        EditText mEdit = (EditText) findViewById(R.id.search_string);
        try {
            ErrorCode ec = dbHelper.findRecord(mEdit.getText().toString());
            ListView yourListView = (ListView) findViewById(R.id.errorcode_content_list);
            MyListAdapter customAdapter = new MyListAdapter(this, R.layout.activity_main, ec.getAsList());
            yourListView.setAdapter(customAdapter);
        } catch (IllegalArgumentException e) {
            showToast("Search Failed! Probably not found");
        }
    }

    private void showToast(CharSequence seq) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), seq, duration);
        toast.show();
    }

    private String read_file(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                getAssets().open(filename)));

        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            if (++i == 1)
                return line;//only read the first line
            line = reader.readLine();
        }

        reader.close();
        return "Nothing to get";
    }

    private String loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing asset " + name);
                }
            }
        }

        return null;
    }

    private void runOnceStuff() {
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            bsImport();

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }
    }
}
