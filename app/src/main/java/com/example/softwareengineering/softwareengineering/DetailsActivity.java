package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import database.SolutionDBHelper;


public class DetailsActivity extends ActionBarActivity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Bundle solution = getIntent().getExtras();

        int id = solution.getInt("id") + 1;

        String[] data = mDbHelper.getSolutionData(id);

        ListAdapter adapter = new TypeAdapter(this, data);

        ListView solutions = (ListView) findViewById(R.id.details);

        solutions.setAdapter(adapter);
    }
}
