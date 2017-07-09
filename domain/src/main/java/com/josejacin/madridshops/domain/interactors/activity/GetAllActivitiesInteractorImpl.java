package com.josejacin.madridshops.domain.interactors.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.ActivitiesNetworkManager;
import com.josejacin.madridshops.domain.managers.network.GetAllActivitiesManagerCompletion;
import com.josejacin.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.entities.ActivityEntity;
import com.josejacin.madridshops.domain.managers.network.mappers.ActivityEntityIntoActivitiesMapper;
import com.josejacin.madridshops.domain.model.Activities;

import java.util.List;

public class GetAllActivitiesInteractorImpl implements GetAllActivitiesInteractor {
    private ActivitiesNetworkManager activitiesNetworkManager;

    public GetAllActivitiesInteractorImpl(@NonNull final ActivitiesNetworkManager activitiesNetworkManager) {
        this.activitiesNetworkManager = activitiesNetworkManager;
    }

    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        if (this.activitiesNetworkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.onError("Error en la conexión"); //TODO: Esto se tiene que exportar en una constante
            }
        }

        this.activitiesNetworkManager.getActivitiesFromServer(
                new GetAllActivitiesManagerCompletion() {
                    @Override
                    public void completion(@NonNull List<ActivityEntity> activityEntities) {
                        Log.d("ACTIVITY", activityEntities.toString());
                        if (completion != null) {
                            Activities activities = ActivityEntityIntoActivitiesMapper.map(activityEntities);
                            completion.completion(activities);
                        }
                    }
                }, new ManagerErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        if (onError != null) {
                            onError.onError(errorDescription);
                        }
                    }
                });
    }
}
