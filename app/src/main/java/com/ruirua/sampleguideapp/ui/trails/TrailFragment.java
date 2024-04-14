package com.ruirua.sampleguideapp.ui.trails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruirua.sampleguideapp.R;


public class TrailFragment extends Fragment {

    private int trailId;

    public TrailFragment(int trailId) {
        // Required empty public constructor
        this.trailId = trailId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trail, container, false);
        TextView id = v.findViewById(R.id.idTrilho);
        id.setText(String.valueOf(trailId));
        return v;
    }
}