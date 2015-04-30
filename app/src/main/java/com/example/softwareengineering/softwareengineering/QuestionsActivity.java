package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
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
import domain.StandardAddition;

public class QuestionsActivity extends Activity {
    int count = 0, trys = 0;
    boolean file = false, repeat = false, correct = false;
    int id;
    SolutionSet soluType;
    String[] data;
    Solution sol;

    private AlertDialog.Builder builder;
    private AlertDialog repeatDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView head = (TextView) findViewById(R.id.header);
        head.setTypeface(myTypeface);
        TextView head2 = (TextView) findViewById(R.id.subheader);
        head2.setTypeface(myTypeface);
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);
        EditText answer = (EditText) findViewById(R.id.answer);
        answer.setTypeface(myTypeface);
        answer.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        TextView myprevButton = (TextView) findViewById(R.id.prev);
        myprevButton.setTypeface(myTypeface);
        TextView mycontButton = (TextView) findViewById(R.id.cont);
        mycontButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.id = type.getInt("id");
            if(id > 1) repeat = true;
            this.file = type.getBoolean("file");
            if(this.file) {
                this.data = type.getStringArray("data");
                this.sol = new Solution(data);
                count = 6;
            }
            createSolutionType();
            text.setText(soluType.getQuestion(count));
            setEdit(answer, "");
        }
    }

    //needs work 2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView text = (TextView) findViewById(R.id.text);
        final EditText answer = (EditText) findViewById(R.id.answer);

        if(resultCode==2){
            if(repeat) {
                builder = new AlertDialog.Builder(this);
                builder.setMessage(soluType.getDialog())

                        // Set the action buttons
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                count = soluType.getRestart();
                                soluType.eraseAnswers(count);
                                setEdit(answer, soluType.getAnswerValue(count));
                                text.setText(soluType.getQuestion(count));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                repeatDialog = builder.create();
                repeatDialog.show();
            }
            else finish();
        }
        else if(resultCode == 3) finish();
    }

    public void onPrevious(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        if(count == 0) {
            finish();
        }
        else {
            soluType.setAnswerValue(count, answer.getText().toString());
            count--;
            setEdit(answer, soluType.getAnswerValue(count));
            text.setText(soluType.getQuestion(count));
            changeHeader();
            changeSubHeader();
        }
    }

    //needs work 2
    public void onContinue(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        soluType.setAnswerValue(count, answer.getText().toString());
        if(answer.getText().toString().trim().equals(""))
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        else if(answer.getText().toString().trim().equals("0") || answer.getText().toString().trim().equals("."))
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        else if(soluType.getANSWERS()[count].getCHECK()) {
            compute();
            check();
        }
        else {
            correct = true;
        }

        if(correct) {
            if(count == (soluType.getQUESTIONS().length - 1))
                save();
            else {
                count++;
                setEdit(answer, soluType.getAnswerValue(count));
                text.setText(soluType.getQuestion(count));
                changeHeader();
                changeSubHeader();
            }
            trys = 0;
            correct = false;
        }
    }

    public void setEdit(EditText answer, String input) {
        answer.setText(input);
        answer.setSelection(answer.getText().length());
        if(soluType.getANSWERS()[count].getTYPE().equals("String"))
            answer.setInputType(InputType.TYPE_CLASS_TEXT);
        else
            answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public void check() {
        if(trys < 3 && soluType.getCompare(count) != soluType.getAnsw()) {
            Toast.makeText(QuestionsActivity.this, "Incorect please try again", Toast.LENGTH_SHORT).show();
            trys++;
        }
        else if(soluType.getCompare(count) == soluType.getAnsw()) {
            Toast.makeText(QuestionsActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            correct = true;
        }
        else {
            Toast.makeText(QuestionsActivity.this, "Incorrect the correct answer is: " + soluType.getCompare(count), Toast.LENGTH_SHORT).show();
            soluType.setAnswerValue(count, String.valueOf(soluType.getCompare(count)));
            correct = true;
        }
    }

    public void compute() {
        soluType.setValues(soluType.getANSWERS(), count);
        soluType.compute(count);
    }

    public void save() {
        compute();
        Intent nextScreen = new Intent(QuestionsActivity.this, SaveActivity.class);
        nextScreen.putExtra("solutionDetails", soluType.getDETAILS());
        nextScreen.putExtra("solutionData", soluType.getDATA());
        startActivityForResult(nextScreen, 1);
    }

    public void createSolutionType() {
        if (file) {
            switch (id) {
                case 1:
                    soluType = new Dilution(sol, false);
                    break;
                case 2:
                    soluType = new Dilution(sol, true);
                    break;
                case 3:
                    soluType = new ExternalStandards(sol);
                    break;
                case 4:
                    soluType = new InternalStandards(new ExternalStandards(sol), new Solution());
                    break;
                case 5:
                    soluType = new StandardAddition(new ExternalStandards(sol), new Solution());
                    break;
            }
        } else {
            switch (id) {
                case 0:
                    soluType = new Solution();
                    break;
                case 1:
                    soluType = new Dilution(new Solution("stock solution"), false);
                    break;
                case 2:
                    soluType = new Dilution(new Solution("stock solution"), true);
                    break;
                case 3:
                    soluType = new ExternalStandards(new Solution("stock solution"));
                    break;
                case 4:
                    soluType = new InternalStandards(new ExternalStandards(new Solution("stock solution")), new Solution("internal standard"));
                    break;
                case 5:
                    soluType = new StandardAddition(new ExternalStandards(new Solution("stock solution")), new Solution("standard"));
                    break;
            }
        }
    }

    public void changeHeader() {
        TextView head = (TextView) findViewById(R.id.header);
        switch (id) {
            case 0:
                head.setText("Creating Solution");
                break;
            case 1:
                head.setText("Creating Dilution");
                break;
            case 2:
                head.setText("Creating Serial Dilutions");
                break;
            case 3:
                head.setText("Creating External Standards");
                break;
            case 4:
                head.setText("Creating Internal Standards");
                break;
            case 5:
                head.setText("Creating Standard Using Standard Addition Method");
                break;
        }
    }

    public void changeSubHeader() {
        TextView head2 = (TextView) findViewById(R.id.subheader);
        head2.setText("Step " + (count + 1) + ":");
    }
}
