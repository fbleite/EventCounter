package com.feliperbleite.eventcounter.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity()
public class Event {
    private LocalDateTime timestamp;
    private String type = "generic";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Event() {
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return this.type;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s: %s", this.timestamp.format(this.formatter), this.type);
    }
}
