package com.totoro.record.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by totoro-fly on 2017/4/28.
 */

public class RecordDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "record6.db";
    private static final int DATABASE_VERSION = 1;

    public RecordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_RECORD_TABLE = "CREATE TABLE " + RecordContract.RecordEntry.TABLE_NAME + " ( "
                + RecordContract.RecordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RecordContract.RecordEntry.COLUMN_RECORD_YEAR + " INTEGER NOT NULL, "
                + RecordContract.RecordEntry.COLUMN_RECORD_MONTH + " INTEGER NOT NULL, "
                + RecordContract.RecordEntry.COLUMN_RECORD_DAY + " INTEGER NOT NULL, "
                + RecordContract.RecordEntry.COLUMN_RECORD_MESSAGE + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_RECORD_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor readAllData(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(" SELECT " + "*" +
                " FROM " + RecordContract.RecordEntry.TABLE_NAME +
                " ORDER BY " + RecordContract.RecordEntry.COLUMN_RECORD_YEAR +
                " , " + RecordContract.RecordEntry.COLUMN_RECORD_MONTH +
                " , " + RecordContract.RecordEntry.COLUMN_RECORD_DAY, null
        );
        return cursor;
    }
}
