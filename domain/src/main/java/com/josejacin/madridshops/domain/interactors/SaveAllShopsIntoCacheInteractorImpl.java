package com.josejacin.madridshops.domain.interactors;

import com.josejacin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.josejacin.madridshops.domain.model.Shops;

public class SaveAllShopsIntoCacheInteractorImpl  implements SaveAllShopsIntoCacheInteractor {

    private SaveAllShopsIntoCacheManager manager;

    public SaveAllShopsIntoCacheInteractorImpl(SaveAllShopsIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);
    }
}
