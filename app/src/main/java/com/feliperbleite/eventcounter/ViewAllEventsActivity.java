package com.feliperbleite.eventcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feliperbleite.eventcounter.contentproviders.EventManager;

import java.util.stream.Collectors;

public class ViewAllEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                EventManager.getAllEvents().stream().map(event -> event.toString()).collect(Collectors.toList()));

        ListView listView = (ListView) findViewById(R.id.all_events);
        listView.setAdapter(adapter);
    }
}