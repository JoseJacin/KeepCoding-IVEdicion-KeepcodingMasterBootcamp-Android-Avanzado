package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.interactors.InteractorErrorCompletion;

public interface GetAllShopsInteractor {
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion,
                        @Nullable final InteractorErrorCompletion onError);
}
