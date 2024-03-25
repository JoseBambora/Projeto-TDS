package com.ruirua.sampleguideapp.model;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.trails.TrailDAO;

@Database(entities = {Trail.class, User.class, Pin.class}, version = 962)
public abstract class GuideDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "BraGuide";

    public abstract TrailDAO trailDAO();

    public abstract PinDAO pinDAO();

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

        public PopulateDbAsyn(GuideDatabase catDatabase) {
            traildao = catDatabase.trailDAO();
            pinDAO = catDatabase.pinDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            traildao.deleteAll();
            pinDAO.deleteAll();
            return null;
        }
    }
}