package com.example.softwareengineering.softwareengineering;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import database.SolutionDBHelper;
import domain.Card;


public class ViewSavedActivity extends ActionBarActivity {
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);
    private CardArrayAdapter adapter;
    private AlertDialog.Builder builder;
    private AlertDialog removeAllDialog, removeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter = new CardArrayAdapter( getApplicationContext(), R.layout.list_item_card);

        ListView solutions = (ListView) findViewById(R.id.card_listView);


        solutions.addHeaderView(new View(this));
        solutions.addFooterView(new View(this));

        String[] names = mDbHelper.getSolutionNames();

        for (int i = 0; i < names.length;i++) {
            String[] data = mDbHelper.getSolutionData(i+1);
            Card card = new Card(names[i] + "\n\n" + printSolution(data));
            adapter.add(card);
        }

        solutions.setAdapter(adapter);

        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.remove_all_dialog_message)

                // Set the action buttons
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        removeAllDialog = builder.create();



        solutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                cardDialog(position).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_solutions, menu);


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
            case R.id.home:
                openSolutionTypes();
                return true;
            case R.id.action_remove:
                removeAllCards();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void removeAllCards() {
        removeAllDialog.show();
    }

    private void openSolutionTypes() {
        Intent intent = new Intent(this, TypesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private AlertDialog cardDialog(final int position){
        final int pos = position - 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Would you like to delete this solution?")


                .setMessage(adapter.getItem(pos).getData())


                // Set the action buttons
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        adapter.remove(adapter.getItem(pos));
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    private String printSolution(String[] data){
        String print = "";
        print += data[0] + " mL of solution volume\n";
        print += "Using " + data[1] +  " as solvent\n";
        print += "Using " + data[2] + " as solute\n";
        print += data[3] + " g/mol of solute molecular weight\n";
        print += data[4] + " g/mole of solution molarity\n";
        print += data[5] + " g of solute mass";
        return print;
    }

}
