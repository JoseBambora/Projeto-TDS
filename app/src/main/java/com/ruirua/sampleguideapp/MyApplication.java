package com.ruirua.sampleguideapp;

import android.app.Application;

import com.ruirua.sampleguideapp.repositories.UserRepository;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize your UserRepository here
        UserRepository.createUserRepository(this);
    }
}
