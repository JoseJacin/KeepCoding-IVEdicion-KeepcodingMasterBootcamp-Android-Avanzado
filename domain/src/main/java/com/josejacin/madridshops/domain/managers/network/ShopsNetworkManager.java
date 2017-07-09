package com.josejacin.madridshops.domain.managers.network;

import android.support.annotation.*;

import com.josejacin.madridshops.domain.interactors.activity.GetAllActivitiesInteractor;

public interface ShopsNetworkManager {
    // Permite lanzar una petición y obtener datos
    void getShopsFromServer(@NonNull final GetAllShopsManagerCompletion completion,
                            @Nullable final ManagerErrorCompletion errorCompletion);
}
