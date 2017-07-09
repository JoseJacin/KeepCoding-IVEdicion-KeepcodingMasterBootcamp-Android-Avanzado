package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.model.Shops;

// Actuaría como un bloque de finalización
public interface GetAllShopsInteractorCompletion {
    void completion(@NonNull final Shops shops);
}
