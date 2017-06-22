package com.josejacin.madridshops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josejacin.madridshops.R;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;
import com.josejacin.madridshops.views.OnElementClick;
import com.josejacin.madridshops.views.ShopRowViewHolder;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder> {

    private Shops shops;
    private LayoutInflater inflater;
    private OnElementClick<Shop> listener;

    // Constructor
    // Se encarga de pasar una lista de Shops y un contexto al Adapter
    public ShopsAdapter(final Shops shops, final Context context) {
        this.shops = shops;
        inflater = LayoutInflater.from(context);
    }

    // Se encarga de crear la vista de la celda
    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.row_shop, parent, false);

        ShopRowViewHolder viewHolder = new ShopRowViewHolder(view);
        return viewHolder;
    }

    // Se encarga de pintar la vista de la celda
    @Override
    public void onBindViewHolder(ShopRowViewHolder shopRow, final int position) {
        final Shop shop = this.shops.get(position);
        shopRow.setShop(shop);

        // Se establece el OnClickListener en toda la celda
        shopRow.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickedOn(shop, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.shops != null) {
            return (int) this.shops.size();
        }
        return 0;
    }

    public void setOnClickListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }
}
