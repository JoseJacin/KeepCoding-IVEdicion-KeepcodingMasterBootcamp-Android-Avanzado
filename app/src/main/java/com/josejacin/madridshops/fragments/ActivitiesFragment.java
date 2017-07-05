package com.josejacin.madridshops.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.adapters.ActivitiesAdapter;
import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.views.OnElementClick;

public class ActivitiesFragment extends Fragment {

    private OnElementClick<Activity> listener;

    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;
    private Activities activities;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_activities__recycler_view);
        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;

        adapter = new ActivitiesAdapter(activities, getActivity());
        activitiesRecyclerView.setAdapter(adapter);
    }
}
