package com.ruirua.sampleguideapp.ui.shared;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class SettingsFragment extends Fragment {
    private Activity activity;
    public SettingsFragment() {
        // Required empty public constructor
    }

    private void setOnClicks(View view) {
        Button buttonChangePassword = view.findViewById(R.id.buttonChangePassword);
        Button buttonSettingsVoltar = view.findViewById(R.id.buttonSettingsVoltar);
        activity = getActivity();
        buttonSettingsVoltar.setOnClickListener(v -> UIFuns.goBack(activity));
        buttonChangePassword.setOnClickListener(v -> Toast.makeText(getContext(), "Botão Mudar Palavra-Passe clicado", Toast.LENGTH_SHORT).show());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setOnClicks(view);
        RadioGroup radioGroupMode = view.findViewById(R.id.radioGroupMode);
        radioGroupMode.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = view.findViewById(checkedId);
            if (radioButton != null) {
                String mode = radioButton.getText().toString();
                Toast.makeText(getContext(), "Modo selecionado: " + mode, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
