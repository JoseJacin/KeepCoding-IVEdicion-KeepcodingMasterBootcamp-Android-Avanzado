package com.josejacin.madridshops.domain.model;

import java.util.List;

public class Activities implements ActivitiesIterable, ActivitiesUpdatable {
    // Properties

    @Override
    public long size() {
        return 0;
    }

    @Override
    public Activity get(long index) {
        return null;
    }

    @Override
    public List<Activity> allActivities() {
        return null;
    }

    @Override
    public void add(Activity activity) {

    }

    @Override
    public void delete(Activity activity) {

    }

    @Override
    public void update(Activity newActivity, long index) {

    }
}
