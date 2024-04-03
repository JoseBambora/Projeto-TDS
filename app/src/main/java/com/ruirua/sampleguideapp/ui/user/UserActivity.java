package com.ruirua.sampleguideapp.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.Settings;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String action = intent.getStringExtra("action");

        if(Settings.getInstance().isLightMode())
            setTheme(R.style.AppThemeLight);
        else
            setTheme(R.style.AppThemeDark);

        Fragment fragment = "register".equals(action) ? new RegisterFragment() : "login".equals(action) ? new LoginFragment() : new UserFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();


    }
}

