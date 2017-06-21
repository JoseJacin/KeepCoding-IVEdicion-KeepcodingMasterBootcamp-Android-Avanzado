package com.josejacin.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.model.Shops;

import java.util.List;

public interface GetAllShopsManagerCompletion {
    void completion(@NonNull final List<ShopEntity> shopEntities);
}
