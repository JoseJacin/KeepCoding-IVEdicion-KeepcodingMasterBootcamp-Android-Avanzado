package com.josejacin.madridshops.domain.managers.cache.activity;

import android.content.Context;

import com.josejacin.madridshops.domain.managers.cache.shop.SaveAllShopsIntoCacheManager;
import com.josejacin.madridshops.domain.managers.db.ActivityDAO;
import com.josejacin.madridshops.domain.managers.db.ShopDAO;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;

public class SaveAllActivitiesIntoCacheManagerDAOImpl implements SaveAllActivitiesIntoCacheManager {
    private WeakReference<Context> contextWeakReference;

    public SaveAllActivitiesIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Activities activities, Runnable completion) {
        ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
        for (Activity activity : activities.allActivities()) {
            dao.insert(activity);
        }
        completion.run();
    }
}
