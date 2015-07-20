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
        public static final String COLUMN_ENTRY_ID = "identifier";
        public static final String COLUMN_PRIORITY_NAME = "pname";
        public static final String COLUMN_DRIVER_EVENT_DES = "driverEventDescription";
        public static final String COLUMN_S_ADVICE = "shortAdvice";
        public static final String COLUMN_E_ADVICE = "extendedAdvice";
        public static final String COLUMN_MAINTENANCE_EVENT_DES = "maintenanceEventDescription";
        public static final String COLUMN_REPAIR_TEXT = "repairText";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ErrorCodes.TABLE_NAME + " (" +
                    ErrorCodes.COLUMN_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_PRIORITY_NAME + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_DRIVER_EVENT_DES + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_S_ADVICE + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_E_ADVICE + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_MAINTENANCE_EVENT_DES + TEXT_TYPE + COMMA_SEP +
                    ErrorCodes.COLUMN_REPAIR_TEXT + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ErrorCodes.TABLE_NAME;
}
