package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import database.SolutionDBHelper;


public class SolutionsActivity extends Activity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this, null, null, 1);
    boolean file;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.id = type.getInt("id");
            this.file = type.getBoolean("file");
        }

        String[] names = mDbHelper.getSolutionNames();

        ListAdapter soluAdapter = new typeAdapter(this, names);

        ListView soluTypes = (ListView) findViewById(R.id.solutions);

        soluTypes.setAdapter(soluAdapter);

        soluTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent nextScreen = new Intent(SolutionsActivity.this, QuestionsActivity.class);
                nextScreen.putExtra("id", id);
                nextScreen.putExtra("file", false);
                startActivity(nextScreen);
            }
        });
    }
}
