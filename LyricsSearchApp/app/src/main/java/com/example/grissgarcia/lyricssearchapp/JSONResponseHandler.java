package com.example.grissgarcia.lyricssearchapp;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class JSONResponseHandler implements ResponseHandler<Lyric> {

    private static final String ARTIST_NAME = "artist";
    private static final String SONG_NAME = "song";
    private static final String LYRIC = "lyrics";
    private static final String URL = "url";

    @Override
    public Lyric handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        Lyric result = new Lyric();
        String JSONResponse = new BasicResponseHandler().handleResponse(httpResponse);
        try {
            JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();

            result.setArtistName(responseObject.getString(ARTIST_NAME));
            result.setSongName(responseObject.getString(SONG_NAME));
            result.setLyric(responseObject.getString(LYRIC));
            result.setLyricUrl(responseObject.getString(URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
