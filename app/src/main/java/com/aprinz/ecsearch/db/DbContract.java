package com.aprinz.ecsearch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Alexander on 13.07.2015.
 */
public class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DbContract() {
    }

    /* Inner class that defines the table contents */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "errorCodes";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TEXT = "text";

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbEntry.TABLE_NAME + " (" +
                    DbEntry._ID + " INTEGER PRIMARY KEY," +
                    DbEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    DbEntry.COLUMN_NAME_TEXT + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbEntry.TABLE_NAME;
}
