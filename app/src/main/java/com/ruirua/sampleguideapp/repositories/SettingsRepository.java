package com.ruirua.sampleguideapp.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.location.LocationRequest;
import com.ruirua.sampleguideapp.notifications.NotificationSystem;
import com.ruirua.sampleguideapp.sensors.OurLocationListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsRepository {
    private boolean darkMode = false;
    private boolean onLocationListener = true;
    private boolean notification = true;

    private int delayLocationSensor = 10;

    private int priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
    private Context context = null;
    private static String file = "settings.json";

    private void setContext(Context context) {
        this.context = context;
    }



    public boolean isDarkMode() {
        return darkMode;
    }

    public boolean isLightMode() {
        return !darkMode;
    }

    public boolean isOnLocationListener (){
        return onLocationListener;
    }
    private void saveFile() {
        SharedPreferences prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("darkMode", darkMode);
        editor.putBoolean("onLocationListener", onLocationListener);
        editor.putBoolean("notification", notification);
        editor.putInt("delayLocationSensor", delayLocationSensor);
        editor.putInt("priority", priority);
        editor.apply();
    }
    public void changeToDarkMode() {
        darkMode = true;
        saveFile();
    }

    public void changeToLightMode() {
        darkMode = false;
        saveFile();
    }

    public void turnOnLocationListener() {
        onLocationListener = true;
        OurLocationListener.getInstance().onStart();
        saveFile();
    }

    public void turnOffLocationListener() {
        onLocationListener = false;
        OurLocationListener.getInstance().onStop();
        saveFile();
    }

    public void turnOnNotifications() {
        notification = true;
        NotificationSystem.setNotify(notification);
        saveFile();

    }
    public void turnOffNotifications() {
        notification = false;
        NotificationSystem.setNotify(notification);
        saveFile();
    }

    public void setDelay(int number) {
        this.delayLocationSensor = number;
        OurLocationListener.getInstance().setDelay(number);
        OurLocationListener.getInstance().restart();
        saveFile();
    }
    public int getDelay() {
        return delayLocationSensor;
    }

    private String converterToString() {
        switch (priority) {
            case LocationRequest.PRIORITY_HIGH_ACCURACY:
                return "Accuracy alta";
            case LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY:
                return "Accuracy balanceada";
            case LocationRequest.PRIORITY_LOW_POWER:
                return "Bateria baixa";
            default:
                return "Sem bateria";
        }
    }
    private int converterToInt(String input) {
        switch (input) {
            case "Accuracy alta":
                return LocationRequest.PRIORITY_HIGH_ACCURACY;
            case "Accuracy balanceada":
                return  LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;
            case "Bateria baixa":
                return  LocationRequest.PRIORITY_LOW_POWER;
            default:
                return LocationRequest.PRIORITY_NO_POWER;
        }
    }

    private List<String> getPriorityValues() {
        List<String> list = new ArrayList<>(4);
        list.add("Accuracy alta");
        list.add("Accuracy balanceada");
        list.add("Bateria baixa");
        list.add("Sem bateria");
        return list;
    }

    public String[] getPossiblePriorityValues() {
        return getPriorityValues().toArray(new String[0]);
    }

    public int getPositionPriority() {
        return getPriorityValues().indexOf(converterToString());
    }

    public void setPriority(String selected) {
        priority = converterToInt(selected);
        OurLocationListener.getInstance().setPriority(priority);
        OurLocationListener.getInstance().restart();
        saveFile();
    }


    private static SettingsRepository instance = null;

    public static void createInstance(Context context) {
        if(instance == null) {
            instance = new SettingsRepository();
            SharedPreferences prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE);
            instance.darkMode = prefs.getBoolean("darkMode", false);
            instance.onLocationListener = prefs.getBoolean("onLocationListener", true);
            instance.notification = prefs.getBoolean("notification", true);
            instance.delayLocationSensor = prefs.getInt("delayLocationSensor", 10);
            instance.priority = prefs.getInt("priority", LocationRequest.PRIORITY_HIGH_ACCURACY);
            instance.setContext(context);
        }
    }
    public static SettingsRepository getInstance() {
        return instance;
    }
}
