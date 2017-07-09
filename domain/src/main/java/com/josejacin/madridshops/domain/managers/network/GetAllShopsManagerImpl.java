package com.josejacin.madridshops.domain.managers.network;

import android.content.Context;
import android.support.annotation.*;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.josejacin.domain.R;
import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.managers.network.jsonparser.ShopsJsonParser;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllShopsManagerImpl implements ShopsNetworkManager {

    WeakReference<Context> weakContext;

    public GetAllShopsManagerImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }

    // Método que descarga información de red
    @Override
    public void getShopsFromServer(@NonNull final GetAllShopsManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion) {
        String url = weakContext.get().getString(R.string.shops_url);

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
                        ShopsJsonParser parser = new ShopsJsonParser();
                        List<ShopEntity> entities = parser.parse(response);

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
