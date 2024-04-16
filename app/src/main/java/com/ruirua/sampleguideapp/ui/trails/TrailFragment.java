package com.ruirua.sampleguideapp.ui.trails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailFragment extends Fragment {

    private Trail trail;

    private FragmentManager fragmentManager;

    public TrailFragment(Trail trail,FragmentManager fragmentManager) {
        this.trail = trail;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trail, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView id = view.findViewById(R.id.idTrilho);
        id.setText("ID: " + trail.getId());

        ImageView trailImg = view.findViewById(R.id.trailImg);

        if (trail.getTrailImg() != null && !trail.getTrailImg().isEmpty()) {
            // Replace "http://" with "https://"
            String imageUrl = trail.getTrailImg().replace("http:", "https:");
            Picasso.get().load(imageUrl).into(trailImg, new Callback() {
                @Override
                public void onSuccess() {
                    // Image loaded successfully
                }

                @Override
                public void onError(Exception e) {
                    // Error loading image
                    trailImg.setVisibility(View.GONE);
                }
            });
        } else {
            // Image URL is null or empty
            trailImg.setVisibility(View.GONE);
        }

        TextView trailName = view.findViewById(R.id.trailName);
        trailName.setText(trail.getTrailName());

        TextView trailDesc = view.findViewById(R.id.trailDesc);
        trailDesc.setText(trail.getTrailDesc());

        TextView trailDuration = view.findViewById(R.id.trailDuration);
        trailDuration.setText("Duração: " + trail.getTrailDuration());

        TextView trailDifficulty = view.findViewById(R.id.trailDifficulty);
        trailDifficulty.setText("Dificuldade: " + trail.getTrailDifficulty());

        // RecyclerView for pins
        RecyclerView recyclerViewPins = view.findViewById(R.id.recycler_view_pins);
        recyclerViewPins.setLayoutManager(new LinearLayoutManager(requireContext()));
        List<Pin> pinList = trail.getPins();
        TrailPinRecyclerView adapter = new TrailPinRecyclerView(pinList,getActivity());
        recyclerViewPins.setAdapter(adapter);
    }
}
