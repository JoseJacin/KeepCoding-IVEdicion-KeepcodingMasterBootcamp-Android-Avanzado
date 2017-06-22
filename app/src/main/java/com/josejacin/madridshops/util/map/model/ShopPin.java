package com.josejacin.madridshops.util.map.model;

import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.util.map.MapPinnable;

import java.util.ArrayList;
import java.util.List;

public class ShopPin implements MapPinnable<Shop> {
    private Shop shop;

    public static List<MapPinnable> shopPinsFromShops(Shops shops) {
        List<Shop> shopList = shops.allShops();
        List<MapPinnable> shopPinList = new ArrayList<>();

        for (Shop shop : shopList) {
            ShopPin sp = new ShopPin(shop);
            shopPinList.add(sp);
        }

        return shopPinList;
    }

    public ShopPin(Shop shop) {
        this.shop = shop;
    }

    @Override
    public float getLatitude() {
        return shop.getLatitude();
    }

    @Override
    public float getLongitude() {
        return shop.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return shop.getName();
    }

    @Override
    public String getPinImageUrl() {
        return shop.getLogoUrl();
    }

    @Override
    public Shop getRelatedModelObject() {
        return shop;
    }
}
