package com.example.softwareengineering.softwareengineering;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import database.MyDBHandler;
import database.SolutionDBHelper;


public class SaveActivity extends ActionBarActivity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    String[] details, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/DK Cool Crayon.ttf");
        TextView text = (TextView) findViewById(R.id.show);
        text.setTypeface(myTypeface);
        EditText name = (EditText) findViewById(R.id.soluname);
        name.setTypeface(myTypeface);
        TextView mysaveButton = (TextView) findViewById(R.id.save);
        mysaveButton.setTypeface(myTypeface);
        TextView myfinishButton = (TextView) findViewById(R.id.finish);
        myfinishButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();
        if(type != null) {
            details = type.getStringArray("solutionDetails");
            data = type.getStringArray("solutionData");

            ListAdapter soluAdapter = new typeAdapter(this, details);

            ListView soluDetails = (ListView) findViewById(R.id.details);

            soluDetails.setAdapter(soluAdapter);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSave(View view) {
        EditText name = (EditText) findViewById(R.id.soluname);
        if(name.getText().toString().trim().equals("")) {
            String soluname = name.getText().toString();

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_NAME, soluname);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME, data[0]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT, data[1]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLUTE, data[2]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT, data[3]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY, data[4]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES, data[5]);
            values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MASS, data[6]);

            // Insert the new row, returning the primary key value of the new row
            db.insert(MyDBHandler.SolutionEntry.TABLE_NAME, null, values);
            db.close();

            setResult(2);
            finish();
        }
    }

    public void onfinish(View view) {
        setResult(2);
        finish();
    }
}
