package com.example.softwareengineering.softwareengineering;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import database.SolutionDBHelper;
import domain.SolutionSet;


public class TypesActivity extends ActionBarActivity {
    SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_types);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);

        //adds action bar support
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //adapter for the list view
        ListAdapter soluAdapter = new TypeAdapter(this, SolutionSet.SOLUTIONTYPES);
        ListView soluTypes = (ListView) findViewById(R.id.soluTypes);
        soluTypes.setAdapter(soluAdapter);

        //adds onclics for the items in the list view
        soluTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if clicked goes to the questions if there is nothing saved in the database or solution was selected
                if(mDbHelper.getCount() == 0 || position <3) {
                    Intent nextScreen = new Intent(TypesActivity.this, QuestionsActivity.class);
                    nextScreen.putExtra("id", position);
                    nextScreen.putExtra("file", false);
                    startActivity(nextScreen);
                }
                //if clicked goes to the load functionality
                else {
                    Intent nextScreen = new Intent(TypesActivity.this, LoadActivity.class);
                    nextScreen.putExtra("id", position);
                    startActivity(nextScreen);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_solution_types, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_cards:
                if(mDbHelper.getCount() != 0)
                    openCardsList();
                else Toast.makeText(this, "You have no saved solutions", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * The following methods redirect users to appropriate activities when
     * using onOptionItemSelected in menu
     */
    private void openSearch(){
        //expandable functionality for search
    }

    private void openCardsList(){
        Intent i = new Intent(this, ViewSavedActivity.class);
        startActivity(i);
    }

    private void setupSearchView(MenuItem searchItem){
        //expandable functionality for search
    }
}

