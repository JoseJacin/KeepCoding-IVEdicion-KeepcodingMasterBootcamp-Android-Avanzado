package com.josejacin.madridshops.domain.managers.cache.shop;

import com.josejacin.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheManager {
    void execute(Shops shops, Runnable completion);
}
