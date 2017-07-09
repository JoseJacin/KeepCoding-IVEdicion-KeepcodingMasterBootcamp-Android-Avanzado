package com.josejacin.madridshops.domain.interactors.activity;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsFromCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManagerCompletion;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManagerCompletion;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Shops;

public class GetAllActivitiesFromCacheInteractorImpl implements GetAllActivitiesFromCacheInteractor {

    private GetAllActivitiesFromCacheManager cacheManager;

    public GetAllActivitiesFromCacheInteractorImpl(final GetAllActivitiesFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion) {
        cacheManager.execute(
                new GetAllActivitiesFromCacheManagerCompletion() {
                    @Override
                    public void completion(@NonNull Activities activities) {
                        completion.completion(activities);
                    }
                }
        );
    }
}
