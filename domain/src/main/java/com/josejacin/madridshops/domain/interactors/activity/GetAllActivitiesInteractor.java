package com.josejacin.madridshops.domain.interactors.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;

public interface GetAllActivitiesInteractor {
    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion,
                        @Nullable final InteractorErrorCompletion onError);
}
