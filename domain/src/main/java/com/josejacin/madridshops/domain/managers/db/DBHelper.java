package com.josejacin.madridshops.domain.managers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_FILE_NAME = "madridshops.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory NO_CURSOR_FACTORY = null;
    public static final long INVALID_ID = -1;

    public DBHelper(Context context) {
        this(context, DATABASE_FILE_NAME, NO_CURSOR_FACTORY, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        // Se activa el borrado en cascada
        // if API LEVEL > 16
        db.setForeignKeyConstraintsEnabled(true);
        // else
        //db.execSQL("PRAGMA foreing_keys = ON");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String script : DBConstants.CREATE_DATABASE_SCRIPTS) {
            sqLiteDatabase.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion ==2) { // v1 --> v2
            sqLiteDatabase.execSQL(DBConstants.UPDATE_DATABASE_SCRIPTS);
        } else if (oldVersion == 1 && newVersion == 3) { // v1 --> v3

        }
    }

    // Utility methods
    public static int convertBooleanToInt(boolean b) {
        // Retorna 1 si b es true
        // Retorna 0 si b es false
        return b ? 1 : 0;
    }

    public static boolean convertIntToBoolean(int i) {
        // Retorna false si i == 0
        // Retorna true si i != 0
        return i != 0;
    }

    //TODO - Hay que añadir aquí los métodos que meta freniche
}
