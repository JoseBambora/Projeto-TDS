package com.ruirua.sampleguideapp.ui.history;

import android.app.Activity;
import android.util.Log;
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
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.ui.trails.TrailFragment;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

import java.util.List;

public class HistoryTrailAdapter extends RecyclerView.Adapter<HistoryTrailAdapter.TrailViewHolder> {
    private List<LiveData <Trail>> trails;
    private LifecycleOwner lifecycleOwner;
    private FragmentManager fragmentManager;

    private Activity activity;
    public HistoryTrailAdapter(List<LiveData <Trail>> trails, LifecycleOwner lifecycleOwner, FragmentManager fragmentManager, Activity activity) {
        this.trails = trails;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }
    @NonNull
    @Override
    public TrailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new TrailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailViewHolder holder, int position) {
        Log.d("AVB2024", "" + position);
        LiveData <Trail> t = trails.get(position);
        t.observe(lifecycleOwner,trail-> {
            holder.mIdView.setText("ID: " + trail.getId());
            holder.mNameView.setText(trail.getTrailName());
            holder.mDifficultyView.setText("Dificuldade: "+trail.getTrailDifficulty());
            UIFuns.showImage(trail.getTrailImg(),holder.imageView);
            holder.mView.setOnClickListener(view -> UIFuns.changeFragment(this.fragmentManager,new TrailFragment(trail.getId(),fragmentManager)));
        });
    }

    @Override
    public int getItemCount() {
        return trails.size();
    }

    public static class TrailViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mNameView;
        public final ImageView imageView;

        public final TextView mDifficultyView;

        public TrailViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mNameView = view.findViewById(R.id.trail_name);
            imageView = view.findViewById(R.id.cardimage);
            mDifficultyView = view.findViewById(R.id.trail_difficulty);
        }

        @Override
        public String toString() {
            return super.toString() + mIdView;
        }

    }
}
