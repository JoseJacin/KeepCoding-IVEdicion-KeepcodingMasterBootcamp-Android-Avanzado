package com.josejacin.madridshops.domain.interactors;

import com.josejacin.madridshops.domain.managers.cache.ClearCacheManager;

public class ClearCacheInteractorImpl implements ClearCacheInteractor {
    private ClearCacheManager manager;

    public ClearCacheInteractorImpl(ClearCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Runnable completion) {
        manager.execute(completion);
    }
}
