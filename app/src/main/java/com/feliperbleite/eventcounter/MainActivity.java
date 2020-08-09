package com.feliperbleite.eventcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feliperbleite.eventcounter.contentproviders.EventManager;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.feliperbleite.eventcounter.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dailyCounterView = (TextView) findViewById(R.id.dailyCountViewer);
        dailyCounterView.setText(EventManager.getEventsCount().toString());
    }

    public void viewAllEvents(View view) {
        Intent intent = new Intent(this, ViewAllEventsActivity.class);
        startActivity(intent);
    }

    public void countEvent(View view) {
        TextView dailyCounterView = (TextView) findViewById(R.id.dailyCountViewer);
        Integer eventCounter = EventManager.addEvent();
        dailyCounterView.setText(eventCounter.toString());
    }
}