package com.josejacin.madridshops.domain.interactors.activity;

public interface GetIfAllActivitiesAreCacheInteractor {
    void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached);
}
