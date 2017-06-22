package com.josejacin.madridshops.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public class MainThread {
    // Volver al hilo principal
    // Método 2 (Cuando no se encuentra dentro de una actividad)
    public static void run(@NonNull final Runnable codeToRunOnMainThread) {
        if (codeToRunOnMainThread == null) {
            return;
        }

        // Se accede al hilo principal
        Handler  uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(codeToRunOnMainThread);
    }
}
