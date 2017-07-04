package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.josejacin.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.josejacin.madridshops.domain.managers.network.NetworkManager;
import com.josejacin.madridshops.domain.managers.network.entities.ShopEntity;
import com.josejacin.madridshops.domain.managers.network.mappers.ShopEntityIntoShopsMapper;
import com.josejacin.madridshops.domain.model.Shops;

import java.util.List;

public class GetAllShopsInteractorImpl implements GetAllShopsInteractor {
    private NetworkManager networkManager;

    public GetAllShopsInteractorImpl(@NonNull final NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.onError("Error en la conexión"); //TODO: Esto se tiene que exportar en una constante
            }
        }

        this.networkManager.getShopsFromServer(
                new GetAllShopsManagerCompletion() {
                    @Override
                    public void completion(@NonNull List<ShopEntity> shopEntities) {
                        Log.d("SHOP", shopEntities.toString());
                        if (completion != null) {
                            Shops shops = ShopEntityIntoShopsMapper.map(shopEntities);
                            completion.completion(shops);
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
