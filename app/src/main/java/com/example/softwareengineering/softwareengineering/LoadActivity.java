package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoadActivity extends Activity {

    /*
    This screen is displayed after selecting a solution type and handles setting up for creating that solution type.
     */

    int id;
    static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        //appContext = getApplicationContext();
        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        TextView myYesButton = (TextView) findViewById(R.id.yes);
        myYesButton.setTypeface(myTypeface);
        TextView myNoButton = (TextView) findViewById(R.id.no);
        myNoButton.setTypeface(myTypeface);

        //gets what was passed from previous screen, assigns it, and changes header according to id
        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.id = type.getInt("id");
            changeHeader();
        }
    }

    //closes activity under certain circumstances from next activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==2){
            finish();
        }
    }

    //on yes button click passes info along to and loads next activity
    public void onYes(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivityForResult(nextScreen, 1);
    }

    //on no button click passes info along to and loads next activity
    public void onNo(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, QuestionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", false);
        startActivity(nextScreen);
        finish();
    }

    //method to change header
    public void changeHeader() {
        TextView head = (TextView) findViewById(R.id.text);
        switch (id) {
            case 1:
                head.setText("Creating a dilution requires an existing stock solution, would you like to make a new one or load one that is saved?");
                break;
            case 2:
                head.setText("Creating serial dilutions requires an existing stock solution, would you like to make a new one or load one that is saved?");
                break;
            case 3:
                head.setText("Creating a set of external standards requires an existing stock analyte solution, would you like to make a new one or load one that is saved?");
                break;
            case 4:
                head.setText("Creating a set of internal standards requires an existing stock analyte solution, would you like to make a new one or load one that is saved?");
                break;
            case 5:
                head.setText("Creating a set of standards using the standard addition method requires an existing stock analyte solution, would you like to make a new one or load one that is saved?");
                break;
        }
    }
}
