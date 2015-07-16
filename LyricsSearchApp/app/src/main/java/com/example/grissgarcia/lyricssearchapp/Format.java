package com.example.grissgarcia.lyricssearchapp;

import android.provider.BaseColumns;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class Format {
    public static final String DB_NAME = "artist";
    public static final int VERSION = 1;
    public static  final String TABLE = "artists";

    public class Columns {
        public static final String ARTIST_NAME = "name";
        public static final String SONG = "phone";
        public static final String _ID = BaseColumns._ID;
    }
}
