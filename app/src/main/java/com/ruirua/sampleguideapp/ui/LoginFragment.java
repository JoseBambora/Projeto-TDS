package com.ruirua.sampleguideapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;

public class LoginFragment extends Fragment {

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_screen, container, false);

        Button buttonLoginVoltar = view.findViewById(R.id.buttonLoginVoltar);
        Button buttonLoginParaRegister = view.findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginLogin = view.findViewById(R.id.buttonLoginLogin);

        buttonLoginVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        buttonLoginParaRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
            intent.putExtra("action", "register");
            startActivity(intent);
            getActivity().finish();
        });

        buttonLoginLogin.setOnClickListener(v -> {
            EditText editTextUsername = view.findViewById(R.id.editLoginTextUsername);
            EditText editTextPassword = view.findViewById(R.id.editLoginTextPassword);

            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            UserRepository ur = UserRepository.getInstance();
            Log.d("DebugApp","A realizar login " + ur);
            ur.login(username,password,this::postLogin);
        });

        return view;
    }

    private void postLogin(boolean success) {
        Log.d("DebugApp","Login feito " + success);
        if (success) {
            UserRepository ur = UserRepository.getInstance();
            String t1 = ur.getCsrfToken();
            String t2 = ur.getSessionId();
            Toast.makeText(getActivity(), "Login Feito, tokens: " + t1 + " | " + t2, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Dados Incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}