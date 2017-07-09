package com.josejacin.madridshops.util.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;

public class MapUtil {
    public static void centerMapInPosition(GoogleMap googleMap, double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public static void addPins(List<MapPinnable> mapPinnables, final GoogleMap googleMap, final Context context) {
        if (mapPinnables == null || googleMap == null || context == null) {
            return;
        }

        for (final MapPinnable pinnable: mapPinnables) {
            final LatLng position = new LatLng(pinnable.getLatitude(), pinnable.getLongitude());
            final String profileImageUrl = pinnable.getPinImageUrl();

            final MarkerOptions marker = new MarkerOptions().position(position).title(pinnable.getPinDescription());

            Marker m = googleMap.addMarker(marker);
            m.setTag(pinnable.getRelatedModelObject());
        }
    }

    public static GoogleMap configureMap(GoogleMap map) {
        centerMapInPosition(map, 40.411335, -3.674908);
        map.setBuildingsEnabled(true);
        map.setMapType(MAP_TYPE_SATELLITE);
        map.getUiSettings().setRotateGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMyLocationEnabled(true);

        return map;
    }

    //https://stackoverflow.com/questions/37986082/android-googlemaps-mylocation-permission
    public static void checkMapPermissionsAndAdd(@NonNull Activity activity){
        if(activity == null){
            return;
        }

        boolean permissionGranted = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if(!permissionGranted) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }
}
