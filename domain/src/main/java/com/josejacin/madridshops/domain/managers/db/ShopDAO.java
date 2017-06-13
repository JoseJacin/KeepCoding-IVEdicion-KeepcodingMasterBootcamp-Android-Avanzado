package com.josejacin.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.josejacin.madridshops.domain.model.Shop;

import java.util.List;

import static com.josejacin.madridshops.domain.managers.db.DBConstants.*;

public class ShopDAO implements DAOReadable<Shop>, DAOWritable<Shop> {

    // Constants
    private static final long EMPTY_SHOP = 0;

    // Properties
    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    // Constructor
    public ShopDAO(DBHelper dbHelper) {
        // Se obtiene la conexión a la BDD
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();
    }

    public ShopDAO(Context context) {
        this(new DBHelper(context));
    }

    // Methods
    @Override
    public Shop query(@NonNull long id) {
        return null;
    }

    @Override
    public List<Shop> query() {
        return null;
    }

    @Override
    public long insert(@NonNull Shop element) {
        if (element == null) {
            return EMPTY_SHOP;
        }

        // Se inicia la transacción
        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;

        // Se realiza la inserción
        try {
            id = dbWriteConnection.insert(TABLE_SHOP, null, getContentValues(element));
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

    private ContentValues getContentValues(Shop shop) {
        // Se crea el mapa de clave : valor
        final ContentValues contentValues = new ContentValues();

        if (shop == null) {
            return contentValues;
        }

        contentValues.put(KEY_SHOP_NAME, shop.getName()); // Habría que validar que no es null, ya que en BDD es NOT NULL
        contentValues.put(KEY_SHOP_ADDRESS, shop.getAddress());
        contentValues.put(KEY_SHOP_DESCRIPTION, shop.getDescription());
        contentValues.put(KEY_SHOP_IMAGE_URL, shop.getImageUrl());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL, shop.getLogoUrl());
        contentValues.put(KEY_SHOP_URL, shop.getUrl());
        contentValues.put(KEY_SHOP_LATITUDE, shop.getLatitude());
        contentValues.put(KEY_SHOP_LONGITUDE, shop.getLongitude());

        return contentValues;
    }

    @Override
    public long update(@NonNull long id, Shop element) {
        return 0;
    }

    @Override
    public long delete(@NonNull long id) {
        return 0;
    }

    @Override
    public long delete(@NonNull Shop element) {
        return 0;
    }

    @Override
    public void deleteAll() {

    }
}
