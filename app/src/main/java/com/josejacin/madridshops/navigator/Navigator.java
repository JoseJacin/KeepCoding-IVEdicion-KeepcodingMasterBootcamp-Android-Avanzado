package com.josejacin.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.josejacin.madridshops.activities.ActivityDetailActivity;
import com.josejacin.madridshops.activities.MainActivity;
import com.josejacin.madridshops.activities.ShopDetailActivity;
import com.josejacin.madridshops.activities.ShopListActivity;
import com.josejacin.madridshops.activities.ActivityListActivity;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;

import static com.josejacin.madridshops.util.Constants.INTENT_ACTIVITY_DETAIL;
import static com.josejacin.madridshops.util.Constants.INTENT_SHOP_DETAIL;

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
        i.putExtra(INTENT_SHOP_DETAIL, shop);
        shopListActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromMainActivityToActivityListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ActivityListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromActivityListActivityToActivityDetailActivity(@NonNull final ActivityListActivity activityListActivity, final Activity activity, final int position) {
        final Intent i = new Intent(activityListActivity, ActivityDetailActivity.class);
        // Se añade al intent la Shop
        i.putExtra(INTENT_ACTIVITY_DETAIL, activity);
        activityListActivity.startActivity(i);

        return i;
    }
}
