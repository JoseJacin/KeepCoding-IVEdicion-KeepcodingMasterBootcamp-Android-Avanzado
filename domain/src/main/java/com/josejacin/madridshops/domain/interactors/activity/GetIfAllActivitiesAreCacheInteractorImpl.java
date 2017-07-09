package com.josejacin.madridshops.domain.interactors.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.josejacin.madridshops.domain.interactors.shop.GetIfAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractor;

import java.lang.ref.WeakReference;

public class GetIfAllActivitiesAreCacheInteractorImpl implements GetIfAllActivitiesAreCacheInteractor {
    private WeakReference<Context> context;

    public GetIfAllActivitiesAreCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean activitiesSaved = preferences.getBoolean(SetAllActivitiesAreCacheInteractor.ACTIVITIES_SAVED, false);

        if (activitiesSaved) {
            onAllActivitiesAreCached.run();
        } else {
            onAllActivitiesAreNotCached.run();
        }
    }
}
