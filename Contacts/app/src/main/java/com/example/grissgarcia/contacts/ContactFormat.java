package com.example.grissgarcia.contacts;

import android.provider.BaseColumns;

/**
 * Created by Griss Garcia on 30/06/2015.
 */
public class ContactFormat  {
    public static final String DB_NAME = "contact";
    public static final int VERSION = 1;
    public static  final String TABLE = "contacts";

    public class Columns {
        public static final String CONTACT_NAME = "name";
        public static final String PHONE = "phone";
        public static final String E_MAIL = "e_mail";
        public static final String _ID = BaseColumns._ID;
    }
}
