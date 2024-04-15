package com.ruirua.sampleguideapp.ui.trails;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TrailFragment extends Fragment {

    private Trail trail;

    public TrailFragment(Trail trail) {
        this.trail = trail;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trail, container, false);

        TextView id = v.findViewById(R.id.idTrilho);
        id.setText("ID: "+ trail.getId());

        ImageView trailImg = v.findViewById(R.id.trailImg);

        if (trail.getTrailImg() != null && !trail.getTrailImg().isEmpty()) {
            // Replace "http://" with "https://"
            String imageUrl = trail.getTrailImg().replace("http:", "https:");
            Picasso.get().load(imageUrl).into(trailImg, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("Image", "Image loaded successfully");
                }

                @Override
                public void onError(Exception e) {
                    Log.e("Image", "Error loading image: " + e.getMessage());
                    trailImg.setVisibility(View.GONE);
                }
            });
        } else {
            Log.d("Image", "Image URL is null or empty");
            trailImg.setVisibility(View.GONE);
        }

        TextView trailName = v.findViewById(R.id.trailName);
        trailName.setText(trail.getTrailName());

        TextView trailDesc = v.findViewById(R.id.trailDesc);
        trailDesc.setText(trail.getTrailDesc());

        TextView trailDuration = v.findViewById(R.id.trailDuration);
        trailDuration.setText("Duração: " + trail.getTrailDuration());

        TextView trailDifficulty = v.findViewById(R.id.trailDifficulty);
        trailDifficulty.setText("Dificuldade: " + trail.getTrailDifficulty());

        return v;
    }
}
