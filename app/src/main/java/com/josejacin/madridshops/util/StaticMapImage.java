package com.josejacin.madridshops.util;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;

public class StaticMapImage {
    public static String getMapImageUrlToShop(@NonNull final Shop shop) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220", shop.getLatitude(), shop.getLongitude());

        return url;
    }

    public static String getMapImageUrlToActivity(@NonNull final Activity activity) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220", activity.getLatitude(), activity.getLongitude());

        return url;
    }
}
