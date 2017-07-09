package com.josejacin.madridshops.domain.managers.network.mappers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.josejacin.madridshops.domain.managers.network.entities.ActivityEntity;
import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import java.util.List;

public class ActivityEntityIntoActivitiesMapper {
    // Método que mapea una lista de ActivityEntity a un Activities

    /**
     * @param activityEntities List of a activityEntities
     * @return null is activityEntities is null or activityEntities is empty else a Activities aggregate
     */
    public static Activities map(@NonNull final List<ActivityEntity> activityEntities) {
        Activities activities = new Activities();

        for (ActivityEntity activityEntity : activityEntities) {
            Activity activity = Activity.of(activityEntity.getId(), activityEntity.getName());

            activity.setDescription(activityEntity.getDescription_es());
            activity.setLatitude(getCorrectCoordinateComponent(activityEntity.getGps_lat()));
            activity.setLongitude(getCorrectCoordinateComponent(activityEntity.getGps_lon()));
            activity.setAddress(activityEntity.getAddress());
            activity.setUrl(activityEntity.getUrl());
            activity.setImageUrl(activityEntity.getImg());
            activity.setLogoUrl(activityEntity.getLogo_img());

            activities.add(activity);
        }

        return activities;
    }

    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // Problem: JSON has this errors "-3.9018104,275"
        float coordinate = 0.0f; //TODO: Esto debería ser una coordenada por defecto de, por ejemplo, el centro de Madrid

        String s = coordinateComponent.replace(",","");
        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }

        return coordinate;
    }
}
