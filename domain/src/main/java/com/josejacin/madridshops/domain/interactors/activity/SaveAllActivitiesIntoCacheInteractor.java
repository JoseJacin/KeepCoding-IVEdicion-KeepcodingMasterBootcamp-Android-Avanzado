package com.josejacin.madridshops.domain.interactors.activity;

import com.josejacin.madridshops.domain.model.Activities;

public interface SaveAllActivitiesIntoCacheInteractor {
    void execute(Activities activities, Runnable completion);
}
