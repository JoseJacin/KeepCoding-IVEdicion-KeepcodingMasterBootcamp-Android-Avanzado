package com.josejacin.madridshops.util.map.model;

import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.util.map.MapPinnable;

import java.util.ArrayList;
import java.util.List;

public class ActivityPin implements MapPinnable<Activity> {
    private Activity activity;

    public static List<MapPinnable> activityPinsFromActivities(Activities activities) {
        List<Activity> activityList = activities.allActivities();
        List<MapPinnable> activityPinList = new ArrayList<>();

        for (Activity activity : activityList) {
            ActivityPin ap = new ActivityPin(activity);
            activityPinList.add(ap);
        }

        return activityPinList;
    }

    public ActivityPin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public float getLatitude() {
        return activity.getLatitude();
    }

    @Override
    public float getLongitude() {
        return activity.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return activity.getName();
    }

    @Override
    public String getPinImageUrl() {
        return activity.getLogoUrl();
    }

    @Override
    public Activity getRelatedModelObject() {
        return activity;
    }
}
