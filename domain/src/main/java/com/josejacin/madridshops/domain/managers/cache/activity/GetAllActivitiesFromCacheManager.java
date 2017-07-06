package com.josejacin.madridshops.domain.managers.cache.activity;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManagerCompletion;

public interface GetAllActivitiesFromCacheManager {
    void execute(@NonNull final GetAllActivitiesFromCacheManagerCompletion completion);

}
