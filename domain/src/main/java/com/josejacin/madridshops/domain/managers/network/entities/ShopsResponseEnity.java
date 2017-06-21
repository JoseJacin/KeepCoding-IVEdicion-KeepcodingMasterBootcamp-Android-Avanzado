package com.josejacin.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopsResponseEnity {
    @SerializedName("result") private List<ShopEntity> result;

    //Getters (Solo se crean Getters para que sean inmutables y no se puedan cambiar, ni siquiera por error)
    public List<ShopEntity> getResult() {
        return result;
    }
}
