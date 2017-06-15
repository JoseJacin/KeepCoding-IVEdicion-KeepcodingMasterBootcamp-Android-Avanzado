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
import com.josejacin.madridshops.adapters.ShopsAdapter;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.views.OnElementClick;

public class ShopsFragment extends Fragment {

    private RecyclerView shopsRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;

    public ShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_shops__recycler_view);
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setShops(Shops shops) {
        this.shops = shops;

        adapter = new ShopsAdapter(shops, getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickOn(@NonNull Shop shop, int position) {
                Log.d("Click", shop.getName());
            }
        });
    }

}
