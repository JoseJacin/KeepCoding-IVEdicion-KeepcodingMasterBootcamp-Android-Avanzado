package com.josejacin.madridshops.domain.interactors.shop;

import com.josejacin.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);
}
