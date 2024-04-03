package com.ruirua.sampleguideapp.ui.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Settings {
    private boolean darkMode = true;

    private static Settings instance = null;
    private Context context = null;
    private static String file = "settings.json";

    public static Settings getInstance() {
        return instance;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public static void createInstance(Context context) {
        if(instance == null) {
            try {
                InputStream is  = context.getAssets().open(file);
                InputStreamReader reader = new InputStreamReader(is);
                Gson gson = new Gson();
                instance = gson.fromJson(reader, Settings.class);
            } catch (IOException ignored) {
                instance = new Settings();
            }
            instance.setContext(context);
        }
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public boolean isLightMode() {
        return !darkMode;
    }
    private void saveFile() {
        try {
            FileWriter writer = new FileWriter(file);
            Gson gson = new Gson();
            gson.toJson(this,this.getClass(),writer);
        } catch (IOException ignored) {}
    }
    public void changeToDarkMode() {
        darkMode = true;
        saveFile();
    }

    public void changeToLightMode() {
        darkMode = false;
        saveFile();
    }
}
