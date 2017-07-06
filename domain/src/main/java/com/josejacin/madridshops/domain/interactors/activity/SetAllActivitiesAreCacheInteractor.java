package com.josejacin.madridshops.domain.interactors.activity;

public interface SetAllActivitiesAreCacheInteractor {
    public static final String ACTIVITIES_SAVED = "ACTIVITIES_SAVED";

    void execute(boolean activitiesSaved);
}
