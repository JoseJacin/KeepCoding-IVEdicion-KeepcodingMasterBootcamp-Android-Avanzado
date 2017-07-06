package com.josejacin.madridshops.domain.managers.cache.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.db.ActivityDAO;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllActivitiesFromCacheManagerDAOImpl implements GetAllActivitiesFromCacheManager {

    private WeakReference<Context> contextWeakReference;

    public GetAllActivitiesFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final GetAllActivitiesFromCacheManagerCompletion completion) {
        ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
        List<Activity> activityList = dao.query();
        if (activityList == null) {
            return;
        }

        Activities activities = Activities.from(activityList);
        completion.completion(activities);
    }
}
