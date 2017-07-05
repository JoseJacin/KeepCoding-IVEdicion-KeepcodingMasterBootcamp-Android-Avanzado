package com.josejacin.madridshops.domain.managers.network.jsonparser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josejacin.madridshops.domain.managers.network.entities.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class ActivitiesJsonParser {
    public List<ActivityEntity> parse(@NonNull final String response) {
        if (response == null) {
            return null;
        }

        List<ActivityEntity> activityEntities = null;

        try {
            // Se construye un parseador de JSON
            Gson gson = new GsonBuilder().create();

            // Se crea un Reader, qe lee el JSON línea a línea
            Reader reader = new StringReader(response);
            ActivitiesResponseEntity activitiesResponseEnity = gson.fromJson(reader, ActivitiesResponseEntity.class);
            activityEntities = activitiesResponseEnity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activityEntities;
    }
}
