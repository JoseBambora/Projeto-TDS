package com.ruirua.sampleguideapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ruirua.sampleguideapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial_menu);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonList = findViewById(R.id.buttonTourRoutes);

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            intent.putExtra("action", "register");
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            intent.putExtra("action", "login");
            startActivity(intent);
        });

        buttonList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrailActivity.class);
            startActivity(intent);
        });
    }
}
