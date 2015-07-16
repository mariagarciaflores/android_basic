package com.example.grissgarcia.lyricssearchapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class MainActivity extends Activity {

    private EditText artistName;
    private EditText songTitle;
    private String artist;
    private String song;
    private TextView partOfLyric;
    private ImageButton previousBtn;
    private Helper cHelper;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_lyric_layout);

        artistName = (EditText) findViewById(R.id.artistName_editabletxt);
        songTitle = (EditText) findViewById(R.id.singleTitle_editabletxt);
        partOfLyric = (TextView) findViewById(R.id.txtView_previous);

        previousBtn = (ImageButton) findViewById(R.id.previous_btn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist = artistName.getText().toString().replace(" ", "_");
                song = songTitle.getText().toString().replace(" ", "_");
                new HttpGetLyrics(artist, song).execute();
            }
        });

    }

    public void searchCompleteLyric(View view) {
        Intent i = new Intent(this, LyricWebSite.class);
        artist = artistName.getText().toString().replace(" ", "_");
        song = songTitle.getText().toString().replace(" ", "_");
        String urlLyric = "http://lyrics.wikia.com/" + artist + ":" + song;
        i.putExtra("direccion", urlLyric);
        startActivity(i);
    }

    public void saveIntheBaseDate(View view) {
        Intent s = new Intent(this, DataBaseMain.class);
        artist = artistName.getText().toString();
        song = songTitle.getText().toString();

        cHelper = new Helper(this);

        SQLiteDatabase db = cHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put(Format.Columns.ARTIST_NAME, artist);
        values.put(Format.Columns.SONG, song);
        db.insertWithOnConflict(Format.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        startActivity(s);
    }

    private  class HttpGetLyrics extends AsyncTask<Void, Void, Lyric> {

        private final String artistN;
        private final String songN;
        private final String urlLyric;

        public  HttpGetLyrics(String artistN, String songN) {
            this.artistN = artistN;
            this.songN = songN;
            urlLyric = "http://lyrics.wikia.com/api.php?artist="+ this.artistN + "&song=" + this.songN + "&fmt=realjson";
        }

        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Lyric doInBackground(Void... params) {
            HttpGet request = new HttpGet(urlLyric);
            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                return mClient.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Lyric result) {
            if (null != mClient) {
                mClient.close();
            }

            partOfLyric.setText(result.getLyric());
        }
    }

}
