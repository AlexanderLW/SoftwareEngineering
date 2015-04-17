package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import domain.Dilution;
import domain.ExternalStandards;
import domain.InternalStandards;
import domain.Solution;
import domain.SolutionSet;


public class QuestionsActivity extends Activity {
    int count = 0, current = 0;
    boolean file = false;
    int id;
    SolutionSet soluType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        EditText answer = (EditText) findViewById(R.id.answer);
        answer.setTypeface(myTypeface);
        TextView myprevButton = (TextView) findViewById(R.id.prev);
        myprevButton.setTypeface(myTypeface);
        TextView mycontButton = (TextView) findViewById(R.id.cont);
        mycontButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.id = type.getInt("id");
            createSolutionType();
            text.setText(soluType.getQuestion(count));
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
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        if(count == 0) {
            finish();
        }
        else {
            if(current < count) current++;
            soluType.setAnswerValue(count, answer.getText().toString());
            count--;
            answer.setText(soluType.getAnswerValue(count));
            text.setText(soluType.getQuestion(count));
        }
    }

    public void onContinue(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        if(count == (soluType.getQUESTIONS().length - 1)) {
            if(soluType.getANSWERS()[count].getVALUE()== null)
                Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
            else {
                soluType.setAnswerValue(count, answer.getText().toString());
                Toast.makeText(QuestionsActivity.this, String.valueOf(soluType.compute()), Toast.LENGTH_SHORT).show();
            }
        }
        else if(answer.getText().toString().trim().equals("")) {
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        }
        else {
            if(current > count) {
                soluType.setAnswerValue(count, answer.getText().toString());
                count++;
                answer.setText(soluType.getAnswerValue(count));
                answer.setSelection(answer.getText().length());
            }
            else {
                soluType.setAnswerValue(count, answer.getText().toString());
                count++;
                answer.setText("");
            }
            text.setText(soluType.getQuestion(count));
        }
    }

    public void createSolutionType() {
        if (file) {
            switch (id) {
                case 1:
                    //soluType = new Dilution(new Solution(), false);
                    break;
                case 2:
                    //soluType = new Dilution(new Solution(), true);
                    break;
                case 3:
                    //soluType = new ExternalStandards(new Solution());
                    break;
                case 4:
                    //soluType = new InternalStandards(new ExternalStandards(new Solution()));
            }
        } else {
            switch (id) {
                case 0:
                    soluType = new Solution();
                    break;
                case 1:
                    soluType = new Dilution(new Solution(), false);
                    break;
                case 2:
                    soluType = new Dilution(new Solution(), true);
                    break;
                case 3:
                    soluType = new ExternalStandards(new Solution());
                    break;
                case 4:
                    soluType = new InternalStandards(new ExternalStandards(new Solution()));
                    break;
            }
        }
    }
}
