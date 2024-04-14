package com.ruirua.sampleguideapp.model;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ruirua.sampleguideapp.model.pins.Media;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.model.pins.RelPin;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.trails.TrailDAO;
import com.ruirua.sampleguideapp.model.user.UserLogged;
import com.ruirua.sampleguideapp.model.user.UserDAO;

@Database(entities = {Trail.class, UserLogged.class, Pin.class, RelPin.class, Media.class}, version = 968)
public abstract class GuideDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BraGuide";

    public abstract TrailDAO trailDAO();

    public abstract PinDAO pinDAO();

    public abstract UserDAO userDAO();

    public static volatile GuideDatabase INSTANCE = null;

    public static GuideDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GuideDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, GuideDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{

        private final TrailDAO traildao;
        private final PinDAO pinDAO;
        private final UserDAO userDAO;

        public PopulateDbAsyn(GuideDatabase catDatabase) {
            traildao = catDatabase.trailDAO();
            pinDAO = catDatabase.pinDAO();
            userDAO = catDatabase.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            traildao.deleteAll();
            pinDAO.deleteAll();
            userDAO.deleteAll();
            return null;
        }
    }
}