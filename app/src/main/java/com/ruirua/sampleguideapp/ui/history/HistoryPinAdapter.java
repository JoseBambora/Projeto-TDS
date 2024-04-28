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
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.ui.pins.PinFragment;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin, parent, false);
        return new TrailPinViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TrailPinViewHolder holder, int position) {
        LiveData<Pin> pin = pins.get(position);
        holder.bind(pin);
    }

    @Override
    public int getItemCount() {
        return pins.size();
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
            itemView.findViewById(R.id.divider_image).setVisibility(View.GONE);
        }

        public void bind(LiveData<Pin> pinLiveData) {
            pinLiveData.observe((LifecycleOwner) activity, pin -> {
                if (pin != null) {
                    pinNameTextView.setText(pin.getPin_name());
                    UIFuns.setPinImage(pin,pinImageView);
                    UIFuns.checkMultimedia(pin.getMediaList(),pinHasImage,pinHasAudio,pinHasVideo);
                    itemView.setOnClickListener(view -> UIFuns.changeFragment(fragmentManager, new PinFragment(pin.getId())));
                }
            });

        }

    }
}
