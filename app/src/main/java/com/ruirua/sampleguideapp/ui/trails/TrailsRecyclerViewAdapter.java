package com.ruirua.sampleguideapp.ui.trails;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TrailsRecyclerViewAdapter extends RecyclerView.Adapter<TrailsRecyclerViewAdapter.ViewHolder> {

    private final List<Trail> mValues;


    private FragmentManager fragmentManager;
    private Activity activity;

    public TrailsRecyclerViewAdapter(List<Trail> items,FragmentManager fragmentManager, Activity activity) {
        mValues = items;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Trail trail = mValues.get(position);
        holder.mIdView.setText("ID: " + trail.getId());
        holder.mNameView.setText(trail.getTrailName());
        holder.mDifficultyView.setText("Dificuldade: "+trail.getTrailDifficulty());
        UIFuns.showImage(trail.getTrailImg(),holder.imageView,activity);
        holder.mView.setOnClickListener(view -> UIFuns.changeFragment(this.fragmentManager,new TrailFragment(trail.getId(),fragmentManager)));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mNameView;
        public final ImageView imageView;

        public final TextView mDifficultyView;

        public ViewHolder(View view) {
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
