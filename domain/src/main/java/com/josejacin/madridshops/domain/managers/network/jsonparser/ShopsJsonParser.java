package com.josejacin.madridshops.domain.managers.network.jsonparser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.managers.network.entities.ShopsResponseEnity;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class ShopsJsonParser {
    public List<ShopEntity> parse(@NonNull final String response) {
        if (response == null) {
            return null;
        }

        List<ShopEntity> shopEntities = null;

        try {
            // Se construye un parseador de JSON
            Gson gson = new GsonBuilder().create();

            // Se crea un Reader, qe lee el JSON línea a línea
            Reader reader = new StringReader(response);
            ShopsResponseEnity shopsResponseEnity = gson.fromJson(reader, ShopsResponseEnity.class);
            shopEntities = shopsResponseEnity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shopEntities;
    }
}
