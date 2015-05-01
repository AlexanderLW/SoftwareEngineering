package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import database.SolutionDBHelper;


public class SolutionsActivity extends Activity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    boolean file;
    int ids;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);

        //gets what was passed from previous activity and assigns it
        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.ids = type.getInt("id");
            this.file = type.getBoolean("file");
        }

        //adapter for list view to list solutions that are saved in the database
        String[] names = mDbHelper.getSolutionNames();
        ListAdapter adapter = new TypeAdapter(this, names);
        ListView solutions = (ListView) findViewById(R.id.solutions);
        solutions.setAdapter(adapter);

        //sets on click for each item in list view so that it can pass the correct information to the next screen and start
        solutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data = mDbHelper.getSolutionData(position + 1);
                Intent nextScreen = new Intent(SolutionsActivity.this, DetailsActivity.class);
                nextScreen.putExtra("id", ids);
                nextScreen.putExtra("file", true);
                nextScreen.putExtra("data", data);
                startActivityForResult(nextScreen, 0);
            }
        });
    }

    //closes activity under certain circumstances from next activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==2){
            setResult(2);
            finish();
        }
    }
}
