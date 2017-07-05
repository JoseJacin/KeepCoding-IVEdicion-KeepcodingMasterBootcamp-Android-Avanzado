package com.josejacin.madridshops.domain.managers.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.josejacin.domain.R;
import com.josejacin.madridshops.domain.managers.network.entities.ActivityEntity;
import com.josejacin.madridshops.domain.managers.network.jsonparser.ActivitiesJsonParser;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllActivitiesManagerImpl implements ActivitiesNetworkManager {

    WeakReference<Context> weakContext;

    public GetAllActivitiesManagerImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }

    // Método que descarga información de red
    @Override
    public void getActivitiesFromServer(@NonNull final GetAllActivitiesManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion) {
        String url = weakContext.get().getString(R.string.activities_url);

        // Se inicializa Volley
        // Se crea una cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(weakContext.get());
        // Se conecta a la url
        // Los parámetros son los siguientes
        // url: Url a la que se quiere conectar
        // Response.Listener: Listener que se ejecute cuando la descarga ha ido correctamente
        // Response.ErrorListener: Listener de error que se ejecute cuando ha ocurrido un error en la descarga
        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);

                        // Se crea un objeto de tipo ShopsJsonParser
                        ActivitiesJsonParser parser = new ActivitiesJsonParser();
                        List<ActivityEntity> entities = parser.parse(response);

                        // Se retorna la información en el bloque de completion
                        if (completion != null) {
                            completion.completion(entities);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", error.toString());
                        if (errorCompletion != null) {
                            errorCompletion.onError(error.getMessage());
                        }
                    }
                }
        );
        // Se añade la petición a la cola
        queue.add(request);
    }
}
