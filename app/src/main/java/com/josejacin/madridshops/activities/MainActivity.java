package com.josejacin.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.ClearCacheInteractor;
import com.josejacin.madridshops.domain.interactors.ClearCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.activity.SetAllActivitiesAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.activity.SetAllActivitiesAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.managers.cache.ClearCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.ClearCacheManagerDAOImpl;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.util.MainThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__shops_button) Button shopsButton;
    @BindView(R.id.activity_main__activities_button) Button activitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        shopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getCanonicalName(), "Hello");

                Navigator.navigateFromMainActivityToShopListActivity(MainActivity.this);
            }
        });

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getCanonicalName(), "Hello activities");

                Navigator.navigateFromMainActivityToActivityListActivity(MainActivity.this);
            }
        });

        launchInBackgroundThread();
    }

    private  void launchInBackgroundThread() {
        // onPreExecute (Antes de lanzar el hilo)

        // Se crea el hilo
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { // doInBackground (Mientras se ejecuta el hilo)
                Log.d("Hilo", Thread.currentThread().getName());
                final String s = testMultiThread();

                // onPostExecute (Después de la ejecución del hilo)

                // Volver al hilo principal
                // Método 1 (Cuando se encuentra dentro de una actividad)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        shopsButton.setText(s);
                    }
                });

                // Volver al hilo principal
                // Método 2 (Cuando no se encuentra dentro de una actividad)
                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        shopsButton.setText(s);
                    }
                });
            }
        });

        // Se arranca el hilo
        thread.start();
    }

    private String testMultiThread() {
        final String web = "http://freegeoip.net/json/";
        String result = null;
        try {
            URL url = new URL(web);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            InputStream is = (InputStream) request.getContent();
            result = streamToString(is);
            Log.d("Web", result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    String streamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for(String line = br.readLine(); line != null; line = br.readLine())
            out.append(line);
        br.close();
        return out.toString();
    }

    // Esto es lo mismo que usar @BindView y crear un setOnClickListener
    @OnClick(R.id.activity_main__clear_cache_button) void clearCache() {
        ClearCacheManager clearCacheManager = new ClearCacheManagerDAOImpl(this);
        ClearCacheInteractor clearCacheInteractor = new ClearCacheInteractorImpl(clearCacheManager);
        clearCacheInteractor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        // Se establece el indicador de que las Shops ya se han almacenado en BBDD a false
                        SetAllShopsAreCacheInteractor setAllShopsAreCachedInteractor = new SetAllShopsAreCacheInteractorImpl(getBaseContext());
                        setAllShopsAreCachedInteractor.execute(false);

                        // Se establece el indicador de que las Activities ya se han almacenado en BBDD a false
                        SetAllActivitiesAreCacheInteractor setAllActivitiesAreCachedInteractor = new SetAllActivitiesAreCacheInteractorImpl(getBaseContext());
                        setAllActivitiesAreCachedInteractor.execute(false);
                    }
                }
        );
    }
}
