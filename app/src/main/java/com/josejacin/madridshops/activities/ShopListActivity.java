package com.josejacin.madridshops.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsFromCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsFromCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.shop.GetAllShopsInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.GetIfAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.GetIfAllShopsAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.interactors.shop.SaveAllShopsIntoCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.SaveAllShopsIntoCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.shop.SetAllShopsAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.GetAllShopsFromCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.cache.shop.SaveAllShopsIntoCacheManager;
import com.josejacin.madridshops.domain.managers.cache.shop.SaveAllShopsIntoCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.josejacin.madridshops.domain.managers.network.ShopsNetworkManager;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.fragments.ShopsFragment;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.util.map.MapPinnable;
import com.josejacin.madridshops.util.map.MapUtil;
import com.josejacin.madridshops.util.map.model.ShopPin;
import com.josejacin.madridshops.views.OnElementClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
//import static com.josejacin.madridshops.util.map.MapUtil.centerMapInPosition;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;

    ShopsFragment shopsFragment;
    private SupportMapFragment mapFragment;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        initializeMap();
    }

    private void checkCacheData() {
        // Se consulta si ya se han guardado el listado de las tiendas
        GetIfAllShopsAreCacheInteractor getIfAllShopsAreCacheInteractor = new GetIfAllShopsAreCacheInteractorImpl(this);
        getIfAllShopsAreCacheInteractor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        // Las tiendas ya se encuentran en BBDD. No hay que leer de red, sino de BBDD
                        readDataFromCache();

                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        // Las tiendas no se encuentran en BBDD. Hay que leer de red y guardar en BBDD
                        // Se obtiene una lista de tiendas
                        obtainShopsList();
                    }
                }
        );
    }

    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__map);
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
        /*if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

        centerMapInPosition(map, 40.411335, -3.674908);
        map.setBuildingsEnabled(true);
        map.setMapType(MAP_TYPE_SATELLITE);
        map.getUiSettings().setRotateGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        //map.setMyLocationEnabled(true);

        MarkerOptions retiroMarkerOptions = new MarkerOptions()
                .position(new LatLng(40.411335, -3.674908))
                .title("Hello world").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        MarkerOptions retiroMarkerOptions2 = new MarkerOptions()
                .position(new LatLng(42, -3.674908))
                .title("Hello world").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_camera));

        Marker marker = map.addMarker(retiroMarkerOptions);
        map.addMarker(retiroMarkerOptions2);
    }

    private void centerMapInPosition(GoogleMap googleMap, double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void readDataFromCache() {
        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImpl(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);
            }
        });
    }

    private void obtainShopsList() {
        // Se establece el spinner (ProgressBar)
        progressBar.setVisibility(View.VISIBLE);

        ShopsNetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(
                new GetAllShopsInteractorCompletion() {
                    @Override
                    public void completion(Shops shops) {
                        // Se guardan las tiendas en BBDD
                        SaveAllShopsIntoCacheManager saveManager = new SaveAllShopsIntoCacheManagerDAOImpl(getBaseContext());
                        SaveAllShopsIntoCacheInteractor saveInteractor = new SaveAllShopsIntoCacheInteractorImpl(saveManager);
                        saveInteractor.execute(shops, new Runnable() {
                            @Override
                            public void run() {
                                // Se establece el indicador de que las Shops ya se han almacenado en BBDD a true
                                SetAllShopsAreCacheInteractor setAllShopsCacheInteractor = new SetAllShopsAreCacheInteractorImpl(getBaseContext());
                                setAllShopsCacheInteractor.execute(true);
                            }
                        });

                        configShopsFragment(shops);
                        // Se quita el spinner (ProgressBar)
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        System.out.println("Hay un error: " + errorDescription);
                    }
                });
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        // Se establece el listener
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                // Se accede a la actividad de detalle
                // Parámetros
                // Puntero a la actividad de detalle
                // Elemento
                // Posición pulsada
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });

        // Se pintan los pins en el mapa
        putShopPinsOnMap(shops);
    }

    private void putShopPinsOnMap(final Shops shops) {
        List<MapPinnable> shopPins = ShopPin.shopPinsFromShops(shops);
        MapUtil.addPins(shopPins, map, this);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Shop)) {
                    return;
                }
                Shop shop = (Shop) marker.getTag();
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, shop, 0);
            }
        });
    }
}
