package com.josejacin.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.managers.network.entities.ActivityEntity;

import java.util.List;

public interface GetAllActivitiesManagerCompletion {
    void completion(@NonNull final List<ActivityEntity> activityEntities);
}
