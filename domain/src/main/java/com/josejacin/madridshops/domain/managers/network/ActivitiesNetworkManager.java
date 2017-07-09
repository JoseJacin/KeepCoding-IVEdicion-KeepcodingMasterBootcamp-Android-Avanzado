package com.josejacin.madridshops.domain.managers.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ActivitiesNetworkManager {
    // Permite lanzar una petición y obtener datos
    void getActivitiesFromServer(@NonNull final GetAllActivitiesManagerCompletion completion,
                                 @Nullable final ManagerErrorCompletion errorCompletion);
}
