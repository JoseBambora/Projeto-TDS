package com.ruirua.sampleguideapp.ui.pins;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.RelPin;
import com.ruirua.sampleguideapp.ui.initial.MainActivity;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;

import org.w3c.dom.Text;

public class PinFragment extends Fragment {

    private final int pinId;
    private Activity activity;
    private GoBackInterface goBackInterface;


    public PinFragment(GoBackInterface goBackInterface, int pinId) {
        this.pinId = pinId;
        this.goBackInterface = goBackInterface;
    }

    private void setOnClicks(View v) {
        Button buttonGoBack = v.findViewById(R.id.buttonPinVoltar);
        buttonGoBack.setOnClickListener(view -> goBackInterface.goBack());
    }

    @SuppressLint("SetTextI18n")
    private void fillInfo(Pin pin, View v) {
        Log.d("DebugApp","PIN: " + pin.toString());

        TextView idtv = v.findViewById(R.id.pinID);
        TextView nametv = v.findViewById(R.id.pinName);
        TextView coordstv = v.findViewById(R.id.coordsTV);
        TextView descttv = v.findViewById(R.id.descTV);
        TableLayout relpinsTable = v.findViewById(R.id.relpinsTable);
        idtv.setText("ID: " + pin.getId());
        nametv.setText("Name: " + pin.getId());
        coordstv.setText("Localização: (" +pin.getPin_lat() + "," + pin.getPin_lng()+ "," + pin.getPin_alt() + ")");
        descttv.setText("Description: " + pin.getPin_desc());
        int position = 1;
        for(RelPin relPin : pin.getRelPinList()) {
            TableRow tableRow = new TableRow(this.getActivity());
            TextView idTextView = new TextView(this.getActivity());
            TextView valueTextView = new TextView(this.getActivity());
            TextView attributeTextView = new TextView(this.getActivity());
            idTextView.setText(String.valueOf(relPin.getIdPin()));
            valueTextView.setText(relPin.getValue());
            attributeTextView.setText(relPin.getAttribute());

            idTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            valueTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            attributeTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            idTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            valueTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            attributeTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            if (position % 2 == 0) {  // Even positions after header (0)
                tableRow.setBackgroundColor(ContextCompat.getColor(this.getActivity(), R.color.gray));
            }
            position++;

            tableRow.addView(idTextView);
            tableRow.addView(valueTextView);
            tableRow.addView(attributeTextView);

            relpinsTable.addView(tableRow);
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container, false);
        activity = getActivity();
        PinsViewModel pvm = new ViewModelProvider(this).get(PinsViewModel.class);
        pvm.getPin(pinId).observe(getViewLifecycleOwner(), p -> this.fillInfo(p,v));
        UIFuns.configureTheme(activity);
        setOnClicks(v);
        return v;
    }
}