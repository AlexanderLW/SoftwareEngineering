package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import database.SolutionDBHelper;


public class DetailsActivity extends Activity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle solution = getIntent().getExtras();

        int id = solution.getInt("id") + 1;

        String[] data = mDbHelper.getSolutionData(id);

        ListAdapter adapter = new typeAdapter(this, data);

        ListView solutions = (ListView) findViewById(R.id.details);

        solutions.setAdapter(adapter);
    }
}
