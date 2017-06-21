package com.josejacin.madridshops.domain.managers.network;

import android.support.annotation.*;

public interface NetworkManager {
    // Permite lanzar una petición y obtener datos
    void getShopsFromServer(@NonNull final GetAllShopsManagerCompletion completion,
                            @Nullable final ManagerErrorCompletion errorCompletion);
}
