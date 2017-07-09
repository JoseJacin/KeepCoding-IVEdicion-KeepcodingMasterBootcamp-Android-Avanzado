package com.josejacin.madridshops.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.activity.GetAllActivitiesFromCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.cache.activity.SaveAllActivitiesIntoCacheManager;
import com.josejacin.madridshops.domain.managers.cache.activity.SaveAllActivitiesIntoCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.network.ActivitiesNetworkManager;
import com.josejacin.madridshops.domain.managers.network.GetAllActivitiesManagerImpl;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.fragments.ActivitiesFragment;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.util.map.MapPinnable;
import com.josejacin.madridshops.util.map.MapUtil;
import com.josejacin.madridshops.util.map.model.ActivityPin;
import com.josejacin.madridshops.views.OnElementClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.josejacin.madridshops.util.map.MapUtil.centerMapInPosition;

public class ActivityListActivity extends AppCompatActivity {

    @BindView(R.id.activity_activity_list__progress_bar) ProgressBar progressBar;

    ActivitiesFragment activitiesFragment;
    private SupportMapFragment mapFragment;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        ButterKnife.bind(this);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        initializeMap();
    }

    private void checkCacheData() {
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

    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    map = googleMap;
                    checkCacheData();
                    addDataToMap(googleMap);
                }
            }
        });
    }

    public void addDataToMap(GoogleMap map) {
        // Se solicitan los permisos al usuario
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        this.map = MapUtil.configureMap(map);
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

        putShopsPinsOnMap(activities);
    }

    private void putShopsPinsOnMap(Activities activities) {
        List<MapPinnable> activityPins = ActivityPin.activityPinsFromActivities(activities);
        MapUtil.addPins(activityPins, map, this);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Activity)) {
                    return;
                }
                Activity activity = (Activity) marker.getTag();
                Navigator.navigateFromActivityListActivityToActivityDetailActivity(ActivityListActivity.this, activity, 0);
            }
        });
    }
}
