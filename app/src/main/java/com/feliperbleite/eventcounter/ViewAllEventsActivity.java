package com.feliperbleite.eventcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feliperbleite.eventcounter.contentproviders.EventManager;
import com.feliperbleite.eventcounter.dao.ApplicationDatabase;

import java.util.stream.Collectors;

public class ViewAllEventsActivity extends AppCompatActivity {

    ApplicationDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);

        db = ApplicationDatabase.getInstance(getApplicationContext());

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                db.getEventDao().findAllList().stream().map(event -> event.toString()).collect(Collectors.toList()));

        ListView listView = (ListView) findViewById(R.id.all_events);
        listView.setAdapter(adapter);
    }
}