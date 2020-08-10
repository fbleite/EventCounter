package com.feliperbleite.eventcounter.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.feliperbleite.eventcounter.domain.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    long insert (Event event);

    @Query("SELECT * FROM events")
    Cursor findAll();

    @Query("SELECT * FROM events ORDER BY id DESC")
    List<Event> findAllList();

    @Query("DELETE FROM events where id = :id")
    int delete(long id);

    @Update
    int update(Event event);

}
