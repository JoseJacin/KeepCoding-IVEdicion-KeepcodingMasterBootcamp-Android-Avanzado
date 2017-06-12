package com.josejacin.madridshops;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    // Método que se dispara cuando la aplicación se crea y se lanza
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(APP_NAME, "App starting");

    }

    // Método que se dispara cuando hay poca memoria
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
