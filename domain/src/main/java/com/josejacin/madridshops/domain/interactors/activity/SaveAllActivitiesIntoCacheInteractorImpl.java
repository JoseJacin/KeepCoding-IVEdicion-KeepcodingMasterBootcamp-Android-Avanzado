package com.josejacin.madridshops.domain.interactors.activity;

import com.josejacin.madridshops.domain.interactors.shop.SaveAllShopsIntoCacheInteractor;
import com.josejacin.madridshops.domain.managers.cache.activity.SaveAllActivitiesIntoCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.SaveAllShopsIntoCacheManager;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Shops;

public class SaveAllActivitiesIntoCacheInteractorImpl implements SaveAllActivitiesIntoCacheInteractor {

    private SaveAllActivitiesIntoCacheManager manager;

    public SaveAllActivitiesIntoCacheInteractorImpl(SaveAllActivitiesIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Activities activities, Runnable completion) {
        manager.execute(activities, completion);
    }
}
