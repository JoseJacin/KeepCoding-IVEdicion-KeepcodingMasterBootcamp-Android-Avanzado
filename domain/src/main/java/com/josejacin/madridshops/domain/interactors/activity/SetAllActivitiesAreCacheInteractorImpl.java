package com.josejacin.madridshops.domain.interactors.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractor;

import java.lang.ref.WeakReference;

public class SetAllActivitiesAreCacheInteractorImpl implements SetAllActivitiesAreCacheInteractor {
    private WeakReference<Context> context;

    public SetAllActivitiesAreCacheInteractorImpl(Context context) {
       this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean activitiesSaved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SetAllActivitiesAreCacheInteractorImpl.ACTIVITIES_SAVED, activitiesSaved);

        editor.commit();
    }
}
