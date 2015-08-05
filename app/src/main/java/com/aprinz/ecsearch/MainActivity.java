package com.aprinz.ecsearch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.aprinz.ecsearch.db.DbHelper;
import com.aprinz.ecsearch.extractor.Extractor;

import java.io.File;
import java.util.Set;


public class MainActivity extends Activity implements SearchFragment.OnSearchedListener{
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                openSearchDialog();
                return true;
            case R.id.action_import:
                importStuff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearchDialog() {
        SearchFragment fragment = new SearchFragment();
        fragment.show(getFragmentManager(), "fragmentTag");
    }

    private void bsImport() {
        Set<ErrorCode> ecs = Extractor.extract(FileLoader.loadAssetTextAsString(getApplicationContext(), "dbinit.csv"));
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

    public void pullErrorCode(String id) {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        try {
            ErrorCode ec = dbHelper.findRecord(id);
            showErrorCode(ec);
        } catch (IllegalArgumentException e) {
            showToast("Search Failed! Probably not found");
        }
    }

    private void showErrorCode(ErrorCode ec) {
        ListView yourListView = (ListView) findViewById(R.id.errorcode_content_list);
        MyListAdapter customAdapter = new MyListAdapter(this, R.layout.activity_main, ec.getAsList());
        yourListView.setAdapter(customAdapter);
    }

    private void showToast(CharSequence seq) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), seq, duration);
        toast.show();
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

    @Override
    public void onErrorCodeSearched(String id) {
        pullErrorCode(id);
    }
}
