package com.josejacin.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractor;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorFakeImpl;
import com.josejacin.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.josejacin.madridshops.domain.managers.network.NetworkManager;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.fragments.ShopsFragment;
import com.josejacin.madridshops.navigator.Navigator;
import com.josejacin.madridshops.views.OnElementClick;

public class ShopListActivity extends AppCompatActivity {

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);

        // Se obtiene una lista de tiendas
        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(
            new GetAllShopsInteractorCompletion() {
                @Override
                public void completion(Shops shops) {
                    System.out.println("Hello hello");
                    shopsFragment.setShops(shops);
                    // Se establece el listener
                    shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                        @Override
                        public void clickOn(@NonNull Shop element, int position) {
                            // Se accede a la actividad de detalle
                            // Parámetros
                            // Puntero a la actividad de detalle
                            // Elemento
                            // Posición pulsada
                            Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
                        }
                    });
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
}
