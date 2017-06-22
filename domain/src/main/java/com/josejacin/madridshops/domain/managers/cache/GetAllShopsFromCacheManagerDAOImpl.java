package com.josejacin.madridshops.domain.managers.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.db.ShopDAO;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllShopsFromCacheManagerDAOImpl implements GetAllShopsFromCacheManager {

    private WeakReference<Context> contextWeakReference;

    public GetAllShopsFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final GetAllShopsFromCacheManagerCompletion completion) {
        // Se levanta otro hilo distinto al principal para acceder a BDD
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(contextWeakReference.get());
                List<Shop> shopList = dao.query();
                if (shopList == null) {
                    return;
                }
                final Shops shops = Shops.from(shopList);

                // Se accede al hilo principal
                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });
            }
        }).start();
    }
}
