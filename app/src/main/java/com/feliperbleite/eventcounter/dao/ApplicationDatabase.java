package com.feliperbleite.eventcounter.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.feliperbleite.eventcounter.domain.Event;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Database(entities = {Event.class}, version = 1 )
@TypeConverters({Converters.class})
public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "eventsDb";
    private static ApplicationDatabase INSTANCE;

    public static ApplicationDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ApplicationDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract EventDao getEventDao();

}



class Converters {
    @TypeConverter
    public static LocalDateTime fromTimestamp(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDateTime date) {
        return date == null ? null : date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
