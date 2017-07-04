package com.josejacin.madridshops.domain.managers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.model.Activity;

import java.util.List;

public class ActivityDAO implements DAOReadable<Activity>, DAOWritable<Activity> {

    // Constants
    private static final long EMPTY_ACTIVITY = 0;

    // Properties
    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    // Constructor
    public ActivityDAO(DBHelper dbHelper) {
        // Se obtiene la conexión a la BDD
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();
    }

    public ActivityDAO(Context context) {
        this(new DBHelper(context));
    }

    // Methods
    @Override
    public List<Activity> query() {
        return null;
    }

    @Override
    public Activity query(@NonNull long id) {
        return null;
    }

    @Nullable
    @Override
    public List<Activity> query(String where, String[] whereArgs, String orderBy) {
        return null;
    }

    @Override
    public long insert(@NonNull Activity element) {
        return 0;
    }

    @Override
    public long update(@NonNull long id, Activity element) {
        return 0;
    }

    @Override
    public long delete(@NonNull Activity element) {
        return 0;
    }

    @Override
    public long delete(@NonNull long id) {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long delete(String where, String... whereClause) {
        return 0;
    }

    @Override
    public int numRecords() {
        return 0;
    }
}
