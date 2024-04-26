package com.ruirua.sampleguideapp;

import android.app.Application;
import android.util.Log;

import com.ruirua.sampleguideapp.notifications.NotificationSystem;
import com.ruirua.sampleguideapp.repositories.HistoryRepository;
import com.ruirua.sampleguideapp.repositories.MediaRepository;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.repositories.SettingsRepository;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your UserRepository here
        Log.d("DebugApp","A inicializar");
        UserRepository.createInstance(this);
        MediaRepository.createInstance(this);
        HistoryRepository.createInstance(this);
        SettingsRepository.createInstance(this);
        NotificationSystem.createNotificationChannel(this);
    }
}
