package com.josejacin.madridshops.domain.interactors.shop;

public interface GetIfAllShopsAreCacheInteractor {
    void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAeNotCached);
}
