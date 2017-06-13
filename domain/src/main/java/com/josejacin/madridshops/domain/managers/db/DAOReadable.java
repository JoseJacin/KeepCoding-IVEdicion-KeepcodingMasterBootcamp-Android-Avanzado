package com.josejacin.madridshops.domain.managers.db;

import android.support.annotation.NonNull;

import java.util.List;

public interface DAOReadable<T> {
    T query(@NonNull final long id);
    List<T> query();
}
