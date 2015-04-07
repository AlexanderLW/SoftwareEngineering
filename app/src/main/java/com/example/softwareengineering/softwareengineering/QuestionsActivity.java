package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class QuestionsActivity extends Activity {
    String[] solution = {"What is the volume of your flask?", "What is the solvent you are using?", "What solute are you using?", "What is the molecular weight of your solute?", "What is the molarity of the solution?", "What is the mass of the solute that you are adding?"};
    String[] dilution = {"What is the volume of the stock solution you are transferring?", "What is the molarity of the new dilution?"};
    String serialDilution = "Would you like to dilute again?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);
        TextView text = (TextView)findViewById(R.id.text);
        Bundle type = getIntent().getExtras();

        if(type != null)
        {

            String soluType = type.getString("id");
            text.setText(soluType);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solution_questions, menu);
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
}
