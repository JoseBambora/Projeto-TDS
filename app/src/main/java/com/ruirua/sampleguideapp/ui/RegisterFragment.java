package com.ruirua.sampleguideapp.ui;


import android.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruirua.sampleguideapp.R;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button buttonRegisterVoltar = view.findViewById(R.id.buttonRegisterVoltar);
        Button buttonRegisterparaLogin = view.findViewById(R.id.buttonRegisterparaLogin);

        buttonRegisterVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        buttonRegisterparaLogin.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new LoginFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}