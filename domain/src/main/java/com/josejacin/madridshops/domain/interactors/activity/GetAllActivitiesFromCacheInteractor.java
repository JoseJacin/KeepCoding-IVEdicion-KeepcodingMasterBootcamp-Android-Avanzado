package com.josejacin.madridshops.domain.interactors.activity;

import android.support.annotation.NonNull;

public interface GetAllActivitiesFromCacheInteractor {
    void execute(@NonNull final GetAllActivitiesInteractorCompletion completion);

}
