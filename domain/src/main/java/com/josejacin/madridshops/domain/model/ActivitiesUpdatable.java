package com.josejacin.madridshops.domain.model;

public interface ActivitiesUpdatable {
    void add(Activity activity);
    void delete(Activity activity);
    void update(Activity newActivity, long index);
}
