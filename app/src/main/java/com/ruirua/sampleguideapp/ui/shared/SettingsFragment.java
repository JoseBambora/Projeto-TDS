package com.ruirua.sampleguideapp.ui.shared;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.Settings;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;


public class SettingsFragment extends Fragment {
    private GoBackInterface onBackPressedListener;
    public SettingsFragment(GoBackInterface onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    private void goBack() {
        onBackPressedListener.goBack();
    }

    private void setOnClicks(View view) {
        Button buttonChangePassword = view.findViewById(R.id.buttonChangePassword);
        Button buttonSettingsVoltar = view.findViewById(R.id.buttonSettingsVoltar);
        buttonSettingsVoltar.setOnClickListener(v -> goBack());
        buttonChangePassword.setOnClickListener(v -> Toast.makeText(getContext(), "Botão Mudar Palavra-Passe clicado", Toast.LENGTH_SHORT).show());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setOnClicks(view);

        ToggleButton toggleButtonMode = view.findViewById(R.id.toggleButtonMode);
        toggleButtonMode.setChecked(Settings.getInstance().isDarkMode());
        toggleButtonMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Modo escuro ativado
                Settings.getInstance().changeToDarkMode();
                Toast.makeText(getContext(), "Modo escuro ativado", Toast.LENGTH_SHORT).show();
            } else {
                // Modo escuro desativado
                Settings.getInstance().changeToLightMode();
                Toast.makeText(getContext(), "Modo escuro desativado", Toast.LENGTH_SHORT).show();
            }
        });
        UIFuns.configureTheme(getActivity());

        return view;
    }

}
