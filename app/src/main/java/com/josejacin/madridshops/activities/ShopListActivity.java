package com.josejacin.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.GetAllShopsFromCacheInteractor;
import com.josejacin.madridshops.domain.interactors.GetAllShopsFromCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.GetIfAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.GetIfAllShopsAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractor;
import com.josejacin.madridshops.domain.interactors.SaveAllShopsIntoCacheInteractorImpl;
import com.josejacin.madridshops.domain.interactors.SetAllShopsAreCacheInteractor;
import com.josejacin.madridshops.domain.interactors.SetAllShopsAreCacheInteractorImpl;
import com.josejacin.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.josejacin.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.josejacin.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManagerDAOImpl;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.josejacin.madridshops.domain.managers.network.NetworkManager;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.fragments.ShopsFragment;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.views.OnElementClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

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

        NetworkManager manager = new GetAllShopsManagerImpl(this);
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
            }
        );
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
    }
}
