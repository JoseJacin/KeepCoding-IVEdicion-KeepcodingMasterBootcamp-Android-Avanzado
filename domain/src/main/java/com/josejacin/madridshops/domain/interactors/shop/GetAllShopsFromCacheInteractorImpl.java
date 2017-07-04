package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManagerCompletion;
import com.josejacin.madridshops.domain.model.Shops;

public class GetAllShopsFromCacheInteractorImpl implements GetAllShopsFromCacheInteractor {

    private GetAllShopsFromCacheManager cacheManager;

    public GetAllShopsFromCacheInteractorImpl(final GetAllShopsFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion) {
        cacheManager.execute(
                new GetAllShopsFromCacheManagerCompletion() {
                    @Override
                    public void completion(@NonNull Shops shops) {
                        completion.completion(shops);
                    }
                }
        );
    }
}
