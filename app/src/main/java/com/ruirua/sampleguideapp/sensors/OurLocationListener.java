package com.ruirua.sampleguideapp.sensors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import com.ruirua.sampleguideapp.Permissions;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;

public class OurLocationListener extends LocationCallback {
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private final int delay = 10000;
    private static OurLocationListener instance = null;

    private OurActivity ourActivity;
    public OurLocationListener(OurActivity activity) {
        this.ourActivity = activity;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    public static OurLocationListener createInstance(OurActivity activity) {
        if(instance == null)
            instance = new OurLocationListener(activity);
        return instance;
    }

    public static OurLocationListener getInstance() {
        return instance;
    }

    public boolean hasPermissions(Context context) {
        int fineLocationPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        return fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                    coarseLocationPermission == PackageManager.PERMISSION_GRANTED;
    }
    @SuppressLint("MissingPermission")
    public void onStart() {
        if(Permissions.permission_location) {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(delay);
            fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    this,
                    Looper.getMainLooper()
            );
        }
    }

    public void onStop() {
        fusedLocationProviderClient.removeLocationUpdates(this);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        Location location = locationResult.getLastLocation();
        ourActivity.sendNotifications(location.getLongitude(),location.getLatitude(),location.getAltitude());
    }

    public void updateActivity(OurActivity ourActivity) {
        this.ourActivity = ourActivity;
    }
}
