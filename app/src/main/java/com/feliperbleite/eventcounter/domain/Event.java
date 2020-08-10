package com.feliperbleite.eventcounter.domain;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "events")
public class Event {

    private static final String EVENT_ID = "id";
    private static final String EVENT_TIMESTAMP = "timestamp";
    private static final String EVENT_TYPE = "type";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "timestamp")
    private LocalDateTime timestamp;

    @ColumnInfo(name = "type")
    private String type = "generic";

    public Event() {
        this.timestamp = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static Event fromContentValues(ContentValues contentValues) {
        Event event = new Event();

        if (contentValues.containsKey(EVENT_ID)) {
            event.setId(contentValues.getAsInteger(EVENT_ID));
        }
        if (contentValues.containsKey(EVENT_TIMESTAMP)) {
            event.setTimestamp(contentValues.getAsLong(EVENT_TIMESTAMP));
        }
        if (contentValues.containsKey(EVENT_TYPE)) {
            event.setType(contentValues.getAsString(EVENT_TYPE));
        }
        return event;
    }

    @NonNull
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s: %s", this.timestamp.format(formatter), this.type);
    }
}
