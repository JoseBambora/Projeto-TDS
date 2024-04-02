package com.ruirua.sampleguideapp.ui.authentication;

import static androidx.navigation.Navigation.findNavController;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.MainActivity;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonLoginParaRegister = view.findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginVoltar = view.findViewById(R.id.buttonLoginVoltar);
        Button buttonLoginLogin = view.findViewById(R.id.buttonLoginLogin);

        buttonLoginVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        buttonLoginParaRegister.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        });


        buttonLoginLogin.setOnClickListener(v -> {
            EditText editTextUsername = view.findViewById(R.id.editLoginTextUsername);
            EditText editTextPassword = view.findViewById(R.id.editLoginTextPassword);

            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            UserRepository ur = UserRepository.getInstance();
            Log.d("DebugApp","A realizar login " + ur + " " + ur.isLogged());
            ur.login(username,password,this::postLogin);
            Toast.makeText(getActivity(), "A realizar login", Toast.LENGTH_SHORT).show();
        });
    }

    private void postLogin(boolean success) {
        Log.d("DebugApp","Login feito " + success);
        if (success) {
            UserRepository ur = UserRepository.getInstance();
            boolean t1 = ur.isPremium();
            boolean t2 = ur.isStandard();
            Toast.makeText(getActivity(), "Login Feito, tokens: " + t1 + " | " + t2, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Dados Incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}