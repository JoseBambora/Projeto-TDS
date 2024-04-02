package com.ruirua.sampleguideapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.user.UserActivity;
import com.ruirua.sampleguideapp.ui.trails.TrailActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial_menu);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonList = findViewById(R.id.buttonTourRoutes);
        Button buttonEmergency = findViewById(R.id.buttonEmergency);
        Button buttonUserInfo = findViewById(R.id.userInfo);

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra("action", "register");
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra("action", "login");
            startActivity(intent);
        });

        buttonList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrailActivity.class);
            startActivity(intent);
        });

        buttonEmergency.setOnClickListener(v -> {
            Toast.makeText(this,"Funcionalidade desativada para evitar destrastes", Toast.LENGTH_SHORT).show();

            // String emergencyNumber = "112";
            // Intent callIntent = new Intent(Intent.ACTION_CALL);
            // callIntent.setData(Uri.parse("tel:" + emergencyNumber));
            // if (callIntent.resolveActivity(getPackageManager()) != null) {
            //     startActivity(callIntent);
            // } else {
            //     Toast.makeText(this, "Não é possível realizar chamada de emergência", Toast.LENGTH_SHORT).show();
            // }
        });

        buttonUserInfo.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra("action", "userinfo");
            startActivity(intent);
        });

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
        }

        if(!UserRepository.getInstance().isLogged()) {
            buttonUserInfo.setVisibility(View.GONE);
        }
    }

}
