package com.example.grissgarcia.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by Griss Garcia on 30/06/2015.
 */
public class MainActivity extends Activity {//AppCompatActivity implements View.OnLongClickListener {

    private ListAdapter listAdapter;
    private ContactsDataBaseHelper cHelper;
    private Button addButton;
    LayoutInflater inflater;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addButton = (Button) findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button pressed = (Button) v;
                if (pressed == addButton) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Add Contact");
                    View view = inflater.inflate(R.layout.message_layout, null);
                    final EditText contactName = (EditText) view.findViewById(R.id.txtContactName_insert);
                    final EditText contactPhone =  (EditText) view.findViewById(R.id.txtPhone_insert);
                    final EditText contactEmail = (EditText) view.findViewById(R.id.txtEmail_insert);
                    builder.setView(view);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String contact = contactName.getText().toString();
                            String phone = contactPhone.getText().toString();


                            String email = contactEmail.getText().toString();

                            cHelper = new ContactsDataBaseHelper(MainActivity.this);
                            SQLiteDatabase db = cHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();

                            values.clear();
                            values.put(ContactFormat.Columns.CONTACT_NAME, contact);
                            values.put(ContactFormat.Columns.PHONE, phone);
                            values.put(ContactFormat.Columns.E_MAIL, email);

                            db.insertWithOnConflict(ContactFormat.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                            updateUI();
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    builder.create().show();
                }
            }
        });
        inflater = this.getLayoutInflater();

        updateUI();
    }


    private void updateUI() {
        ListView view = (ListView) findViewById(R.id.contact_list);
       cHelper = new ContactsDataBaseHelper(this);
        SQLiteDatabase sqlDB = cHelper.getReadableDatabase();
        Cursor cursor = sqlDB.query(ContactFormat.TABLE,
                new String[]{ContactFormat.Columns._ID, ContactFormat.Columns.CONTACT_NAME,
                             ContactFormat.Columns.PHONE, ContactFormat.Columns.E_MAIL,
                             }, null, null, null, null, null);
        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.list_layout,
                cursor,
                new String[]{ContactFormat.Columns.CONTACT_NAME, ContactFormat.Columns.PHONE,
                             ContactFormat.Columns.E_MAIL},
                new int[]{R.id.txtContactName_insert, R.id.txtPhone_insert, R.id.txtEmail_insert},
                0
        );
        view.setAdapter(listAdapter);
    }

    //@Override
    public boolean onLongClick(View v) {
        LayoutInflater inflater2 = this.getLayoutInflater();
        View addressBook = inflater2.inflate(R.layout.list_layout, null);
        final TextView contactView = (TextView) addressBook.findViewById(R.id.txtContactName_insert);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Delete Contact");
        alertBuilder.setMessage("Are You Sure?");
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deleteContact = contactView.getText().toString();

                String sql = String.format("DELETE FROM %s WHERE %s = '%s",
                        ContactFormat.TABLE,
                        ContactFormat.Columns.CONTACT_NAME, deleteContact);

                cHelper = new ContactsDataBaseHelper(MainActivity.this);
                SQLiteDatabase sqlDB = cHelper.getWritableDatabase();
                sqlDB.execSQL(sql);
                updateUI();

            }
        });
        alertBuilder.setNegativeButton("Cancel", null);
        alertBuilder.create().show();
        return true;
        }
    }
