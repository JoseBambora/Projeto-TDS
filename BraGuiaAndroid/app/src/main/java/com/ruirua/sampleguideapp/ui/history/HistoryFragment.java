package com.ruirua.sampleguideapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.repositories.HistoryRepository;
import com.ruirua.sampleguideapp.viewModel.PinsViewModel;
import com.ruirua.sampleguideapp.viewModel.TrailsViewModel;

import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryRepository historyRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyRepository = HistoryRepository.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        RecyclerView recyclerViewP = view.findViewById(R.id.historyPinRecyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewP.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Integer> historyPinIds = historyRepository.getHistoryPins();
        List<Integer> historyTrailIds = historyRepository.getHistoryTrails();

        PinsViewModel pvm = new ViewModelProvider(this).get(PinsViewModel.class);
        TrailsViewModel tvm = new ViewModelProvider(this).get(TrailsViewModel.class);

        List<LiveData<Pin>> pins = pvm.getPins(historyPinIds);
        List<LiveData<Trail>> trails = tvm.getTrails(historyTrailIds);
        TextView emptyTextView = view.findViewById(R.id.emptyTextView);
        TextView trailsText = view.findViewById(R.id.trails);
        TextView pinsText= view.findViewById(R.id.pins);

        if (pins.isEmpty() && trails.isEmpty()) {

            emptyTextView.setVisibility(View.VISIBLE);
            trailsText.setVisibility(View.GONE);
            pinsText.setVisibility(View.GONE);
        } else {

            emptyTextView.setVisibility(View.GONE);
            trailsText.setVisibility(View.VISIBLE);
            pinsText.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new HistoryTrailAdapter(trails, getViewLifecycleOwner(), getFragmentManager(), getActivity()));
            recyclerViewP.setAdapter(new HistoryPinAdapter(pins, getViewLifecycleOwner(), getFragmentManager(), getActivity()));

        }
    }
}
