package com.josejacin.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.josejacin.madridshops.domain.model.Shop;

import java.util.ArrayList;
import java.util.List;

import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.*;

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
    public @Nullable List<Shop> query() {
        return query(null, null, KEY_SHOP_ID);
    }

    @Override
    public @Nullable Shop query(@NonNull long id) {
        // Forma de hacer que no se inyecte código
        //String idAsString = String.format("%d", id);
        //List<Shop> shops = query("ID = ?", new String[]{ idAsString }, KEY_SHOP_ID);

        // Otra forma de hacer que no se inyecto código
        List<Shop> shops = query(KEY_SHOP_ID + " = ?", new String[]{ "" + id }, KEY_SHOP_ID);

        if (shops == null || shops.size() == 0) {
            return null;
        }

        return shops.get(0);
    }

    @Override
    public @Nullable List<Shop> query(String where, String[] whereArgs, String orderBy) {
        Cursor c = dbReadConnection.query(TABLE_SHOP, // Table name
                ALL_SHOP_COLUMNS,    // Columns I want to obtain
                where,             // Where
                whereArgs,           // Where arguments
                orderBy,    // Order by
                null,           // Group by
                null);          // Having

        if (c == null || c.getCount() == 0) {
            // La consulta ha ido mal o no se han recuperado datos
            return null;
        }

        /*
        Estructura de un cursor
        -- beforefirst  -> Cuando se accede a un cursor, el puntero se encuentra en esta posición
        1, aaaa         -> Con moveToNext se posiciona en la primera posición
        2, bbbb
        3, cccc
        4, dddd
        -- afterlast
         */

        List<Shop> shopsList = new ArrayList<>();

        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(KEY_SHOP_ID));
            String name = c.getString(c.getColumnIndex(KEY_SHOP_NAME));
            String address = c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS));
            String description = c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION));
            String imageUrl = c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL));
            String logoImageUrl = c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL));
            String url = c.getString(c.getColumnIndex(KEY_SHOP_URL));
            Float latitude = c.getFloat(c.getColumnIndex(KEY_SHOP_LATITUDE));
            Float longitude = c.getFloat(c.getColumnIndex(KEY_SHOP_LONGITUDE));

            Shop shop = Shop.of(id, name)
                    .setAddress(address)
                    .setDescription(description)
                    .setImageUrl(imageUrl)
                    .setLogoUrl(logoImageUrl)
                    .setUrl(url)
                    .setLatitude(latitude)
                    .setLongitude(longitude);

            shopsList.add(shop);
        }

        return shopsList;
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
    public long delete(@NonNull Shop element) {
        return delete(element.getId());
    }

    @Override
    public long delete(@NonNull long id) {
        return delete(KEY_SHOP_ID + " = ?", "" + id);
    }

    @Override
    public void deleteAll() {
        delete(null, null);
    }

    // Con ... indicas que el último parámetro es un array con un número indetermiado de parámetros
    @Override
    public long delete(String where, String... whereClause) {
        int deletedRegs = 0;
        dbWriteConnection.beginTransaction();

        // Se realiza el borrado
        try {
            deletedRegs = dbWriteConnection.delete(TABLE_SHOP, where, whereClause);
            // Se indica a la transacción que no ha habido errores
            dbWriteConnection.setTransactionSuccessful();
        } finally {
            // Se cierra la transacción
            // Si se ha ejecutado setTransactionSuccessful se realiza un commit
            // Si no se ha ejecutado setTransactionSuccessful se realiza un rollback
            dbWriteConnection.endTransaction();
        }
        return deletedRegs;
    }

    @Override
    public int numRecords() {
        List<Shop> shopList = query();

        return shopList == null ? 0 : shopList.size();
    }
}
