package com.example.softwareengineering.softwareengineering;

import android.app.Activity;

import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;


/**
 * Created by Samuel on 11/19/2015.
 */
public class BranchActivity extends Activity{
/*
this class allows selection of a solute type for creation of single solutions
 */
        int id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_branch);

            //set font
            Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
            TextView head = (TextView) findViewById(R.id.header);
            head.setTypeface(myTypeface);

            TextView text = (TextView) findViewById(R.id.text);
            text.setTypeface(myTypeface);


            TextView mysolidButton = (TextView) findViewById(R.id.solid);
            mysolidButton.setTypeface(myTypeface);
            TextView myneatButton = (TextView) findViewById(R.id.neat);
            myneatButton.setTypeface(myTypeface);
            TextView myconcentratedButton = (TextView) findViewById(R.id.concentrated);
            myconcentratedButton.setTypeface(myTypeface);


        }

        //on Neat button click, create new questionsActivity; pass -1 as id
        public void onNeat(View view) {
            Intent nextScreen = new Intent(BranchActivity.this, QuestionsActivity.class);
            nextScreen.putExtra("id", -1);
            nextScreen.putExtra("file", false);
            startActivity(nextScreen);
        }

        //on Solid button click create QuestionsActivity, pass 0 as id
        public void onSolid(View view) {
            Intent nextScreen = new Intent(BranchActivity.this, QuestionsActivity.class);
            nextScreen.putExtra("id", 0);
            nextScreen.putExtra("file", false);
            startActivity(nextScreen);
        }
    //on Solid button click create QuestionsActivity, pass -2 as id
        public void onConcentrated(View view) {
            Intent nextScreen = new Intent(BranchActivity.this, QuestionsActivity.class);
            nextScreen.putExtra("id", -2);
            nextScreen.putExtra("file", false);
            startActivity(nextScreen);
        }


    }


