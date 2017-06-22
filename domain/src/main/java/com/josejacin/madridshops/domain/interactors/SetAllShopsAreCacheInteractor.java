package com.josejacin.madridshops.domain.interactors;

public interface SetAllShopsAreCacheInteractor {
    public static final String SHOPS_SAVED = "SHOPS_SAVED";

    void execute(boolean shopsSaved);
}
