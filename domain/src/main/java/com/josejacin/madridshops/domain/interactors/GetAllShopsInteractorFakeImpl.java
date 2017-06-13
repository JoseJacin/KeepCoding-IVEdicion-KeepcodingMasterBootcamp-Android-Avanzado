package com.josejacin.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

public class GetAllShopsInteractorFakeImpl implements GetAllShopsInteractor {
    @Override
    public void execute(@NonNull GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i);
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }
    }
}
