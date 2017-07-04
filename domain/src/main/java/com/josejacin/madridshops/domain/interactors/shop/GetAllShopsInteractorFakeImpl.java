package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

public class GetAllShopsInteractorFakeImpl implements GetAllShopsInteractor {
    @Override
    public void execute(@NonNull GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i)
                    .setLogoUrl("http://www.iconarchive.com/download/i77853/custom-icon-design/pretty-office-11/shop.ico");
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }
    }
}
