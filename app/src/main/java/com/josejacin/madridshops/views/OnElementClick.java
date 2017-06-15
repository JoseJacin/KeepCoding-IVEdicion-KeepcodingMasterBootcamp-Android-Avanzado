package com.josejacin.madridshops.views;

import android.support.annotation.NonNull;

// Interfaz del objeto listener que se encuentra dentro del Adapter
public interface OnElementClick<T> {
    void clickOn(@NonNull T element, int position);
}
