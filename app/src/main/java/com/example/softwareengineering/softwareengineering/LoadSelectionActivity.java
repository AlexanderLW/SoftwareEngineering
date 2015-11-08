package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alexander on 11/3/2015.
 */
public class LoadSelectionActivity extends Activity {

    int id;
    static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_selection);

        //set font for the header and all the buttons
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        Button loadAnalyte = (Button) findViewById(R.id.loadAnalyte);
        loadAnalyte.setTypeface(myTypeface);
        Button loadStandard = (Button) findViewById(R.id.loadStandard);
        loadStandard.setTypeface(myTypeface);
        Button loadBoth = (Button) findViewById(R.id.loadBoth);
        loadBoth.setTypeface(myTypeface);
        Button newSolu = (Button) findViewById(R.id.newSolu);
        newSolu.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.id = type.getInt("id");
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
    public void onStandardClick(View view) {
        Intent nextScreen = new Intent(LoadSelectionActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivityForResult(nextScreen, 1);

    }
    //on yes button click passes info along to and loads next activity
    public void onAnalyteClick(View view) {
        Intent nextScreen = new Intent(LoadSelectionActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivityForResult(nextScreen, 1);
    }

    //on yes button click passes info along to and loads next activity
    public void onLoadBothClick(View view) {
        Intent nextScreen = new Intent(LoadSelectionActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivityForResult(nextScreen, 1);
    }

    //on yes button click passes info along to and loads next activity
    public void onCreateBothClick(View view) {
        Intent nextScreen = new Intent(LoadSelectionActivity.this, QuestionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", false);
        startActivityForResult(nextScreen, 1);
    }

}
