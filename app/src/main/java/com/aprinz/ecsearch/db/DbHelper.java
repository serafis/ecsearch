package com.aprinz.ecsearch.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.aprinz.ecsearch.ErrorCode;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Set;

/**
 * Created by Alexander on 13.07.2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ecsearch.db";
    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addRecords(Set<ErrorCode> ecs) {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.beginTransaction();
            String sql = "INSERT INTO " + DbContract.ErrorCodes.TABLE_NAME + " (" + DbContract.ErrorCodes.COLUMN_NAME_ENTRY_ID + ", " + DbContract.ErrorCodes.COLUMN_NAME_TEXT + ") VALUES (? ,?)";
            SQLiteStatement statement = database.compileStatement(sql);

            for (ErrorCode ec : ecs) {
                statement.clearBindings();
                statement.bindString(1, ec.id);
                statement.bindString(2, ec.text);
                statement.executeInsert();
            }
            database.setTransactionSuccessful();
        } catch (Exception e) {
            Log.w(TAG, e);
        } finally {
            database.endTransaction();
        }
    }

    public ErrorCode findRecord(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table = DbContract.ErrorCodes.TABLE_NAME;
        String[] columnsToReturn = {DbContract.ErrorCodes.COLUMN_NAME_ENTRY_ID, DbContract.ErrorCodes.COLUMN_NAME_TEXT };
        String selection = DbContract.ErrorCodes.COLUMN_NAME_ENTRY_ID+" =?";
        String[] selectionArgs = { id }; // matched to "?" in selection
        Cursor dbCursor = db.query(table, columnsToReturn, selection, selectionArgs, null, null, null);
        dbCursor.moveToFirst();
        return new ErrorCode(dbCursor.getString(0), dbCursor.getString(1));
    }
}
