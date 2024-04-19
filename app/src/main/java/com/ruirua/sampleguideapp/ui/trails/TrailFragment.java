package com.ruirua.sampleguideapp.ui.trails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.user.LoginData;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;
import com.ruirua.sampleguideapp.viewModel.TrailsViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailFragment extends Fragment {

    private int trailId;

    private FragmentManager fragmentManager;

    public TrailFragment(int trail, FragmentManager fragmentManager) {
        this.trailId = trail;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeTrail(view);
    }

    private void observeTrail(View view) {
        TrailsViewModel tvm = new ViewModelProvider(this).get(TrailsViewModel.class);
        tvm.getTrail(trailId).observe(getViewLifecycleOwner(), trail -> {
            if (trail != null) {
                populateTrailInfo(trail, view);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void populateTrailInfo(Trail trail, View view) {
        TextView id = view.findViewById(R.id.idTrilho);
        id.setText("ID: " + trail.getId());

        ImageView trailImg = view.findViewById(R.id.trailImg);
        Log.d("IMAGE", trail.getTrailImg());
        loadTrailImage(trail.getTrailImg(), trailImg);

        TextView trailName = view.findViewById(R.id.trailName);
        trailName.setText(trail.getTrailName());

        TextView trailDesc = view.findViewById(R.id.trailDesc);
        trailDesc.setText(trail.getTrailDesc());

        TextView trailDuration = view.findViewById(R.id.trailDuration);
        trailDuration.setText("Duração: " + trail.getTrailDuration());

        TextView trailDifficulty = view.findViewById(R.id.trailDifficulty);
        trailDifficulty.setText("Dificuldade: " + trail.getTrailDifficulty());

        setupTrailPinsRecyclerView(trail.getPins(), view);
    }

    private void loadTrailImage(String imageUrl, ImageView trailImg) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            // Replace "http://" with "https://"
            imageUrl = imageUrl.replace("http:", "https:").replace("/trail","");

            Log.d("IMAGE", imageUrl);
            Picasso.get().load(imageUrl).into(trailImg);
        } else {
            // Image URL is null or empty
            if (trailImg != null) {
                trailImg.setVisibility(View.GONE);
            }
        }
    }

    private void setupTrailPinsRecyclerView(List<Pin> pinList, View view) {
        RecyclerView recyclerViewPins = view.findViewById(R.id.recycler_view_pins);
        recyclerViewPins.setLayoutManager(new LinearLayoutManager(requireContext()));
        TrailPinRecyclerView adapter = new TrailPinRecyclerView(pinList, fragmentManager);
        recyclerViewPins.setAdapter(adapter);
    }

}

