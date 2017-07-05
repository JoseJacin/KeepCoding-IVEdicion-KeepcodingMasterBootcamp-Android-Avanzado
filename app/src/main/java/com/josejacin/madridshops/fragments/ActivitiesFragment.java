package com.josejacin.madridshops.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josejacin.madridshops.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private RecyclerView activitiesRecyclerView;

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

}
