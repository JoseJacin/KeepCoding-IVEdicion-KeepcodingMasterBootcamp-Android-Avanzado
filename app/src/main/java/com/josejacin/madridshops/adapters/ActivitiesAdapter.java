package com.josejacin.madridshops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.views.ActivityRowViewHolder;
import com.josejacin.madridshops.views.OnElementClick;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityRowViewHolder> {

    private Activities activities;
    private LayoutInflater inflater;
    private OnElementClick<Activity> listener;

    // Constructor
    // Se encarga de pasar una lista de Shops y un contexto al Adapter
    public ActivitiesAdapter(final Activities activities, final Context context) {
        this.activities = activities;
        inflater = LayoutInflater.from(context);
    }

    // Se encarga de crear la vista de la celda
    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.row_activity, parent, false);

        ActivityRowViewHolder viewHolder = new ActivityRowViewHolder(view);
        return viewHolder;
    }

    // Se encarga de pintar la vista de la celda
    @Override
    public void onBindViewHolder(ActivityRowViewHolder activityRow, final int position) {
        final Activity activity = this.activities.get(position);
        activityRow.setShop(activity);

        // Se establece el OnClickListener en toda la celda
        activityRow.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickedOn(activity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.activities != null) {
            return (int) this.activities.size();
        }
        return 0;
    }

    public void setOnClickListener(OnElementClick<Activity> listener) {
        this.listener = listener;
    }
}
