package com.josejacin.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.josejacin.madridshops.activities.MainActivity;
import com.josejacin.madridshops.activities.ShopDetailActivity;
import com.josejacin.madridshops.activities.ShopListActivity;
import com.josejacin.madridshops.domain.model.Shop;

import static com.josejacin.madridshops.util.Constants.INTENT_SHOPS_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        // Se añade al intent la Shop
        i.putExtra(INTENT_SHOPS_DETAIL, shop);
        shopListActivity.startActivity(i);

        return i;
    }
}
