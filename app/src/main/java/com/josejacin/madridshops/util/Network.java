package com.josejacin.madridshops.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.lang.ref.WeakReference;

public class Network {
    private WeakReference<Context> context;

    public Network(Context context) {
        this.context = new WeakReference<Context>(context);
    }

//https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    public boolean isOnline() {
        ConnectivityManager cm =  (ConnectivityManager) context.get().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
