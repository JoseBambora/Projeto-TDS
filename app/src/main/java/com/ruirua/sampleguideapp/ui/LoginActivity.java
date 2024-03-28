package com.ruirua.sampleguideapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Button buttonLoginVoltar = findViewById(R.id.buttonLoginVoltar);
        Button buttonLoginParaRegister = findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginLogin = findViewById(R.id.buttonLoginLogin);

        buttonLoginVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        buttonLoginParaRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        buttonLoginLogin.setOnClickListener(v -> {
            EditText editTextUsername = findViewById(R.id.editLoginTextUsername);
            EditText editTextPassword = findViewById(R.id.editLoginTextPassword);

            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            UserRepository ur = UserRepository.getInstance();
            Log.d("DebugApp","A realizar login " + ur);
            ur.login(username,password,this::postLogin);
        });
    }

    private void postLogin(boolean sucess) {
        Log.d("DebugApp","Login feito " + sucess);
        if (sucess) {
            UserRepository ur = UserRepository.getInstance();
            String t1 = ur.getCsrfToken();
            String t2 = ur.getSessionId();
            Toast.makeText(LoginActivity.this, "Login Feito, tokens: " + t1 + " | " + t2, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Dados Incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
