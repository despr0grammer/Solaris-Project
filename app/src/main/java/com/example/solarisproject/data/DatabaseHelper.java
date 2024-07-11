package com.example.solarisproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SolarisProject.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserContract.UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_AGE + " INTEGER NOT NULL, " +
                UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_PHONE + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_USER_TYPE + " TEXT NOT NULL)";

        String SQL_CREATE_ENERGY_USAGE_TABLE = "CREATE TABLE energy_usage (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "usage REAL NOT NULL, " +
                "cost REAL NOT NULL)";

        String SQL_CREATE_ENERGY_GENERATION_TABLE = "CREATE TABLE energy_generation (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "generation REAL NOT NULL, " +
                "revenue REAL NOT NULL)";

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_ENERGY_USAGE_TABLE);
        db.execSQL(SQL_CREATE_ENERGY_GENERATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS energy_usage");
        db.execSQL("DROP TABLE IF EXISTS energy_generation");
        onCreate(db);
    }

    public long insertEnergyUsage(double usage, double cost, long timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usage", usage);
        values.put("cost", cost);
        values.put("timestamp", timestamp);
        long newRowId = db.insert("energy_usage", null, values);
        db.close();
        return newRowId;
    }

    public long insertEnergyGeneration(double generation, double revenue, long timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("generation", generation);
        values.put("revenue", revenue);
        values.put("timestamp", timestamp);
        long newRowId = db.insert("energy_generation", null, values);
        db.close();
        return newRowId;
    }

    public Cursor getEnergyUsageDataFiltered(String filter) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM energy_usage";
        switch (filter) {
            case "Diario":
                query += " WHERE date(timestamp / 1000, 'unixepoch') = date('now')";
                break;
            case "Mensual":
                query += " WHERE strftime('%Y-%m', timestamp / 1000, 'unixepoch') = strftime('%Y-%m', 'now')";
                break;
            case "Anual":
                query += " WHERE strftime('%Y', timestamp / 1000, 'unixepoch') = strftime('%Y', 'now')";
                break;
        }
        query += " ORDER BY timestamp DESC";
        return db.rawQuery(query, null);
    }

    public Cursor getEnergyGenerationDataFiltered(String filter) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM energy_generation";
        switch (filter) {
            case "Diario":
                query += " WHERE date(timestamp / 1000, 'unixepoch') = date('now')";
                break;
            case "Mensual":
                query += " WHERE strftime('%Y-%m', timestamp / 1000, 'unixepoch') = strftime('%Y-%m', 'now')";
                break;
            case "Anual":
                query += " WHERE strftime('%Y', timestamp / 1000, 'unixepoch') = strftime('%Y', 'now')";
                break;
        }
        query += " ORDER BY timestamp DESC";
        return db.rawQuery(query, null);
    }

    public long insertUser(String name, String lastName, int age, String email, String phone, String password, String userType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME, name);
        values.put(UserContract.UserEntry.COLUMN_LAST_NAME, lastName);
        values.put(UserContract.UserEntry.COLUMN_AGE, age);
        values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
        values.put(UserContract.UserEntry.COLUMN_PHONE, phone);
        values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);
        values.put(UserContract.UserEntry.COLUMN_USER_TYPE, userType);
        long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                UserContract.UserEntry._ID
        };
        String selection = UserContract.UserEntry.COLUMN_EMAIL + " = ?" + " AND " +
                UserContract.UserEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public Cursor getUserProfile(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                UserContract.UserEntry.COLUMN_NAME,
                UserContract.UserEntry.COLUMN_LAST_NAME,
                UserContract.UserEntry.COLUMN_AGE,
                UserContract.UserEntry.COLUMN_EMAIL,
                UserContract.UserEntry.COLUMN_PHONE,
                UserContract.UserEntry.COLUMN_USER_TYPE
        };
        String selection = UserContract.UserEntry.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};

        return db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }

    public String getUserType(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String userType = null;
        String[] projection = {
                UserContract.UserEntry.COLUMN_USER_TYPE
        };
        String selection = UserContract.UserEntry.COLUMN_EMAIL + " = ?" + " AND " +
                UserContract.UserEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            userType = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_USER_TYPE));
        }
        cursor.close();
        db.close();
        return userType;
    }
}
