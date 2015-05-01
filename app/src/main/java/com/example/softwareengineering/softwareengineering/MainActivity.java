package com.example.softwareengineering.softwareengineering;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        Button mybutton = (Button)findViewById(R.id.begin);
        mybutton.setTypeface(myTypeface);
    }

    //button click to start
    public void onBegin(View view) {
        //sets intent for next screen
        Intent nextScreen = new Intent(this, TypesActivity.class);
        startActivity(nextScreen);
        finish();
    }
}
