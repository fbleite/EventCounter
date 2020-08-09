package com.feliperbleite.eventcounter.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.feliperbleite.eventcounter.domain.Event;

@Database(entities = {Event.class}, version = 1 )
public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "eventsDb";
    private static ApplicationDatabase INSTANCE;

    public static ApplicationDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ApplicationDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract EventDao getEventDao();

}
