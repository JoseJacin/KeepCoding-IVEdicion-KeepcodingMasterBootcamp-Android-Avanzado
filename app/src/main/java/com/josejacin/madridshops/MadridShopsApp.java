package com.josejacin.madridshops;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.josejacin.madridshops.activities.ShopListActivity;
import com.josejacin.madridshops.services.ShopService;
import com.squareup.picasso.Picasso;

public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    // Método que se dispara cuando la aplicación se crea y se lanza
    @Override
    public void onCreate() {
        super.onCreate();

        // Init app
        Log.d(APP_NAME, "App starting" + BuildConfig.BASE_URL);

        // Activa los log de Picasso
        //Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        // Activa los indicadores de Picasso
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        //Intent i = new Intent(getApplicationContext(), ShopService.class);

        //PendingIntent pendingIntent = PendingIntent.getService(getBaseContext(), 0, i, 0);

        //ShopService.startRunningService(this);

        /*
        //getService(context, idOfWhatImDoing, intent, flag)
        // where flag == FLAG_ONE_SHOPT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, FLAG_IMMUTABLE

        // Get a reference to the AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Start the service every 5 seconds (5 * 1000)
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 1000 * 5, pendingIntent);

        startService(i);
        startService(i);
        */

        /*
        Resources resources = getResources();
        Intent intent = new Intent(this, ShopListActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Ticker")    // Set the "ticker" text which is sent to accessibility services.
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("You're spyed now")
                .setContentText("Notification Text")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 0;
        notificationManager.notify(notificationId, notification);
        */
    }

    // Método que se dispara cuando hay poca memoria
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
