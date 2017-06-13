package com.josejacin.madridshops.domain.managers.db;

import android.support.annotation.NonNull;

// DAO: Data Access Object
public interface DAOWritable<T> {
    long insert(@NonNull final T element);
    long update(@NonNull final long id, final T element);
    long delete(@NonNull final long id);
    long delete(@NonNull T element);
    void deleteAll();
}
