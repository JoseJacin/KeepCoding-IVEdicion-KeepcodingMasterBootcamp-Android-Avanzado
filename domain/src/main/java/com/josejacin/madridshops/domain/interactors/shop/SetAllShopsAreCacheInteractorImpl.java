package com.josejacin.madridshops.domain.interactors.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class SetAllShopsAreCacheInteractorImpl implements SetAllShopsAreCacheInteractor {
    private WeakReference<Context> context;

    public SetAllShopsAreCacheInteractorImpl(Context context) {
       this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean shopsSaved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SetAllShopsAreCacheInteractorImpl.SHOPS_SAVED, shopsSaved);

        editor.commit();
    }
}
