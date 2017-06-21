package com.josejacin.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.util.Constants;
import com.josejacin.madridshops.util.StaticMapImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView((R.id.activity_shop_detail__shop_address)) TextView address;
    @BindView(R.id.activity_shop_detail__shop_description) TextView description;
    @BindView(R.id.activity_shop_detail__shop_image) ImageView shopImage;
    @BindView(R.id.activity_shop_detail__shop_map) ImageView shopMap;
    @BindView(R.id.activity_shop_detail__shop_name) TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        // Se obtiene el Shops que nos viene de la ActivityShopList
        Intent intent = getIntent();
        if (intent != null) {
            Shop shop = (Shop) intent.getSerializableExtra(Constants.INTENT_SHOPS_DETAIL);
            name.setText(shop.getName());
            address.setText(shop.getAddress());
            description.setText(shop.getDescription());
            Picasso.with(this).
                    load(shop.getImageUrl()).
                    placeholder(R.drawable.shop_placeholder).
                    into(shopImage);
            String staticMapUrl = StaticMapImage.getMapImageUrl(shop);
            Picasso.with(this).
                    load(staticMapUrl).
                    placeholder(R.drawable.map_placeholder).
                    into(shopMap);
        }
    }
}
