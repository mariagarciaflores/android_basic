package com.example.grissgarcia.lyricssearchapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class DataBaseMain extends Activity{

    private ListAdapter listAdapter;
    private Helper cHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyrics_list_layout);

        /*Bundle bundle = getIntent().getExtras();
        String artist = bundle.getString("artist");
        String song = bundle.getString("song");
        cHelper = new Helper(this);
        SQLiteDatabase db = cHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.clear();
        values.put(Format.Columns.ARTIST_NAME, artist);
        values.put(Format.Columns.SONG, song);

        db.insertWithOnConflict(Format.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);*/
        updateUI();
    }


    private void updateUI() {

        ListView view = (ListView) findViewById(R.id.list_despegable_view);
        cHelper = new Helper(this);
        SQLiteDatabase sqlDB = cHelper.getReadableDatabase();
        Cursor cursor = sqlDB.query(Format.TABLE,
                new String[]{Format.Columns._ID, Format.Columns.ARTIST_NAME,
                        Format.Columns.SONG,
                }, null, null, null, null, null);
        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_list_layout,
                cursor,
                new String[]{Format.Columns.ARTIST_NAME, Format.Columns.SONG},
                new int[]{R.id.artist_name_txt_list_view, R.id.sing_title_txt_list_view},
                0
        );
        view.setAdapter(listAdapter);
    }
}
