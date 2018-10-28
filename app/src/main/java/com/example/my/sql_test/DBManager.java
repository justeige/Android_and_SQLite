package com.example.my.sql_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper _dbHelper;
    private Context        _ctx;
    private SQLiteDatabase _db;

    static public DBManager createAndOpenDB(Context ctx) throws SQLException {
        DBManager newManager = new DBManager(ctx);
        newManager._dbHelper = new DatabaseHelper(newManager._ctx);
        newManager._db = newManager._dbHelper.getWritableDatabase(); // will open the db connection!
        return newManager;
    }

    private DBManager(Context ctx) {
        _ctx = ctx;
    }

    public void close() {
        _dbHelper.close();
    }

    /// CRUD operations
    public Cursor fetch_all() {
        String[] columns = new String[] { DatabaseHelper.ID, DatabaseHelper.RECORD_NAME, DatabaseHelper.DESC };
        Cursor cursor = _db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void insert(String recordName, String desc) {
        ContentValues cvs = new ContentValues();
        cvs.put(DatabaseHelper.RECORD_NAME, recordName);
        cvs.put(DatabaseHelper.DESC, desc);
        _db.insert(DatabaseHelper.TABLE_NAME, null, cvs);
    }

    public int update(long id, String name, String desc) {
        ContentValues cvs = new ContentValues();
        cvs.put(DatabaseHelper.RECORD_NAME, name);
        cvs.put(DatabaseHelper.DESC, desc);
        return _db.update(DatabaseHelper.TABLE_NAME, cvs, DatabaseHelper.ID + " = " + id, null);
    }

    public void delete(long id) {
        _db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + " = " + id, null);
    }
}
