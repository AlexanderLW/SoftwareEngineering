package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import database.SolutionDBHelper;


public class ViewSolutionsActivity extends Activity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solutions);

        String[] names = mDbHelper.getSolutionNames();

        ListAdapter adapter = new typeAdapter(this, names);

        ListView solutions = (ListView) findViewById(R.id.solutions);

        solutions.setAdapter(adapter);
        solutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextScreen = new Intent(ViewSolutionsActivity.this, DetailsActivity.class);
                nextScreen.putExtra("id", position);
                startActivity(nextScreen);
            }
        });
    }
}
