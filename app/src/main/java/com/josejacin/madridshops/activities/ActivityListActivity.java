package com.josejacin.madridshops.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesFromCacheInteractor;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesFromCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractor;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractorImpl;
import com.josejacin.madridshops.domain.interactors.activity.GetIfAllActivitiesAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.activity.GetIfAllActivitiesAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.activity.SaveAllActivitiesIntoCacheInteractor;
import com.josejacin.madridshops.domain.interactors.activity.SaveAllActivitiesIntoCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.activity.SetAllActivitiesAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.activity.SetAllActivitiesAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsFromCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsFromCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.cache.activity.SaveAllActivitiesIntoCacheManager;
import com.josejacin.madridshops.domain.managers.cache.activity.SaveAllActivitiesIntoCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.network.ActivitiesNetworkManager;
import com.josejacin.madridshops.domain.managers.network.GetAllActivitiesManagerImpl;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.fragments.ActivitiesFragment;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.views.OnElementClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityListActivity extends AppCompatActivity {

    @BindView(R.id.activity_activity_list__progress_bar) ProgressBar progressBar;

    ActivitiesFragment activitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        ButterKnife.bind(this);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        GetIfAllActivitiesAreCacheInteractor getIfAllActivitiesAreCacheInteractor = new GetIfAllActivitiesAreCacheInteractorImpl(this);
        getIfAllActivitiesAreCacheInteractor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        // Las actividades ya se encuentran en BBDD. No hay que leer de red, sino de BBDD
                        readDataFromCache();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        // Las actividades no se encuentran en BBDD. Hay que leer de red y guardar en BBDD
                        // Se obtiene una lista de actividades
                        obtainActivitiesList();
                    }
                }
        );
    }

    private void readDataFromCache() {
        GetAllActivitiesFromCacheManager getAllActivitiesFromCacheManager = new GetAllActivitiesFromCacheManagerDAOImpl(this);
        GetAllActivitiesFromCacheInteractor getAllActivitiesFromCacheInteractor = new GetAllActivitiesFromCacheInteractorImpl(getAllActivitiesFromCacheManager);
        getAllActivitiesFromCacheInteractor.execute(new GetAllActivitiesInteractorCompletion() {
            @Override
            public void completion(@NonNull Activities activities) {
                configActivitiesFragment(activities);
            }
        });
    }

    private void obtainActivitiesList() {
        // Se establece el spinner (ProgressBar)
        progressBar.setVisibility(View.VISIBLE);

        ActivitiesNetworkManager manager = new GetAllActivitiesManagerImpl(this);
        GetAllActivitiesInteractor getAllActivitiesInteractor = new GetAllActivitiesInteractorImpl(manager);
        getAllActivitiesInteractor.execute(
                new GetAllActivitiesInteractorCompletion() {
                    @Override
                    public void completion(Activities activities) {
                        // Se guardan las actividades en BBDD
                        SaveAllActivitiesIntoCacheManager saveManager = new SaveAllActivitiesIntoCacheManagerDAOImpl(getBaseContext());
                        SaveAllActivitiesIntoCacheInteractor saveInteractor = new SaveAllActivitiesIntoCacheInteractorImpl(saveManager);
                        saveInteractor.execute(activities, new Runnable() {
                            @Override
                            public void run() {
                                // Se establece el indicador de que las Activities ya se han almacenado en BBDD a true
                                SetAllActivitiesAreCacheInteractor setAllActivitiesAreCacheInteractor = new SetAllActivitiesAreCacheInteractorImpl(getBaseContext());
                                setAllActivitiesAreCacheInteractor.execute(true);
                            }
                        });

                        configActivitiesFragment(activities);
                        // Se quita el spinner (ProgressBar)
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {

                    }
                });
    }

    private void configActivitiesFragment(final Activities activities) {
        activitiesFragment.setActivities(activities);
        // Se establece el listener
        activitiesFragment.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(@NonNull Activity element, int position) {
                // Se accede a la actividad de detalle
                // Parámetros
                // Puntero a la actividad de detalle
                // Elemento
                // Posición pulsada
                Navigator.navigateFromActivityListActivityToActivityDetailActivity(ActivityListActivity.this, element, position);
            }
        });
    }
}
