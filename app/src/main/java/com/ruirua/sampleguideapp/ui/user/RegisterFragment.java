package com.ruirua.sampleguideapp.ui.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class RegisterFragment extends Fragment {
    private FragmentActivity activity;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button buttonRegisterVoltar = view.findViewById(R.id.buttonRegisterVoltar);
        Button buttonRegisterparaLogin = view.findViewById(R.id.buttonRegisterparaLogin);
        activity = getActivity();
        buttonRegisterVoltar.setOnClickListener(v -> UIFuns.goBack(activity));
        buttonRegisterparaLogin.setOnClickListener(v -> UIFuns.changeFragment(activity,this,activity.getSupportFragmentManager(),new LoginFragment()));
        return view;
    }
}