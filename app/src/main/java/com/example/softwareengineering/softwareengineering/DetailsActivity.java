package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DetailsActivity extends Activity {
    boolean file;
    int id;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle type = getIntent().getExtras();

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);
        TextView myYesButton = (TextView) findViewById(R.id.yes);
        myYesButton.setTypeface(myTypeface);
        TextView myNoButton = (TextView) findViewById(R.id.no);
        myNoButton.setTypeface(myTypeface);

        if(type != null) {
            this.id = type.getInt("id");
            this.file = type.getBoolean("file");
            this.data = type.getStringArray("data");
        }

        String[] details = {
                "The Volume of the solution is: " + Double.parseDouble(data[0])/1000 + "mL",
                "The solvent in the solution is: " + data[1],
                "The solute in the solution is:: " + data[2],
                "The molecular weight of the " + data[2] + " is: " + data[3] + "g/mol",
                "The molarity of the solution is: " + data[4] + "M",
                "The mass of " + data[2] + " in the solution is: " + data[6] +"g"
        };

        ListAdapter adapter = new TypeAdapter(this, details);

        ListView detailsview = (ListView) findViewById(R.id.details);

        detailsview.setAdapter(adapter);
    }

    public void onYes(View view) {
        Intent nextScreen = new Intent(DetailsActivity.this, QuestionsActivity.class);
        nextScreen.putExtra("id", id);
        nextScreen.putExtra("file", true);
        nextScreen.putExtra("data", data);
        startActivity(nextScreen);
        setResult(2);
        finish();
    }

    public void onNo(View view) {
        finish();
    }
}
