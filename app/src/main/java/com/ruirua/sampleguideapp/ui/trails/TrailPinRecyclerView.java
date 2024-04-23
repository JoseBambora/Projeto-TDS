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

    private Activity activity;

    public TrailPinRecyclerView(List<Pin> pinList, FragmentManager fragmentManager, Activity activity) {
        this.pinList = pinList;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
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

        private ImageView pinHasImage;
        private ImageView pinHasAudio;
        private ImageView pinHasVideo;

        public TrailPinViewHolder(@NonNull View itemView) {
            super(itemView);
            pinImageView = itemView.findViewById(R.id.pincardimage);
            pinNameTextView = itemView.findViewById(R.id.trail_name);
            pinHasImage= itemView.findViewById(R.id.hasImage);
            pinHasAudio= itemView.findViewById(R.id.hasAudio);
            pinHasVideo= itemView.findViewById(R.id.hasVideo);
        }

        private void handleImage(List<Media>mediaList){
            boolean hasImage = false;
            for (Media media : mediaList) {
                if (media.isImage()) {
                    hasImage = true;
                    break;
                }
            }
            if (hasImage) pinHasImage.setImageResource(R.drawable.baseline_check_24);
            else pinHasImage.setImageResource(R.drawable.baseline_close_24);
        }

        private void handleAudio(List<Media>mediaList){
            boolean hasAudio = false;
            for (Media media : mediaList) {
                if (media.isAudio()) {
                    hasAudio = true;
                    break;
                }
            }
            if (hasAudio) pinHasAudio.setImageResource(R.drawable.baseline_check_24);
            else pinHasAudio.setImageResource(R.drawable.baseline_close_24);
        }

        private void handleVideo(List<Media>mediaList){
            boolean hasVideo = false;
            for (Media media : mediaList) {
                if (media.isVideo()) {
                    hasVideo = true;
                    break;
                }
            }
            if (hasVideo) pinHasVideo.setImageResource(R.drawable.baseline_check_24);
            else pinHasVideo.setImageResource(R.drawable.baseline_close_24);
        }

        private void checkMultimedia(List<Media> mediaList){
            handleImage(mediaList);
            handleAudio(mediaList);
            handleVideo(mediaList);
        }

        public void bind(Pin pin) {
            pinNameTextView.setText(pin.getPin_name());
            List<Media> mediaList = pin.getMediaList();
            if (!mediaList.isEmpty()) {
                for(Media media : mediaList)
                    if(media.isImage())
                        UIFuns.showImage(media.getMedia_file(),pinImageView,activity);
                Picasso.get()
                        .load(mediaList.get(0).getMedia_file().replace("http:", "https:"))
                        .into(pinImageView);
            } else {
                pinImageView.setImageResource(R.drawable.baseline_broken_image_24);
            }
            checkMultimedia(mediaList);
            Map<String,String> params = new HashMap<>();
            params.put("pinid", String.valueOf(pin.getId()));
            itemView.setOnClickListener(view -> UIFuns.changeFragment(fragmentManager,new PinFragment(pin.getId())));
        }
    }
}