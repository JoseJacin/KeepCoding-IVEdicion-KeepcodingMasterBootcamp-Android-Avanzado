package com.josejacin.madridshops.domain.managers.cache.activity;

import com.josejacin.madridshops.domain.model.Activities;

public interface SaveAllActivitiesIntoCacheManager {
    void execute(Activities activities, Runnable completion);
}
