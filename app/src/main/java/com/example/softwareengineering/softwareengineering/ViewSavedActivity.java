package com.example.softwareengineering.softwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import database.SolutionDBHelper;
import domain.Card;


public class ViewSavedActivity extends ActionBarActivity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Bundle solution = getIntent().getExtras();
//        int id = solution.getInt("id") + 1;

        String[] data = mDbHelper.getSolutionData(1);
        String[] names = mDbHelper.getSolutionNames();

        String[][] infos = {names, data};
        CardArrayAdapter adapter = new CardArrayAdapter( getApplicationContext(), R.layout.list_item_card);


        ListView solutions = (ListView) findViewById(R.id.card_listView);


        solutions.addHeaderView(new View(this));
        solutions.addFooterView(new View(this));

        String print = "";
        print += data[0] + " mL of solution volume\n";
        print += "Using " + data[1] +  " as solvent\n";
        print += "Using " + data[2] + " as solute\n";
        print += data[3] + " g/mol of solute molecular weight\n";
        print += data[4] + " g/mole of solution molarity\n";
        print += data[5] + " g of solute mass";

        for (String name : names) {
            Card card = new Card(name + "\n\n" + print);
            adapter.add(card);
        }


        solutions.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.home:
                openSolutionTypes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSolutionTypes() {
        Intent intent = new Intent(this, TypesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
