package com.josejacin.madridshops.domain.interactors.shop;

import android.support.annotation.NonNull;

public interface GetAllShopsFromCacheInteractor {
    void execute(@NonNull final GetAllShopsInteractorCompletion completion);
}
