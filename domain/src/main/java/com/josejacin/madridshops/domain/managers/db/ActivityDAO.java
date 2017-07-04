package com.josejacin.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;

import java.util.List;

import static com.josejacin.madridshops.domain.managers.db.DBActivityConstants.*;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_ADDRESS;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_DESCRIPTION;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_IMAGE_URL;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_LATITUDE;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_LOGO_IMAGE_URL;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_LONGITUDE;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_NAME;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.KEY_SHOP_URL;

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
        if (element == null) {
            return EMPTY_ACTIVITY;
        }

        // Se inicia la transacción
        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;

        // Se realiza la inserción
        try {
            id = dbWriteConnection.insert(TABLE_ACTIVITY, null, getContentValues(element));
            element.setId(id);

            // Se indica a la transacción que no ha habido errores
            dbWriteConnection.setTransactionSuccessful();
        } finally {
            // Se cierra la transacción
            // Si se ha ejecutado setTransactionSuccessful se realiza un commit
            // Si no se ha ejecutado setTransactionSuccessful se realiza un rollback
            dbWriteConnection.endTransaction();
        }
        return id;
    }

    private ContentValues getContentValues(Activity activity) {
        // Se crea el mapa de clave : valor
        final ContentValues contentValues = new ContentValues();

        if (activity == null) {
            return contentValues;
        }

        contentValues.put(KEY_ACTIVITY_NAME, activity.getName()); // Habría que validar que no es null, ya que en BDD es NOT NULL
        contentValues.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(KEY_ACTIVITY_DESCRIPTION, activity.getDescription());
        contentValues.put(KEY_ACTIVITY_IMAGE_URL, activity.getImageUrl());
        contentValues.put(KEY_ACTIVITY_LOGO_IMAGE_URL, activity.getLogoUrl());
        contentValues.put(KEY_ACTIVITY_URL, activity.getUrl());
        contentValues.put(KEY_ACTIVITY_LATITUDE, activity.getLatitude());
        contentValues.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());

        return contentValues;
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
