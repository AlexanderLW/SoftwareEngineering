package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoadActivity extends Activity {
    int id;
    static Context appContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        appContext = getApplicationContext();

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        TextView myYesButton = (TextView) findViewById(R.id.yes);
        myYesButton.setTypeface(myTypeface);
        TextView myNoButton = (TextView) findViewById(R.id.no);
        myNoButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.id = type.getInt("id");
            changeHeader();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==2){
            finish();
        }
    }

    public void onYes(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivityForResult(nextScreen, 1);
    }

    public void onNo(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, QuestionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", false);
        startActivity(nextScreen);
        finish();
    }

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
