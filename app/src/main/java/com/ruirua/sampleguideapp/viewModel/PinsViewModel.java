package com.ruirua.sampleguideapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.repositories.PinRepository;

import java.util.List;

public class PinsViewModel extends AndroidViewModel {

    private PinRepository repository;

    public LiveData<List<Pin>> pins;
    public PinsViewModel(@NonNull Application application) {
        super(application);
        repository= PinRepository.getInstance();
        pins = repository.getAllPins();
    }
    public LiveData<List<Pin>> getAllPins() {
        return pins;
    }
    public LiveData<Pin> getPin(int id) {
        return repository.getPin(id);
    }

}
