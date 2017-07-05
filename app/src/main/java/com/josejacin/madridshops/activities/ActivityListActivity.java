package com.josejacin.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractor;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractorFakeImpl;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractorImpl;
import com.josejacin.madridshops.domain.managers.network.ActivitiesNetworkManager;
import com.josejacin.madridshops.domain.managers.network.GetAllActivitiesManagerImpl;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.josejacin.madridshops.domain.managers.network.ShopsNetworkManager;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.fragments.ActivitiesFragment;
import com.josejacin.madridshops.fragments.ShopsFragment;

public class ActivityListActivity extends AppCompatActivity {

    ActivitiesFragment activitiesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        // Obtain activities list
        ActivitiesNetworkManager manager = new GetAllActivitiesManagerImpl(this);
        GetAllActivitiesInteractor getAllActivitiesInteractor = new GetAllActivitiesInteractorImpl(manager);
        getAllActivitiesInteractor.execute(
                new GetAllActivitiesInteractorCompletion() {
                    @Override
                    public void completion(Activities activities) {
                        System.out.println("Hello hello Activities");
                        activitiesFragment.setActivities(activities);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {

                    }
                });
    }
}
