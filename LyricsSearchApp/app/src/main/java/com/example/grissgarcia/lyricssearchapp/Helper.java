package com.example.grissgarcia.lyricssearchapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class Helper extends SQLiteOpenHelper {
    public Helper(Context context) {

        super(context, Format.DB_NAME, null, Format.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s TEXT, " + "%s TEXT )",
                        Format.TABLE,
                        Format.Columns.ARTIST_NAME, Format.Columns.SONG);
        Log.d("ContactsDataBaseHelper", "Query to form table: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {
        sqlDB.execSQL("DROP TABLE IF EXISTS" + Format.TABLE);
        onCreate(sqlDB);
    }
}
