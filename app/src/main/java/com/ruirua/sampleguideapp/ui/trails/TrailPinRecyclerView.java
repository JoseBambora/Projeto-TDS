package com.ruirua.sampleguideapp.ui.trails;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.ui.pins.PinFragment;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import java.util.List;

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
        boolean isLastItem = position == getItemCount() - 1;
        holder.bind(pin, isLastItem);
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
        private ImageView dividerImageView;
        public TrailPinViewHolder(@NonNull View itemView) {
            super(itemView);
            pinImageView = itemView.findViewById(R.id.pincardimage);
            pinNameTextView = itemView.findViewById(R.id.trail_name);
            pinHasImage= itemView.findViewById(R.id.hasImage);
            pinHasAudio= itemView.findViewById(R.id.hasAudio);
            pinHasVideo= itemView.findViewById(R.id.hasVideo);
            dividerImageView = itemView.findViewById(R.id.divider_image);
        }

        public void bind(Pin pin, boolean isLastItem) {
            pinNameTextView.setText(pin.getPin_name());
            UIFuns.setPinImage(pin,pinImageView);
            UIFuns.checkMultimedia(pin.getMediaList(),pinHasImage,pinHasAudio,pinHasVideo);
            itemView.setOnClickListener(view -> UIFuns.changeFragment(fragmentManager,new PinFragment(pin.getId())));
            dividerImageView.setVisibility(isLastItem ? View.GONE : View.VISIBLE);
        }
    }
}