package com.example.grissgarcia.climabol;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.internal.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by Griss Garcia on 09/07/2015.
 */
public class MainActivity extends Activity {

    private final static int BUTTON_SIZE = 9;
    private Button[] btnDepartamentos;
    private TextView departamentoTitulo;
    private TextView actual;
    private TextView max;
    private TextView min;
    private TextView humedad;
    private TextView presion;
    private LayoutInflater inflater;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);

        btnDepartamentos = new Button[BUTTON_SIZE];


        btnDepartamentos[0] = (Button) findViewById(R.id.btn_cbba);
        btnDepartamentos[1] = (Button) findViewById(R.id.btn_lpz);
        btnDepartamentos[2] = (Button) findViewById(R.id.btn_stcz);
        btnDepartamentos[3] = (Button) findViewById(R.id.btn_Beni);
        btnDepartamentos[4] = (Button) findViewById(R.id.btn_Tarija);
        btnDepartamentos[5] = (Button) findViewById(R.id.btn_Cuquisaca);
        btnDepartamentos[6] = (Button) findViewById(R.id.btn_Pando);
        btnDepartamentos[7] = (Button) findViewById(R.id.btn_Oruro);
        btnDepartamentos[8] = (Button) findViewById(R.id.btn_Potosi);

        for (Button btn : btnDepartamentos) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button pressed = (Button) view;
                    for (Button btn : btnDepartamentos) {
                        if (pressed == btn) {
                            final String actualCity = btn.getText().toString();
                            new HttpGetWeather(actualCity).execute();

                        }
                    }
                }
            });
        }
        inflater = this.getLayoutInflater();
    }

    private class HttpGetWeather extends AsyncTask<Void, Void, Clima> {

        private final String city;
        private final String url;

        public HttpGetWeather(String city) {
            this.city = city;
            url = "http://api.openweathermap.org/data/2.5/weather?q=" + this.city + ",Bo";
        }

        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Clima doInBackground(Void... params) {
            HttpGet request = new HttpGet(url);
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
        protected void onPostExecute(Clima result) {
            if (null != mClient) {
                mClient.close();
            }
            final AlertDialog.Builder message = new AlertDialog.Builder(MainActivity.this);
            message.setTitle("Climabol");
            View messageView = inflater.inflate(R.layout.message_layout, null);

            departamentoTitulo = (TextView) messageView.findViewById(R.id.txt_title_view);
            actual = (TextView) messageView.findViewById(R.id.txt_climaActual_view);
            max = (TextView) messageView.findViewById(R.id.txt_Max_view);
            min = (TextView) messageView.findViewById(R.id.txt_Min_view);
            humedad = (TextView) messageView.findViewById(R.id.txt_Humedad_view);
            presion = (TextView) messageView.findViewById(R.id.txt_Pression_view);

            departamentoTitulo.setText(city);

            actual.setText("Clima Actual: " + result.getStringActual());
            max.setText("Max: " + result.getStringMax());
            min.setText("Min: " + result.getStringMin());
            humedad.setText("Humedad: " + result.getStringHumedad());
            presion.setText("Presion: " + result.getStringPresion());
            message.setView(messageView);
            message.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            message.create().show();
        }
    }
}
