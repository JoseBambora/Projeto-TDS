package com.ruirua.sampleguideapp;

import android.app.Application;
import android.util.Log;

import com.ruirua.sampleguideapp.repositories.UserRepository;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your UserRepository here
        Log.d("DebugApp","A inicializar");
        UserRepository.createUserRepository(this);
    }
}
