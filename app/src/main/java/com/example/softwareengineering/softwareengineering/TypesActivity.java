package com.example.softwareengineering.softwareengineering;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import domain.SolutionSet;


public class TypesActivity extends ActionBarActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_types);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ListAdapter soluAdapter = new TypeAdapter(this, SolutionSet.SOLUTIONTYPES);

        ListView soluTypes = (ListView) findViewById(R.id.soluTypes);


        soluTypes.setAdapter(soluAdapter);

        soluTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent nextScreen = new Intent(TypesActivity.this, QuestionsActivity.class);
                    nextScreen.putExtra("id", position);
                    nextScreen.putExtra("file", false);
                    startActivity(nextScreen);
                }
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
                openCardsList();
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
        throw new UnsupportedOperationException();
    }

    private void openCardsList(){
        Intent i = new Intent(this, ViewSavedActivity.class);
        startActivity(i);
    }

    private void setupSearchView(MenuItem searchItem){


    }
}

