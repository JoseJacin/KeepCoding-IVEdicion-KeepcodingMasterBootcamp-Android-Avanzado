package com.josejacin.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

public interface GetAllShopsFromCacheManager {
    void execute(@NonNull final GetAllShopsFromCacheManagerCompletion completion);

}
