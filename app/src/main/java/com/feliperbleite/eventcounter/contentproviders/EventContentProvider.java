package com.feliperbleite.eventcounter.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.feliperbleite.eventcounter.dao.ApplicationDatabase;
import com.feliperbleite.eventcounter.dao.EventDao;
import com.feliperbleite.eventcounter.domain.Event;

public class EventContentProvider extends ContentProvider {

    private EventDao eventDao;

    public static final String EVENT_TABLE_NAME = "events";

    public static final String AUTHORITY = "com.feliperbleite.contentproviders";

    public static final int ID_EVENT_DATA = 1;

    public static final int ID_EVENT_DATA_ITEM = 2;

    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, EVENT_TABLE_NAME, ID_EVENT_DATA);
        uriMatcher.addURI(AUTHORITY, EVENT_TABLE_NAME + "/*", ID_EVENT_DATA_ITEM);
    }

    @Override
    public boolean onCreate() {
        eventDao = ApplicationDatabase.getInstance(getContext()).getEventDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case ID_EVENT_DATA : {
                cursor = eventDao.findAll();
                if(getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                    return cursor;
                }
            }
            default: throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case ID_EVENT_DATA:
                if (getContext() != null) {
                    long id = eventDao.insert(Event.
                            fromContentValues(contentValues));
                    if (id != 0) {
                        getContext().getContentResolver()
                                .notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    }
                }
            case ID_EVENT_DATA_ITEM:
                throw new IllegalArgumentException
                        ("Invalid URI: Insert failed" + uri);
            default:
                throw new IllegalArgumentException
                        ("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case ID_EVENT_DATA:
                throw new IllegalArgumentException("Invalid uri: cannot delete");
            case ID_EVENT_DATA_ITEM:
                if (getContext() != null) {
                    int count = eventDao.delete(ContentUris.parseId(uri));
                    getContext().getContentResolver().notifyChange(uri, null);
                    return count;
                }
            default:
                throw new IllegalArgumentException
                        ("Unknown URI:" + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues contentValues,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case ID_EVENT_DATA:
                if (getContext() != null) {
                    int count = eventDao.update(Event.fromContentValues(contentValues));
                    if (count != 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        return count;
                    }
                }
            case ID_EVENT_DATA_ITEM:
                throw new IllegalArgumentException("Invalid URI:  cannot update");
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
