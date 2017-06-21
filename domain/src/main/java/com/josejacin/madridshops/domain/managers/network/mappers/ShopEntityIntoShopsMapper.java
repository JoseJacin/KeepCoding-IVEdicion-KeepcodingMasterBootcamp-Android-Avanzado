package com.josejacin.madridshops.domain.managers.network.mappers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import java.util.List;

public class ShopEntityIntoShopsMapper {
    // Método que mapea una lista de ShopEntity a un Shops

    /**
     * @param shopEntities List of a shopEntities
     * @return null is shopEntities is null or shopEntities is empty else a Shops aggregate
     */
    public static Shops map(@NonNull final List<ShopEntity> shopEntities) {
        Shops shops = new Shops();

        for (ShopEntity shopEntity : shopEntities) {
            Shop shop = Shop.of(shopEntity.getId(), shopEntity.getName());

            shop.setDescription(shopEntity.getDescription_es());
            shop.setLatitude(getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            shop.setLongitude(getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            shop.setAddress(shopEntity.getAddress());
            shop.setUrl(shopEntity.getUrl());
            shop.setImageUrl(shopEntity.getImg());
            shop.setLogoUrl(shopEntity.getLogo_img());

            shops.add(shop);
        }

        return shops;
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
