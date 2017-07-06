package com.josejacin.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.util.Constants;
import com.josejacin.madridshops.util.StaticMapImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_activity_detail__activity_address) TextView address;
    @BindView(R.id.activity_activity_detail__activity_description) TextView description;
    @BindView(R.id.activity_activity_detail__activity_image) ImageView activityImage;
    @BindView(R.id.activity_activity_detail__activity_map) ImageView mapImage;
    @BindView(R.id.activity_activity_detail__activity_name) TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Activity activity = (Activity) intent.getSerializableExtra(Constants.INTENT_ACTIVITY_DETAIL);
            name.setText(activity.getName());
            address.setText(activity.getAddress());
            description.setText(activity.getDescription());
            Picasso.with(this).
                    load(activity.getImageUrl()).
                    placeholder(R.drawable.activity_placeholder).
                    into(activityImage);

            String staticMapUrl = StaticMapImage.getMapImageUrlToActivity(activity);
            Picasso.with(this).
                    load(staticMapUrl).
                    placeholder(R.drawable.map_placeholder).
                    into(mapImage);
        }
    }
}
