package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alexander on 11/24/2015.
 */
public class StandardsInfoActivity extends Activity {
/*
this class shows the set of solutions created for external, internal, and standard addition
 */
    String[] details, data;
    int id, numberOfDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standards_info);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setTypeface(myTypeface);

        //Gathers all the information that was bundled together with the Intent
        Bundle type = getIntent().getExtras();
        if(type != null) {
            details = type.getStringArray("details");
            data = type.getStringArray("data");
            this.id = type.getInt("id");
            this.numberOfDetails = type.getInt("numberOfDetails");

            //If Statement to deal with the differences between internal, external and standard addition
            if(Integer.parseInt(details[0]) == 2) {
                internalStandardsInfo(data, id);
            }
            else if(Integer.parseInt(details[0]) == 1){
                externalStandardsInfo();
            }
            else if(Integer.parseInt(details[0]) == 3){
                standardAdditionInfo();
            }
        }

    }

    //This method deals with changing the data for the list view to fit the needs of an internal standard
    public void internalStandardsInfo(String[] data, int id) {

        //This is where the string is split into a string array
        String[] analyteTransferredString = data[3].split(",");
        double[] analyteTransferredDouble = new double[10];

        //casts all values from string array to double and declares them as the values for the double array
        for(int j = 0; j < analyteTransferredString.length; j++) {
                analyteTransferredDouble[j] = Double.parseDouble(analyteTransferredString[j]);
        }

        //Send info to method to actually change the list view
        setInternalListView(data, id, analyteTransferredDouble);

    }


    public void externalStandardsInfo() {

        //This is where the string is split into a string array
        String[] analyteTransferredString = data[3].split(",");
        double[] analyteTransferredDouble = new double[10];

        //casts all values from string array to double and declares them as the values for the double array
        for(int j = 0; j < analyteTransferredString.length; j++) {
            analyteTransferredDouble[j] = Double.parseDouble(analyteTransferredString[j]);
        }

        //Send info to method to actually change the list view
        setExternalListView(data, id, analyteTransferredDouble);

    }

    public void standardAdditionInfo() {

        //This is where the string is split into a string array
        String[] analyteTransferredString = data[4].split(",");
        double[] analyteTransferredDouble = new double[10];

        //casts all values from string array to double and declares them as the values for the double array
        for(int j = 0; j < analyteTransferredString.length; j++) {
            analyteTransferredDouble[j] = Double.parseDouble(analyteTransferredString[j]);
        }

        //Send info to method to actually change the list view
        setStandardListView(data, id, analyteTransferredDouble);

    }

    //This is the method that actually changes the list view
    public void setInternalListView(String[] data, int id, double[] analyteTransferredDouble) {

        //Predeclare the size of the values, there are no more than 5 values that should be dealt with
        String[] values = new String[5];

        //This deals with the special case that is the unknown standard.
        //The unknown standard will always have the id of 0
        if(id == 0) {

            //A string array to grab all the values that go on that page
            values = new String[] {String.valueOf("Name: " + data[0]), String.valueOf("Total Volume: " + data[1] + " mL"), String.valueOf("Unknown Solution Transferred: " + data[4] + " mL")};

            //Sets the adapter for the listview
            ListAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }
        //This deals with all of the normal standards. They all have similar values, so they can easily be replicated.
        else {
            values = new String[]{String.valueOf("Name: " + "\n\tStandard " + id), String.valueOf("Total Volume: " + data[1] + " mL"), String.valueOf("Analyte Transferred: " + analyteTransferredDouble[id - 1] + " mL"),
                    String.valueOf("Internal Standard Transferred: " + data[5] + " mL")};

            //Sets the adapter for the listview
            TypeAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }

    }

    public void setExternalListView(String[] data, int id, double[] analyteTransferredDouble) {

        //Predeclare the size of the values, there are no more than 5 values that should be dealt with
        String[] values = new String[5];

        //This deals with the special case that is the unknown standard.
        //The unknown standard will always have the id of 0
        if(id == 0) {

            //A string array to grab all the values that go on that page
            values = new String[] {String.valueOf("Name: " + data[0]), String.valueOf("Total Volume: " + data[1] + " mL")};

            //Sets the adapter for the listview
            ListAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }
        //This deals with all of the normal standards. They all have similar values, so they can easily be replicated.
        else {
            values = new String[]{String.valueOf("Name: \n\tStandard" + id), String.valueOf("Total Volume: " + data[1] + " mL"), String.valueOf("Analyte Transferred: " + analyteTransferredDouble[id - 1] + " mL")};

            //Sets the adapter for the listview
            TypeAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }

    }

    public void setStandardListView(String[] data, int id, double[] analyteTransferredDouble) {

        //Predeclare the size of the values, there are no more than 5 values that should be dealt with
        String[] values = new String[5];

        //This deals with the special case that is the unknown standard.
        //The unknown standard will always have the id of 0
        if(id == 0) {

            //A string array to grab all the values that go on that page
            values = new String[] {String.valueOf("Name: " + data[0]), String.valueOf("Total Volume: " + data[1] + " mL"), String.valueOf("Unknown Solution Transferred: " + data[3] + " mL")};

            //Sets the adapter for the listview
            ListAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }
        //This deals with all of the normal standards. They all have similar values, so they can easily be replicated.
        else {
            values = new String[]{String.valueOf("Name: \n\tStandard " + id), String.valueOf("Total Volume: " + data[1] + " mL"), String.valueOf("Analyte Transferred: " + analyteTransferredDouble[id - 1] + " mL"),
                    String.valueOf("Unknown Transferred: " + data[3] + " mL")};

            //Sets the adapter for the listview
            TypeAdapter standardsAdapter = new TypeAdapter(this, values);
            ListView standardsInfo = (ListView) findViewById(R.id.standardsInfo);
            standardsInfo.setAdapter(standardsAdapter);

        }

    }

}
