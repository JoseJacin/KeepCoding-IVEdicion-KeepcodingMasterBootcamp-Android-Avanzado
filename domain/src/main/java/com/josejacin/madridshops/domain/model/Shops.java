package com.josejacin.madridshops.domain.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Shops implements ShopsIterable, ShopsUpdatable {
    // Properties
    private List<Shop> shops;

    public Shops() {
    }

    // Lazy getter.
    // Se reserva memoria solo cuando se va a necesitar el objeto
    private List<Shop> getShops() {
        if (shops == null) {
            shops = new LinkedList<>();
        }
        return shops;
    }

    @Override
    public void add(Shop shop) {
        getShops().add(shop);
    }

    @Override
    public void delete(Shop shop) {
        getShops().remove(shop);
    }

    @Override
    public void update(Shop newShop, long index) {
        getShops().set((int)index, newShop);
    }

    @Override
    public long size() {
        return getShops().size();
    }

    @Override
    public Shop get(long index) {
        return getShops().get((int)index);
    }

    @Override
    public List<Shop> allShops() {
        // Se realiza una copia inmutable de la lista de shops para evitar que desde fuera se pueda
        // modificar sin parar por los métodos de modificación (add, delete, update, ...)
        List<Shop> listCopy = new LinkedList<>();

        for (Shop shop : getShops()) {
            listCopy.add(shop);
        }

        return listCopy;
    }
}
