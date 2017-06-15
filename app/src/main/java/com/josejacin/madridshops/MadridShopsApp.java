package com.josejacin.madridshops;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.squareup.picasso.Picasso;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    // Método que se dispara cuando la aplicación se crea y se lanza
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(APP_NAME, "App starting");

        // Activa los log de Picasso
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        // Activa los indicadores de Picasso
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }

    // Método que se dispara cuando hay poca memoria
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
