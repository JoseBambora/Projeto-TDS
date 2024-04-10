package com.ruirua.sampleguideapp.ui.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class RegisterFragment extends Fragment {
    private GoBackInterface goBackInterface;
    private FragmentManager fragmentManager;

    public RegisterFragment(GoBackInterface goBackInterface, FragmentManager fragmentManager) {
        this.goBackInterface = goBackInterface;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button buttonRegisterVoltar = view.findViewById(R.id.buttonRegisterVoltar);
        Button buttonRegisterparaLogin = view.findViewById(R.id.buttonRegisterparaLogin);
        buttonRegisterVoltar.setOnClickListener(v -> goBackInterface.goBack());
        buttonRegisterparaLogin.setOnClickListener(v -> UIFuns.changeFragment(fragmentManager,new LoginFragment(this.goBackInterface,fragmentManager)));
        return view;
    }
}