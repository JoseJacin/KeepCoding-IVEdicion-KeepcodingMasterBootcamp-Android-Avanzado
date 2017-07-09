package com.josejacin.madridshops.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Activity;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class ActivityRowViewHolder extends RecyclerView.ViewHolder {

    private TextView activityNameTextView;
    private ImageView activityLogoImageView;
    private ImageView activityImgImageView;
    WeakReference<Context> context;

    // El constructor se ejecuta tantas veces como celdas quepan en la pantalla más otras dos para tener caché
    public ActivityRowViewHolder(View rowActivity) {
        super(rowActivity);

        // Las dos líneas son lo mismo
        this.context = new WeakReference<>(rowActivity.getContext());

        activityNameTextView = (TextView) rowActivity.findViewById(R.id.row_activity__activity_name);
        activityLogoImageView = (ImageView) rowActivity.findViewById(R.id.row_activity__activity_logo);
        activityImgImageView = (ImageView) rowActivity.findViewById(R.id.row_activity__activity_img);
    }

    public void setShop(Activity activity) {
        if (activity == null) {
            return;
        }

        activityNameTextView.setText(activity.getName());
        Picasso.with(context.get()).
                load(activity.getLogoUrl()).
                placeholder(R.drawable.activity_placeholder).
                into(activityLogoImageView);

        Picasso.with(context.get()).
                load(activity.getImageUrl()).
                placeholder(R.drawable.activity_placeholder).
                into(activityImgImageView);

    }
}
