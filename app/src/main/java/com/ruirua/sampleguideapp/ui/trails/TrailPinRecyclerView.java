package com.ruirua.sampleguideapp.ui.trails;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.ui.pins.PinActivity;
import com.ruirua.sampleguideapp.ui.pins.PinFragment;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrailPinRecyclerView extends RecyclerView.Adapter<TrailPinRecyclerView.TrailPinViewHolder> {
    private List<Pin> pinList;
    private FragmentManager fragmentManager;

    public TrailPinRecyclerView(List<Pin> pinList, FragmentManager fragmentManager) {
        this.pinList = pinList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public TrailPinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin, parent, false);
        return new TrailPinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailPinViewHolder holder, int position) {
        Pin pin = pinList.get(position);
        holder.bind(pin);
    }

    @Override
    public int getItemCount() {
        return pinList.size();
    }

    public class TrailPinViewHolder extends RecyclerView.ViewHolder {
        private TextView pinNameTextView;
        private ImageView pinImageView;

        public TrailPinViewHolder(@NonNull View itemView) {
            super(itemView);
            pinImageView = itemView.findViewById(R.id.pincardimage);
            pinNameTextView = itemView.findViewById(R.id.trail_name);
        }

        public void bind(Pin pin) {
            pinNameTextView.setText(pin.getPin_name());
            List<Media> mediaList = pin.getMediaList();
            if (!mediaList.isEmpty()) {
                Picasso.get()
                        .load(mediaList.get(0).getMedia_file().replace("http:", "https:"))
                        .into(pinImageView);
            } else {
                pinImageView.setImageResource(R.drawable.baseline_broken_image_24);
            }

            // Setting click listener to handle navigation to PinFragment
            Map<String,String> params = new HashMap<>();
            params.put("pinid", String.valueOf(pin.getId()));
            itemView.setOnClickListener(view -> UIFuns.changeFragment(fragmentManager,new PinFragment(pin.getId())));
        }
    }
}