package com.ruirua.sampleguideapp.model.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT DISTINCT * FROM user")
    LiveData<List<User>> getUsers();

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("DELETE FROM user WHERE user.username = :username")
    void deleteUser(String username);
}
