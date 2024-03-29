package com.ruirua.sampleguideapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
public class RegisterFragment extends Fragment {

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_screen, container, false);

        Button buttonRegisterVoltar = view.findViewById(R.id.buttonRegisterVoltar);
        Button buttonRegisterparaLogin = view.findViewById(R.id.buttonRegisterparaLogin);

        buttonRegisterVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        buttonRegisterparaLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
            intent.putExtra("action", "login");
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }
}