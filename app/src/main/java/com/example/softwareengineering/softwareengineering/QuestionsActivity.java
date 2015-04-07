package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class QuestionsActivity extends Activity {
    int count = 0;
    boolean file = false;
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
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
        TextView text = (TextView)findViewById(R.id.text);
        EditText answer = (EditText)findViewById(R.id.answer);
        if(count == 0) {
            Toast.makeText(QuestionsActivity.this, "This is the first question", Toast.LENGTH_SHORT).show();
            Toast.makeText(QuestionsActivity.this, Integer.toString(questions.size()), Toast.LENGTH_SHORT).show();
            Toast.makeText(QuestionsActivity.this, Integer.toString(answers.size()), Toast.LENGTH_SHORT).show();
        }
        else {
            if(answers.size() > count)
                answers.set(count, answer.getText().toString());
            else
                answers.add(count, answer.getText().toString());
            count--;
            answer.setText(answers.get(count));
            text.setText(questions.get(count));
        }
    }

    public void onContinue(View view) {
        TextView text = (TextView)findViewById(R.id.text);
        EditText answer = (EditText)findViewById(R.id.answer);
        if(count == questions.size()-1) {
            if(answers.size() != questions.size())
                answers.add(count, answer.getText().toString());
            Toast.makeText(QuestionsActivity.this, "This is the last question", Toast.LENGTH_SHORT).show();
            Toast.makeText(QuestionsActivity.this, Integer.toString(questions.size()), Toast.LENGTH_SHORT).show();
            Toast.makeText(QuestionsActivity.this, Integer.toString(answers.size()), Toast.LENGTH_SHORT).show();
        }
        else if(answer.getText().toString().trim().equals("")) {
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        }
        else {
            if(answers.size() > count+1) {
                answers.set(count, answer.getText().toString());
                count++;
                answer.setText(answers.get(count));
            }
            else if(answers.size() == count+1) {
                answers.set(count, answer.getText().toString());
                answer.setText("");
                count++;
            }
            else {
                answers.add(count, answer.getText().toString());
                answer.setText("");
                count++;
            }
            text.setText(questions.get(count));
        }
    }

    public void makeQuestions(String id) {
        if(id.equals("Solution"))
            questions.addAll(Arrays.asList(solution));
        else if (id.equals("Dilution")) {
            questions.addAll(Arrays.asList(solution));
            questions.addAll(Arrays.asList(dilution));
        }
        else if (id.equals("Serial Dilution")) {
            questions.addAll(Arrays.asList(solution));
            questions.addAll(Arrays.asList(dilution));
            questions.addAll(Arrays.asList(serialDilution));
        }
        else if (id.equals("External Standards")) {
            questions.addAll(Arrays.asList(solution));
            questions.addAll(Arrays.asList(dilution));
            questions.addAll(Arrays.asList(externalStandards));
        }
        else if (id.equals("Internal Standards")) {
            questions.addAll(Arrays.asList(solution));
            questions.addAll(Arrays.asList(dilution));
            questions.addAll(Arrays.asList(internalStandards));
            questions.addAll(Arrays.asList(externalStandards));
        }
    }
}
