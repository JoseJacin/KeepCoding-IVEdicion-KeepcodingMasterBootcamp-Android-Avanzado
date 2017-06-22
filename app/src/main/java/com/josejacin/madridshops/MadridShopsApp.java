package com.josejacin.madridshops;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.josejacin.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.NetworkManager;
import com.josejacin.madridshops.domain.model.Shops;
import com.squareup.picasso.Picasso;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    // Método que se dispara cuando la aplicación se crea y se lanza
    @Override
    public void onCreate() {
        super.onCreate();



        Log.d(APP_NAME, "App starting" + BuildConfig.BASE_URL);

        // Activa los log de Picasso
        //Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        // Activa los indicadores de Picasso
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }

    // Método que se dispara cuando hay poca memoria
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
