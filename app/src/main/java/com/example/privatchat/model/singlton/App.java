package com.example.privatchat.model.singlton;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.privatchat.model.bd.AppDatabas;

public class App extends Application {
    public static App instance;
    private AppDatabas database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabas.class, "database")
                .build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabas getDatabase() {
        return database;
    }

}