package com.ruirua.sampleguideapp.ui.user;

import static com.ruirua.sampleguideapp.ui.utils.UIFuns.configureTheme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ruirua.sampleguideapp.ui.utils.Settings;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra("action");
        configureTheme(this);

        Fragment fragment = "register".equals(action) ? new RegisterFragment() : "login".equals(action) ? new LoginFragment() : new UserFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();


    }
}

