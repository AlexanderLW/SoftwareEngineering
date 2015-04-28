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

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.ids = type.getInt("id");
            this.file = type.getBoolean("file");
        }

        String[] names = mDbHelper.getSolutionNames();

        ListAdapter adapter = new TypeAdapter(this, names);

        ListView solutions = (ListView) findViewById(R.id.solutions);

        solutions.setAdapter(adapter);

        solutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data = mDbHelper.getSolutionData(position + 1);
                Intent nextScreen = new Intent(SolutionsActivity.this, QuestionsActivity.class);
                nextScreen.putExtra("id", ids);
                nextScreen.putExtra("file", true);
                nextScreen.putExtra("data", data);
                startActivity(nextScreen);
                setResult(2);
                finish();
            }
        });
    }
}
