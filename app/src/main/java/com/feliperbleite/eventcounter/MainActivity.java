package com.feliperbleite.eventcounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feliperbleite.eventcounter.contentproviders.EventContentProvider;
import com.feliperbleite.eventcounter.contentproviders.EventManager;
import com.feliperbleite.eventcounter.dao.ApplicationDatabase;
import com.feliperbleite.eventcounter.domain.Event;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.feliperbleite.eventcounter.MESSAGE";
    ApplicationDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dailyCounterView = (TextView) findViewById(R.id.dailyCountViewer);
        db = ApplicationDatabase.getInstance(getApplicationContext());
        dailyCounterView.setText(String.valueOf(db.getEventDao().findAll().getCount()));
    }

    public void viewAllEvents(View view) {
        Intent intent = new Intent(this, ViewAllEventsActivity.class);
        startActivity(intent);
    }

    public void countEvent(View view) {
        TextView dailyCounterView = (TextView) findViewById(R.id.dailyCountViewer);
        db.getEventDao().insert(new Event());
        dailyCounterView.setText(String.valueOf(db.getEventDao().findAll().getCount()));
    }
}