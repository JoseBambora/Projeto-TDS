package com.ruirua.sampleguideapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruirua.sampleguideapp.R;
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Button buttonLoginVoltar = findViewById(R.id.buttonLoginVoltar);
        Button buttonLoginParaRegister = findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginLogin = findViewById(R.id.buttonLoginLogin);

        buttonLoginVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLoginParaRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsername = findViewById(R.id.editLoginTextUsername);
                EditText editTextPassword = findViewById(R.id.editLoginTextPassword);

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.equals("teste") && password.equals("teste")) {
                    Toast.makeText(LoginActivity.this, "Login Feito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Dados Incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
