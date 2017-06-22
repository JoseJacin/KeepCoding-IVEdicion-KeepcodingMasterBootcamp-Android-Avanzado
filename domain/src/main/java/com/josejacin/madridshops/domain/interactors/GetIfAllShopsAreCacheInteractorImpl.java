package com.josejacin.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetIfAllShopsAreCacheInteractorImpl implements GetIfAllShopsAreCacheInteractor {
    private WeakReference<Context> context;

    public GetIfAllShopsAreCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAeNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopsSaved = preferences.getBoolean(SetAllShopsAreCacheInteractor.SHOPS_SAVED, false);

        if (shopsSaved) {
            onAllShopsAreCached.run();
        } else {
            onAllShopsAeNotCached.run();
        }
    }
}
