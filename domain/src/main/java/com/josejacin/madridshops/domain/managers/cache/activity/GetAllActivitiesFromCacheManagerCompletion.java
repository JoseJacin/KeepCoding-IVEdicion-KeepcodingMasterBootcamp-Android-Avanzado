package com.josejacin.madridshops.domain.managers.cache.activity;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Shops;

// Actuaría como un bloque de finalización
public interface GetAllActivitiesFromCacheManagerCompletion {
    void completion(@NonNull final Activities activities);
}
