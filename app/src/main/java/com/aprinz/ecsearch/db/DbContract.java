package com.aprinz.ecsearch.db;

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

    public static abstract class ErrorCodes implements BaseColumns {
        public static final String TABLE_NAME = "errorCodes";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TEXT = "text";

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ErrorCodes.TABLE_NAME + " (" +
                    ErrorCodes._ID + " INTEGER PRIMARY KEY," +
                    ErrorCodes.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_NAME_TEXT + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ErrorCodes.TABLE_NAME;
}
