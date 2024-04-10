package com.ruirua.sampleguideapp.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ruirua.sampleguideapp.R;

import java.util.Map;

public class UIFuns {
    public static void configureTheme(Activity activity) {
        Log.d("DebugApp","Entrou " + Settings.getInstance().isDarkMode());
        if(Settings.getInstance().isLightMode())
            activity.setTheme(R.style.AppThemeLight);
        else
            activity.setTheme(R.style.AppThemeDark);
    }
    private static void goToFragment(FragmentManager fragmentManager, Fragment newFragment) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, newFragment)
                .commit();
    }

    private static <T> void goToActivity(Context context, Class<T> newActivity, Map<String,String> params) {
        Intent intent = new Intent(context, newActivity);
        if(params != null)
            params.forEach(intent::putExtra);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void changeFragment(Activity activity, Fragment currentFragment,FragmentManager fragmentManager, Fragment newFragment) {
        StackUI.getInstance().pushElement(activity,currentFragment, fragmentManager);
        goToFragment(fragmentManager,newFragment);
    }

    public static <T> void changeActivity(Activity currentActivity, Class<T> newActivity, Map<String,String> oldparams ,Map<String,String> newparams) {
        StackUI.getInstance().pushElement(currentActivity,oldparams);
        goToActivity(currentActivity.getApplicationContext(),newActivity,newparams);
    }

    public static void goBack(Activity currentActivity) {
        StackUIElement sue = StackUI.getInstance().goBack();
        Activity activity = sue.getActivitY();
        if(currentActivity.equals(activity)) {
            Fragment fragment = sue.getFragment();
            FragmentManager fm = sue.getFm();
            goToFragment(fm,fragment);
        }
        else {
            Map<String,String> params = sue.getParams();
            goToActivity(currentActivity.getApplicationContext(),activity.getClass(),params);
        }
    }

    public static Intent intentGoogleMaps(double origin_lat, double origin_lon, double destiny_lat, double destiny_lon) {

        Uri gmmIntentUri = new Uri.Builder()
                .scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("origin", origin_lat + "," + origin_lon)
                .appendQueryParameter("destination", destiny_lat + "," + destiny_lon)
                .build();

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }

    public static Intent permissionsGoogleMap() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }
}