package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ChooseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/DK Cool Crayon.ttf");
        Button myButton = (Button)findViewById(R.id.view);
        myButton.setTypeface(myTypeface);
        Button myMakeButton = (Button)findViewById(R.id.make);
        myMakeButton.setTypeface(myTypeface);
    }

    public void onMake(View view) {
        Intent nextScreen = new Intent(this, TypesActivity.class);
        startActivity(nextScreen);
    }

    public void onView(View view) {
        Intent nextScreen = new Intent(this, ViewSolutionsActivity.class);
        startActivity(nextScreen);
    }
}
