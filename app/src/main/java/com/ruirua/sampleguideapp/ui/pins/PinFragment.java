package com.ruirua.sampleguideapp.ui.pins;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.ui.initial.MainActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;

public class PinFragment extends Fragment {

    private final int pinId;
    private Activity activity;
    public PinFragment(int pinId) {
        this.pinId = pinId;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setOnClicks(View v) {
        Button buttonGoBack = v.findViewById(R.id.goMainPage);
        buttonGoBack.setOnClickListener(view -> UIFuns.goBack(activity));
    }

    private void fillInfo(Pin pin) {
        Log.d("DebugApp","PIN: " + pin.toString());
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container, false);
        activity = getActivity();
        TextView tv = v.findViewById(R.id.title_pin);
        tv.setText("Pin " + pinId);
        PinsViewModel pvm = new ViewModelProvider(this).get(PinsViewModel.class);
        pvm.getPin(pinId).observe(getViewLifecycleOwner(), this::fillInfo);
        setOnClicks(v);
        return v;
    }
}