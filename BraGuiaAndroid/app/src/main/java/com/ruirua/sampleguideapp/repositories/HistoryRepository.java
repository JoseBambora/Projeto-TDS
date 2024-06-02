package com.ruirua.sampleguideapp.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HistoryRepository {
    private final Context context;
    private final Set<Integer> historyPins = new HashSet<>();
    private final Set<Integer> historyTrails = new HashSet<>();
    private final String file = "history";

    private  HistoryRepository(Context context) {
        this.context = context;
        loadInfo();
    }
    private void saveInfo(String name, Set<Integer> data) {
        SharedPreferences prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(name,data.stream().map(Object::toString).collect(Collectors.toSet()));
        editor.apply();
    }
    private void loadInfo(String name, Set<Integer> data) {
        SharedPreferences prefs = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        Set<String> data_string = prefs.getStringSet(name,new HashSet<>());
        data_string.forEach(s -> data.add(Integer.parseInt(s)));
    }
    private void loadInfo() {
        loadInfo("pins",historyPins);
        loadInfo("trails",historyTrails);
    }
    private void saveInfo() {
        saveInfo("pins",historyPins);
        saveInfo("trails",historyTrails);
    }

    public List<Integer> getHistoryPins() {
        return new ArrayList<>(historyPins);
    }

    public List<Integer> getHistoryTrails() {
        return new ArrayList<>(historyTrails);
    }

    public void addHistory(int trailId, List<Integer> pinsId) {
        historyTrails.add(trailId);
        historyPins.addAll(pinsId);
        saveInfo();
    }

    private static HistoryRepository instance = null;

    public static void createInstance(Context context) {
        if(instance == null)
            instance = new HistoryRepository(context);
    }

    public static HistoryRepository getInstance() {
        return  instance;
    }
}
