package com.ruirua.sampleguideapp;

import android.app.Application;
import android.util.Log;

import com.ruirua.sampleguideapp.notifications.NotificationSystem;
import com.ruirua.sampleguideapp.repositories.PinRepository;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.utils.Settings;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your UserRepository here
        Log.d("DebugApp","A inicializar");
        UserRepository.createUserRepository(this);
        PinRepository.createInstance(this);
        Settings.createInstance(this);
        NotificationSystem.createNotificationChannel(this);
    }
}
