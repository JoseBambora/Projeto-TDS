package com.ruirua.sampleguideapp.ui.history;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.ui.pins.PinFragment;
import com.ruirua.sampleguideapp.ui.trails.TrailPinRecyclerView;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryPinAdapter extends RecyclerView.Adapter<HistoryPinAdapter.TrailPinViewHolder> {
    private List<LiveData<Pin>> pins;
    private FragmentManager fragmentManager;
    private LifecycleOwner lifecycleOwner;
    private Activity activity;

    public HistoryPinAdapter(List<LiveData<Pin>> pins, LifecycleOwner lifecycleOwner,FragmentManager fragmentManager,Activity activity) {
        this.pins = pins;
        this.fragmentManager = fragmentManager;
        this.lifecycleOwner = lifecycleOwner;
        this.activity = activity;
    }
    @NonNull
    @Override
    public TrailPinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin_history, parent, false);
        return new TrailPinViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TrailPinViewHolder holder, int position) {
        LiveData<Pin> pin = pins.get(position);
        boolean isLastItem = position == getItemCount() - 1;
        holder.bind(pin, isLastItem);
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }




    public class TrailPinViewHolder extends RecyclerView.ViewHolder {
        private TextView pinNameTextView;
        private ImageView pinImageView;

        public TrailPinViewHolder(@NonNull View itemView) {
            super(itemView);
            pinImageView = itemView.findViewById(R.id.pincardimage);
            pinNameTextView = itemView.findViewById(R.id.trail_name);
        }

        public void bind(LiveData<Pin> pinLiveData, boolean isLastItem) {
            pinLiveData.observe((LifecycleOwner) activity, pin -> {

                if (pin != null) {
                    pinNameTextView.setText(pin.getPin_name());
                    List<Media> mediaList = pin.getMediaList();
                    if (!mediaList.isEmpty()) {
                        for (Media media : mediaList) {
                            if (media.isImage()) {
                                UIFuns.showImage(media.getMedia_file(), pinImageView);
                                break;
                            }
                        }
                        Picasso.get()
                                .load(mediaList.get(0).getMedia_file().replace("http:", "https:"))
                                .into(pinImageView);
                    } else {
                        pinImageView.setImageResource(R.drawable.baseline_broken_image_24);
                    }

                    itemView.setOnClickListener(view -> UIFuns.changeFragment(fragmentManager, new PinFragment(pin.getId())));
                }
            });

        }

    }
}
