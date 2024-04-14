package com.ruirua.sampleguideapp.ui.trails;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TrailsRecyclerViewAdapter extends RecyclerView.Adapter<TrailsRecyclerViewAdapter.ViewHolder> {

    private final List<Trail> mValues;

    public TrailsRecyclerViewAdapter(List<Trail> items) {
        mValues = items;
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
        Picasso.get()
                .load(trail.getTrailImg().replace("http:", "https:"))
                .into(holder.imageView);
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
