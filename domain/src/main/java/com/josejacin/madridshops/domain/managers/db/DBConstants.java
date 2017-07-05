package com.josejacin.madridshops.domain.managers.db;
import static com.josejacin.madridshops.domain.managers.db.DBActivityConstants.SQL_SCRIPT_CREATE_ACTIVITY_TABLE;
import static com.josejacin.madridshops.domain.managers.db.DBShopConstants.SQL_SCRIPT_CREATE_SHOP_TABLE;

public class DBConstants {
    public static final String DROP_DATABASE_SCRIPTS = "";
    public static final String UPDATE_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    };
}
