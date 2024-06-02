package com.ruirua.sampleguideapp.ui.shared;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.SettingsRepository;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;


public class SettingsFragment extends Fragment {
    public SettingsFragment() {}

    private void handleDarkMode(boolean isChecked) {
        if (isChecked)
            SettingsRepository.getInstance().changeToDarkMode();
        else
            SettingsRepository.getInstance().changeToLightMode();
        UIFuns.configureTheme(getActivity());
        UIFuns.refreshPage(getFragmentManager(),this);
    }

    private void handleLocationChange(boolean isChecked) {
        if(isChecked)
            SettingsRepository.getInstance().turnOnLocationListener();
        else
            SettingsRepository.getInstance().turnOffLocationListener();
    }

    private void handleNotificationChange(boolean isChecked) {
        if(isChecked)
            SettingsRepository.getInstance().turnOnNotifications();
        else
            SettingsRepository.getInstance().turnOffNotifications();
    }
    private void handlePriorityChange(String newPriority) {
        SettingsRepository.getInstance().setPriority(newPriority);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        SwitchMaterial sm1 = view.findViewById(R.id.toggleButtonMode);
        sm1.setChecked(SettingsRepository.getInstance().isDarkMode());
        sm1.setOnCheckedChangeListener((buttonView, isChecked) -> this.handleDarkMode(isChecked));


        SwitchMaterial sm3 = view.findViewById(R.id.switchNotification);
        sm3.setChecked(SettingsRepository.getInstance().isOnLocationListener());
        sm3.setOnCheckedChangeListener((buttonView, isChecked) -> this.handleNotificationChange(isChecked));



        SwitchMaterial sm2 = view.findViewById(R.id.switchLocationSensor);
        sm2.setChecked(SettingsRepository.getInstance().isOnLocationListener());
        sm2.setOnCheckedChangeListener((buttonView, isChecked) -> this.handleLocationChange(isChecked));

        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(120);
        numberPicker.setMinValue(1);
        numberPicker.setValue(SettingsRepository.getInstance().getDelay());
        numberPicker.setOnValueChangedListener((picker,oldvalue,newval) -> SettingsRepository.getInstance().setDelay(newval));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, SettingsRepository.getInstance().getPossiblePriorityValues());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(SettingsRepository.getInstance().getPositionPriority());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                handlePriorityChange(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

}
