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

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/DK Cool Crayon.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        TextView myYesButton = (TextView) findViewById(R.id.yes);
        myYesButton.setTypeface(myTypeface);
        TextView myNoButton = (TextView) findViewById(R.id.no);
        myNoButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.id = type.getInt("id");
        }
    }

    public void onYes(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, SolutionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        startActivity(nextScreen);
    }

    public void onNo(View view) {
        Intent nextScreen = new Intent(LoadActivity.this, QuestionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", false);
        startActivity(nextScreen);
    }
}
