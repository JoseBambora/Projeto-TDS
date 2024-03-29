package com.ruirua.sampleguideapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ruirua.sampleguideapp.R;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String action = intent.getStringExtra("action");

        if ("register".equals(action)) {loadRegisterFragment();
        }
        else if ("login".equals(action)){loadLoginFragment();}

    }

    private void loadLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, loginFragment);
        fragmentTransaction.commit();
    }

    // Método para carregar o fragmento de registro
    private void loadRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

