package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import database.SolutionDBHelper;
import domain.SolutionSet;


public class GlossaryActivity extends Activity {


    /*
    This class displays a list of terms  the user can learn more about by clicking on them. when i term is clicked its id is sent to the DefinitionsActivity class and its definition is displayed.
     */



    //String array that stores all of the glossary terms. You can add
    //more terms and they should go right into the listview
    public static final String[] glossary = {
            "Solution (AKA Mixture)",
            "Substance",
            "Solute",
            "Solvent",
            "Concentration",
            "Analyte",
            "External Standards Calibration (Method of Standard Preparation)",
            "internal Standards Calibration (Method of Standard Preparation)",
            "Standard Addition Method of Calibration (Method of Standard Preparation)",
            "Internal Standard (Chemical Species)",
            "Homogeneous",
            "Heterogeneous"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);



        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.glossaryText);
        text.setTypeface(myTypeface);



        //adapter for the list view to load all of the items from the array in
        ListAdapter glossaryAdapter = new TypeAdapter(this, glossary);
        ListView glossaryTerms = (ListView) findViewById(R.id.glossary_list);
        glossaryTerms.setAdapter(glossaryAdapter);




        //adds onclicks for the items in the list view
        glossaryTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //if clicked goes to the questions if there is nothing saved in the database or solution was selected
                Intent nextScreen = new Intent(GlossaryActivity.this, DefinitionsActivity.class);
                nextScreen.putExtra("id", position);
                startActivity(nextScreen);

            }
        });

    }

}

