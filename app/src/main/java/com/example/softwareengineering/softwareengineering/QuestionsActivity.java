package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
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
    int count = 0, current = 0, trys = 0;
    boolean file = false;
    int id;
    SolutionSet soluType;
    String[] data;
    Solution sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/DK Cool Crayon.ttf");
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
            this.file = type.getBoolean("file");
            if(this.file) {
                this.data = type.getStringArray("data");
                this.sol = new Solution(data);
            }
            createSolutionType();
            text.setText(soluType.getQuestion(count));
            if(soluType.getANSWERS()[count].getTYPE().equals("String"))
                answer.setInputType(InputType.TYPE_CLASS_TEXT);
            else answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==2){
            finish();
        }
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
            answer.setSelection(answer.getText().length());
            if(soluType.getANSWERS()[count].getTYPE().equals("String"))
                answer.setInputType(InputType.TYPE_CLASS_TEXT);
            else answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            text.setText(soluType.getQuestion(count));
        }
    }

    public void onContinue(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        if(count == (soluType.getQUESTIONS().length - 1)) {
            if(trys < 3) {
                if (answer.getText().toString().trim().equals(""))
                    Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
                else {
                    soluType.setAnswerValue(count, answer.getText().toString());
                    soluType.setValues(soluType.getANSWERS());
                    soluType.compute();
                    if(soluType.getCompare() == soluType.getAnsw()) {
                        Intent nextScreen = new Intent(QuestionsActivity.this, SaveActivity.class);
                        nextScreen.putExtra("solutionDetails", soluType.getDETAILS());
                        nextScreen.putExtra("solutionData", soluType.getDATA());
                        startActivityForResult(nextScreen, 1);
                        Toast.makeText(QuestionsActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(QuestionsActivity.this, "Incorect please try again", Toast.LENGTH_SHORT).show();
                    }
                }
                trys++;
            }
            else {
                if (answer.getText().toString().trim().equals(""))
                    Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
                else {
                    soluType.setAnswerValue(count, answer.getText().toString());
                    soluType.setValues(soluType.getANSWERS());
                    soluType.compute();
                    if(soluType.getCompare() == soluType.getAnsw()) {
                        Intent nextScreen = new Intent(QuestionsActivity.this, SaveActivity.class);
                        nextScreen.putExtra("solutionDetails", soluType.getDETAILS());
                        nextScreen.putExtra("solutionData", soluType.getDATA());
                        startActivityForResult(nextScreen, 1);
                        Toast.makeText(QuestionsActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        soluType.setAnswerValue(count, answer.getText().toString());
                        soluType.setValues(soluType.getANSWERS());
                        soluType.compute();
                        Intent nextScreen = new Intent(QuestionsActivity.this, SaveActivity.class);
                        nextScreen.putExtra("solutionDetails", soluType.getDETAILS());
                        nextScreen.putExtra("solutionData", soluType.getDATA());
                        startActivityForResult(nextScreen, 1);
                        Toast.makeText(QuestionsActivity.this, "Incorrect correct answer is: " + soluType.getCompare(), Toast.LENGTH_SHORT).show();
                    }
                }
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
                if(soluType.getANSWERS()[count].getTYPE().equals("String"))
                    answer.setInputType(InputType.TYPE_CLASS_TEXT);
                else answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
            else {
                soluType.setAnswerValue(count, answer.getText().toString());
                count++;
                answer.setText("");
                if(soluType.getANSWERS()[count].getTYPE().equals("String"))
                    answer.setInputType(InputType.TYPE_CLASS_TEXT);
                else answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
            text.setText(soluType.getQuestion(count));
        }
    }

    public void createSolutionType() {
        if (file) {
            switch (id) {
                case 1:
                    soluType = new Dilution(sol, false);
                    count = 6;
                    break;
                case 2:
                    soluType = new Dilution(sol, true);
                    count = 6;
                    break;
                case 3:
                    soluType = new ExternalStandards(sol);
                    count = 6;
                    break;
                case 4:
                    soluType = new InternalStandards(new ExternalStandards(sol));
                    count = 6;
                    break;
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
