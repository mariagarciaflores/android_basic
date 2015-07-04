package com.example.grissgarcia.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Griss Garcia on 30/06/2015.
 */
public class ContactsDataBaseHelper extends SQLiteOpenHelper {

    public ContactsDataBaseHelper(Context context) {
        super(context, ContactFormat.DB_NAME, null, ContactFormat.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s TEXT, " + "%s TEXT, " + "%s TEXT )",
                        ContactFormat.TABLE,
                        ContactFormat.Columns.CONTACT_NAME, ContactFormat.Columns.PHONE, ContactFormat.Columns.E_MAIL);
        Log.d("ContactsDataBaseHelper", "Query to form table: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {
        sqlDB.execSQL("DROP TABLE IF EXISTS" + ContactFormat.TABLE);
        onCreate(sqlDB);
    }
}
