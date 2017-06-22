package com.josejacin.madridshops.domain.managers.cache;

import android.content.Context;

import com.josejacin.madridshops.domain.managers.db.ShopDAO;

import java.lang.ref.WeakReference;

public class ClearCacheManagerDAOImpl implements ClearCacheManager {
    private WeakReference<Context> contextWeakReference;

    public ClearCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Runnable completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        dao.deleteAll();
        completion.run();
    }
}
