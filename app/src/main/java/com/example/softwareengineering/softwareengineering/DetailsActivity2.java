package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DetailsActivity2 extends Activity {

    /*
    This class displays information about a saved solution before it is selected to be used in the creation of another solution type.
     */


    boolean file;
    int id;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);
        TextView myYesButton = (TextView) findViewById(R.id.yes);
        myYesButton.setTypeface(myTypeface);
        TextView myNoButton = (TextView) findViewById(R.id.no);
        myNoButton.setTypeface(myTypeface);

        //gets what was passed from previous activity and assigns it
        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.id = type.getInt("id");
            this.file = type.getBoolean("file");
            this.data = type.getStringArray("data");
        }

        //adapter for list view to list the details of the solution that was clicked
        String[] details = {
                "The Volume of the solution is: " + Double.parseDouble(data[0]) + "mL",
                "The solvent in the solution is: " + data[1],
                "The solute in the solution is:: " + data[2],
                "The molecular weight of " + data[2] + " is: " + data[3] + "g/mol",
                "The molarity of the solution is: " + data[4] + "M",
                "The mass of " + data[2] + " in the solution is: " + data[6] +"g"//fixed this value from[5] to [6]
        };
        ListAdapter adapter = new TypeAdapter(this, details);
        ListView detailsView = (ListView) findViewById(R.id.details);
        detailsView.setAdapter(adapter);
    }

    //on yes button click passes info along to and loads next activity it will send the response to the previous activities to close them
    public void onYes(View view) {
        Intent nextScreen = new Intent(DetailsActivity2.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        nextScreen.putExtra("data2", data);
        startActivity(nextScreen);
        setResult(2);
        finish();
    }

    //on no click closes activity and goes back to the previous activity
    public void onNo(View view) {
        finish();
    }
}
