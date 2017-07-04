package com.josejacin.madridshops.domain.managers.cache.shop;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.model.Shops;

// Actuaría como un bloque de finalización
public interface GetAllShopsFromCacheManagerCompletion {
    void completion(@NonNull final Shops shops);
}
