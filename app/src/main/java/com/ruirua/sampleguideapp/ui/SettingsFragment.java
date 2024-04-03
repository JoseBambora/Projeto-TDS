package com.ruirua.sampleguideapp.ui;

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

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button buttonChangePassword = view.findViewById(R.id.buttonChangePassword);
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Botão Mudar Palavra-Passe clicado", Toast.LENGTH_SHORT).show();
            }
        });
        RadioGroup radioGroupMode = view.findViewById(R.id.radioGroupMode);
        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = view.findViewById(checkedId);
                if (radioButton != null) {
                    String mode = radioButton.getText().toString();
                    Toast.makeText(getContext(), "Modo selecionado: " + mode, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
