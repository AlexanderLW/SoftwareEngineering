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
    /*
    This screen allows the naming of a solution to be saved for later use.
     */
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    String[] details, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
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

        //gets what was passed from previous activity and assigns it
        Bundle type = getIntent().getExtras();
        if(type != null) {
            details = type.getStringArray("solutionDetails");
            data = type.getStringArray("solutionData");

            //sets list with details of solution that could be saved
            ListAdapter soluAdapter = new TypeAdapter(this, details);
            ListView soluDetails = (ListView) findViewById(R.id.details);
            soluDetails.setAdapter(soluAdapter);
        }
    }

    //checks to see if edit text is not empty or default and saves the solution and goes back to last screen
    public void onSave(View view) {
        EditText name = (EditText) findViewById(R.id.soluname);
        if(name.getText().toString().trim().equals("") || name.getText().toString().trim().equals("Enter Solution Name Here")) {
            Toast.makeText(SaveActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        else {
            String soluname = name.getText().toString();
            mDbHelper.addSolution(soluname, data);
            setResult(2);
            finish();
        }
    }

    //doesn't save solution and goes back to last screen
    public void onfinish(View view) {
        setResult(2);
        finish();
    }
}
