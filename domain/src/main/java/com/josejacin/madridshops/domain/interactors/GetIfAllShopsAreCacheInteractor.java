package com.josejacin.madridshops.domain.interactors;

public interface GetIfAllShopsAreCacheInteractor {
    void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAeNotCached);
}
