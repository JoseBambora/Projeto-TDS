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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.RelPin;
import com.ruirua.sampleguideapp.ui.initial.MainActivity;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PinFragment extends Fragment {

    private final int pinId;
    private Activity activity;

    private Pin pin;
    private GoBackInterface goBackInterface;


    public PinFragment(GoBackInterface goBackInterface, int pinId) {
        this.pinId = pinId;
        this.goBackInterface = goBackInterface;
    }

    private void setOnClicks(View v) {
        Button buttonGoBack = v.findViewById(R.id.buttonPinVoltar);
        buttonGoBack.setOnClickListener(view -> goBackInterface.goBack());
    }

    private void setMedia(View v) {
        List<Media> mediaList = pin.getMediaList();
        for(Media m : mediaList) {

            if(m.isImage()) {
                ImageView iv = v.findViewById(R.id.imagePin);
                Picasso.get()
                        .load(m.getMedia_file().replace("http:", "https:"))
                        .into(iv);
            }
        }
    }
    private void setGeneralContent(View v) {
        TextInputEditText idtv = v.findViewById(R.id.pinID);
        TextInputEditText nametv = v.findViewById(R.id.pinName);
        TextInputEditText coordstv = v.findViewById(R.id.pinLocation);
        TextInputEditText descttv = v.findViewById(R.id.pinDesc);

        idtv.setEnabled(false);
        nametv.setEnabled(false);
        coordstv.setEnabled(false);
        descttv.setEnabled(false);

        idtv.setText(String.valueOf(pin.getId()));
        nametv.setText(String.valueOf(pin.getId()));
        coordstv.setText("(" +pin.getPin_lat() + "," + pin.getPin_lng()+ "," + pin.getPin_alt() + ")");
        descttv.setText(pin.getPin_desc());
    }

    private void setTable(View v) {
        TableLayout relpinsTable = v.findViewById(R.id.relpinsTable);
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
    private void fillInfo(Pin pin, View v) {
        this.pin = pin;
        Log.d("DebugApp","PIN: " + pin.toString());
        setMedia(v);
        setGeneralContent(v);
        setTable(v);
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