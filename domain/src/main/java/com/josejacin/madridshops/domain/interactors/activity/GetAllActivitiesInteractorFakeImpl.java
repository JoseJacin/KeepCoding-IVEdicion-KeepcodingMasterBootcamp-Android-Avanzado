package com.josejacin.madridshops.domain.interactors.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;

public class GetAllActivitiesInteractorFakeImpl implements GetAllActivitiesInteractor {

    @Override
    public void execute(@NonNull GetAllActivitiesInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Activities activities = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My activity " + i)
                    .setLogoUrl("https://d30y9cdsu7xlg0.cloudfront.net/png/74416-200.png");
            activities.add(activity);
        }

        if (completion != null) {
            completion.completion(activities);
        }
    }
}
