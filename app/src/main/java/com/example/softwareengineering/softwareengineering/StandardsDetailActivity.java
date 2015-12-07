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
import android.widget.Toast;

import domain.ExternalStandards;
import domain.InternalStandards;
import domain.SolutionSet;
import domain.StandardAddition;

/**
 * Created by Alexander on 11/17/2015.
 */
public class StandardsDetailActivity extends Activity {
/*
this displays details of a specific standard from a set
 */
    String[] details, data;
    int numberOfDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standards_detail);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);

        //Gathers all the information that was bundled together with the Intent
        Bundle type = getIntent().getExtras();
        if(type != null) {
            details = type.getStringArray("solutionDetails");
            data = type.getStringArray("solutionData");

            //data[2] is the numberOfStandards variable in InternalStandards.java
            //Declaring this to get the size of the array to incorporate all the made standards.
            int numberOfStandards = Integer.parseInt(data[2]);
            String[] newValues = new String[10];

            //External Standard data setting
            if(Integer.parseInt(details[0]) == 1) {
                //This declaration is including space for the values from InternalStandards as well as the number of standards.
                //The -2 at the end is to not have too many standards since that is in the values string
                newValues = new String[ExternalStandards.values.length + numberOfStandards - 2];


                //Sets the data inside of the newValues array. Count is to change the number suffix for standards
                int count = 1;
                for (int i = 0; i < newValues.length; i++) {
                    //This sets newValues to be equal to to values except for the last one which is set to standards
                    if (i <= ExternalStandards.values.length - 2) {
                        newValues[i] = ExternalStandards.values[i];
                    }
                    //This deals with the number of standards the user said they made
                    //This sets standards to be in the array with a number at the end to signify difference
                    else {
                        newValues[i] = "Standard " + count;
                        count++;
                    }
                }
            }
            //Internal Standards data setting
            else if(Integer.parseInt(details[0]) == 2) {
                //This declaration is including space for the values from InternalStandards as well as the number of standards.
                //The -2 at the end is to not have too many standards since that is in the values string
                newValues = new String[InternalStandards.values.length + numberOfStandards - 2];


                //Sets the data inside of the newValues array. Count is to change the number suffix for standards
                int count = 1;
                for (int i = 0; i < newValues.length; i++) {
                    //This sets newValues to be equal to to values except for the last one which is set to standards
                    if (i <= InternalStandards.values.length - 2) {
                        newValues[i] = InternalStandards.values[i];
                    }
                    //This deals with the number of standards the user said they made
                    //This sets standards to be in the array with a number at the end to signify difference
                    else {
                        newValues[i] = "Standard " + count;
                        count++;
                    }
                }
            }
            //Standard Addition data setting
            else if(Integer.parseInt(details[0]) == 3) {
                //This declaration is including space for the values from InternalStandards as well as the number of standards.
                //The -2 at the end is to not have too many standards since that is in the values string
                newValues = new String[StandardAddition.values.length + numberOfStandards - 2];


                //Sets the data inside of the newValues array. Count is to change the number suffix for standards
                int count = 1;
                for (int i = 0; i < newValues.length; i++) {
                    //This sets newValues to be equal to to values except for the last one which is set to standards
                    if (i <= StandardAddition.values.length - 2) {
                        newValues[i] = StandardAddition.values[i];
                    }
                    //This deals with the number of standards the user said they made
                    //This sets standards to be in the array with a number at the end to signify difference
                    else {
                        newValues[i] = "Standard " + count;
                        count++;
                    }
                }
            }


            numberOfDetails = newValues.length;

            //Sets the adapter for the listview
            ListAdapter standardsAdapter = new TypeAdapter(this, newValues);
            ListView standardsDetails = (ListView) findViewById(R.id.standardsDetail);
            standardsDetails.setAdapter(standardsAdapter);

            //onClicks for the listView
            standardsDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //if clicked goes to the questions if there is nothing saved in the database or solution was selected
                    Intent nextScreen = new Intent(StandardsDetailActivity.this, StandardsInfoActivity.class);
                    nextScreen.putExtra("id", position);
                    nextScreen.putExtra("numberOfDetails", numberOfDetails);
                    nextScreen.putExtra("data", data);
                    nextScreen.putExtra("details", details);
                    startActivity(nextScreen);

                }
            });

        }

    }


}
