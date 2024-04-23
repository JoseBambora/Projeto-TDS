package com.ruirua.sampleguideapp.model.pins;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PinDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Pin> pins);

    @Query("SELECT DISTINCT * FROM pin")
    LiveData<List<Pin>> getPins();

    @Query("SELECT * FROM pin WHERE id = :id")
    LiveData<Pin> getPin(int id);

    @Query("DELETE FROM pin")
    void deleteAll();
}
