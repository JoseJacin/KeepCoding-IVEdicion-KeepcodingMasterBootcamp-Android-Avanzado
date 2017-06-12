package com.josejacin.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Shop;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Shop.of(1, "Mi tienda")
                .setAddress("C")
                .setLatitude(10)
                .setLatitude(10);

    }
}
