package com.example.grissgarcia.climabol;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Griss Garcia on 09/07/2015.
 */
public class JSONResponseHandler implements ResponseHandler<Clima> {

    private static final String WEATHER = "main";
    private static final String CLIMA_ACTUAL = "temp";
    private static final String MAXIMO = "temp_max";
    private static final String MINIMO = "temp_min";
    private static final String HUMEDAD = "humidity";
    private static final String PRESION = "pressure";


    @Override
    public Clima handleResponse(HttpResponse httpResponse)
            throws ClientProtocolException, IOException {
        Clima result = new Clima ();
        String JSONResponse = new BasicResponseHandler().handleResponse(httpResponse);

        try {
            JSONObject responseObject = (JSONObject) new JSONTokener(
                    JSONResponse).nextValue();

            JSONObject weather = responseObject.getJSONObject(WEATHER);
            result.setTemp_actual(weather.getDouble(CLIMA_ACTUAL));
            result.setTemp_max(weather.getDouble(MAXIMO));
            result.setTemp_min(weather.getDouble(MINIMO));
            result.setHumedad(weather.getDouble(HUMEDAD));
            result.setPresion(weather.getDouble(PRESION));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

