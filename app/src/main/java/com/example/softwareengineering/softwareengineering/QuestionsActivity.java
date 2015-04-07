package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class QuestionsActivity extends Activity {
    int count = 0;
    int j = 0;
    List<String> questions;
    String[] solution = {"What is the volume of your flask?", "What is the solvent you are using?", "What solute are you using?", "What is the molecular weight of your solute?", "What is the molarity of the solution?", "What is the mass of the solute that you are adding?"};
    String[] dilution = {"What is the volume of the stock solution you are transferring?", "What is the molarity of the new dilution?"};
    String[] serialDilution = {"Would you like to dilute again?"};
    String[] externalStandards = {"Would you create another standard?"};
    String[] internalStandards = {"What is the volume of the internal standard that you are transferring?", "What is the molarity of the internal standard in the new standard?"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);
        Bundle type = getIntent().getExtras();

        if(type != null) {
            String soluType = type.getString("id");

            makeQuestions(soluType);

            TextView text = (TextView)findViewById(R.id.text);
            text.setText(questions.get(count));
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

    public void onPrevious(View view) {
        if(count == 0) {
            Toast.makeText(QuestionsActivity.this, "This is the first question", Toast.LENGTH_SHORT).show();
            questions = null;
            finish();
        } else {
            count--;
            TextView text = (TextView)findViewById(R.id.text);
            text.setText(questions.get(count));
        }
    }

    public void onContinue(View view) {
        if(count == questions.size()-1) {
            Toast.makeText(QuestionsActivity.this, "This is the last question", Toast.LENGTH_SHORT).show();
            questions = null;
            finish();
        } else {
            count++;
            TextView text = (TextView)findViewById(R.id.text);
            text.setText(questions.get(count));
        }
    }

    public void makeQuestions(String id) {
        if(id.equals("Solution")) questions = Arrays.asList(solution);
        else if (id.equals("Dilution")) questions = Arrays.asList(dilution);
        else if (id.equals("Serial Dilution")) questions = Arrays.asList(serialDilution);
        else if (id.equals("External Standards")) questions = Arrays.asList(externalStandards);
        else if (id.equals("Internal Standards")) questions = Arrays.asList(internalStandards);
    }
}
