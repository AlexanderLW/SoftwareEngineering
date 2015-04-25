package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import database.SolutionDBHelper;


public class SaveActivity extends Activity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    String[] details, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/DK Cool Crayon.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);
        TextView text2 = (TextView) findViewById(R.id.summary);
        text2.setTypeface(myTypeface);
        EditText name = (EditText) findViewById(R.id.soluname);
        name.setTypeface(myTypeface);
        name.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        TextView mysaveButton = (TextView) findViewById(R.id.save);
        mysaveButton.setTypeface(myTypeface);
        TextView myfinishButton = (TextView) findViewById(R.id.finish);
        myfinishButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();
        if(type != null) {
            details = type.getStringArray("solutionDetails");
            data = type.getStringArray("solutionData");

            ListAdapter soluAdapter = new TypeAdapter(this, details);

            ListView soluDetails = (ListView) findViewById(R.id.details);

            soluDetails.setAdapter(soluAdapter);
        }
    }

    public void onSave(View view) {
        EditText name = (EditText) findViewById(R.id.soluname);
        if(name.getText().toString().trim().equals("")) {
            Toast.makeText(SaveActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        else {
            String soluname = name.getText().toString();
            mDbHelper.addSolution(soluname, data);
            setResult(2);
            finish();
        }
    }

    public void onfinish(View view) {
        setResult(2);
        finish();
    }
}
