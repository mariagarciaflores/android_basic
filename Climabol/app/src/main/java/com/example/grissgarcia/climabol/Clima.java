package com.example.grissgarcia.climabol;

import java.text.DecimalFormat;

/**
 * Created by Griss Garcia on 09/07/2015.
 */
public class Clima {
    private DecimalFormat formateador;
    private double temp_actual;
    private double temp_max;
    private double temp_min;
    private double humedad;
    private double presion;

    public Clima() {
        formateador = new DecimalFormat("########.##");

    }

    public void setTemp_actual(double temp_actual) {
        this.temp_actual = temp_actual;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public String getStringActual(){
        return formateador.format(temp_actual - 273.15)+" C";
    }

    public String getStringMax(){
        return formateador.format(temp_max - 273.15)+" C";
    }

    public String getStringMin(){

        return formateador.format(temp_min - 273.15)+" C";
    }

    public String getStringHumedad(){

        return humedad + " %";
    }

    public String getStringPresion(){

        return presion + " hpa";
    }
}
