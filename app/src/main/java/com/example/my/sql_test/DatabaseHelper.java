package com.example.my.sql_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // db specific data
    public static final String DB_Name = "test_app.db";
    public static final int DB_Version = 0;

    // data for records
    public static final String RECORD_NAME = "rec";
    public static final String TABLE_NAME = "memo";
    public static final String DESC       = "dec";
    public static final String ID         = "ID";

    // init for the db
    private static final String CreateTableCode =
            "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
             + RECORD_NAME + " TEXT NOT NULL, " + DESC + " TEXT);";

    private  static final String DropTableCode = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_Name, null, DB_Version);
    }

    // will be called when there is no db yet => first creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableCode);
    }

    // called when the schema needs to upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DropTableCode);
        onCreate(db);
    }
}
