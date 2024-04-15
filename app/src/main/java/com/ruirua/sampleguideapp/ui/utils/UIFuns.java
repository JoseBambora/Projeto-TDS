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
import java.util.Stack;

public class UIFuns {
    private static final Stack<Class<?>> stack = new Stack<>();
    public static void configureTheme(Activity activity) {
        if(Settings.getInstance().isLightMode())
            activity.setTheme(R.style.AppThemeLight);
        else
            activity.setTheme(R.style.AppThemeDark);
    }


    public static void changeFragment(FragmentManager fragmentManager, Fragment newFragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void changeFragmentNoPushStack(FragmentManager fragmentManager, Fragment newFragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commit();
    }

    private static <T> void changeActivity(Activity activity, Class<T> newActivity, Map<String,String> params, boolean push) {
        Intent intent = new Intent(activity, newActivity);
        if(params != null)
            params.forEach(intent::putExtra);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        if(push) {
            stack.push(activity.getClass());
        }
        activity.finish();
    }


    public static <T> void changeActivity(Activity activity, Class<T> newActivity, Map<String,String> params) {
        changeActivity(activity,newActivity,params,true);
    }

    public static void finishActivity(Activity activity) {
        Class<?> name = stack.pop();
        changeActivity(activity,name,null,false);
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

    public static void emergencyCall(Context context) {
        String emergencyNumber = "112";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + emergencyNumber));
        if (callIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(callIntent);
        } else {
            Toast.makeText(context, "Não é possível realizar chamada de emergência", Toast.LENGTH_SHORT).show();
        }
    }
}
