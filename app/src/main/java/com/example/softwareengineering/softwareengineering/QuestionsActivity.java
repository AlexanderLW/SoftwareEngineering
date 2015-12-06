package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import database.SolutionDBHelper;
import domain.Solution;
import domain.SolutionSet;
import domain.SolutionTypeFactory;

public class QuestionsActivity extends Activity {
    /*
    This activity displays all questions and input fields for all solution types. It receives data selected from the solutions and details activity classes to load saved solutions for use.
     */
    private AutoCompleteTextView actv;
    int count = 0, trys = 0;
    boolean file = false, repeat = false, correct = false;
    int id;
    SolutionSet soluType;
    String[] data;
    String[] data2;
    Solution sol;
    Solution sol2;
    String[] autoComplete;
    private SolutionDBHelper mDbHelper = new SolutionDBHelper(this);

    private AlertDialog.Builder builder;
    private AlertDialog repeatDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);
        actv = (AutoCompleteTextView) findViewById(R.id.answer);



        String autoComplete[] = {};
        //for questions asking for solute, set up autocomplete





        //set font
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


        //gets what was passed from previous activity and assigns it
        Bundle type = getIntent().getExtras();
        if(type != null) {
            this.id = type.getInt("id");
            //sets the questions sequence to repeatable
            if(id > 1) repeat = true;
            //if a solution was loaded it creates it for creation of the questions
            this.file = type.getBoolean("file");
            if(this.file) {
                this.data = type.getStringArray("data");
                this.sol = new Solution(data);
                if(id > 3) {
                    this.data2 = type.getStringArray("data2");
                    this.sol2 = new Solution(data2);
                }
                count = 6;
            }
            //creates the correct questions depending on the solution type clicked in types activity
            createSolutionType();
            //sets all texts fields to the first values
            text.setText(soluType.getQuestion(count));
            System.out.println(soluType.getQuestion(count));
            setEdit(answer, "");
            changeHeader();
            changeSubHeader();

        }
        System.out.println(soluType.getQuestion(count));

    }

    //listens for the save to close or pop up dialog box to ask if they want to repeat
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView text = (TextView) findViewById(R.id.text);
        final EditText answer = (EditText) findViewById(R.id.answer);
        //pops up dialog box if repeatable
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
    }

    //on previous button click sets the correct data for questions and puts the answer they entered into the edit box so that they can change it
    public void onPrevious(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);
        //if it is the first question then it closes
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
        if(soluType.getQuestion(count).toString().equals("What is the solvent?")||soluType.getQuestion(count).toString().equals("What is the solvent you are using?")) {
            mDbHelper = new SolutionDBHelper(this);
            autoComplete = mDbHelper.getSolventNames();
        }else if(soluType.getQuestion(count).toString().equals("What is the solute?")||soluType.getQuestion(count).toString().equals("What is the solute you are using?")) {
            mDbHelper = new SolutionDBHelper(this);
            autoComplete = mDbHelper.getSoluteNames();
        }else{
            autoComplete = new String[]{};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autoComplete);
        actv.setAdapter(adapter);
    }

    //on continue it checks to see if input is correct and moves to next questions, if it is the last question then it goes to the save activity
    public void onContinue(View view) {
        TextView text = (TextView) findViewById(R.id.text);
        EditText answer = (EditText) findViewById(R.id.answer);

        soluType.setAnswerValue(count, answer.getText().toString());
        if(answer.getText().toString().trim().equals("")||answer.getText().toString().trim().equals("."))
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        else if(soluType.getAnswers()[count].getType().equals("double") && Double.parseDouble(answer.getText().toString().trim()) == 0)
            Toast.makeText(QuestionsActivity.this, "Please enter something in before continuing", Toast.LENGTH_SHORT).show();
        else if(soluType.getAnswers()[count].getCheck()) {
            check();
        }else {
            correct = true;
        }

        if(correct) {
            //for solvent and solute entries, add the data to the database for future autocompletes
            if(soluType.getQuestion(count).toString().equals("What is the solvent?")||soluType.getQuestion(count).toString().equals("What is the solvent you are using?")) {
                try {
                    mDbHelper.addSolvent(answer.getText().toString());
                }catch(Exception e){//don't reinsert data into the database that already exists//
                }
            }else if(soluType.getQuestion(count).toString().equals("What is the solute?")||soluType.getQuestion(count).toString().equals("What is the solute you are using?")) {
                try {
                    mDbHelper.addSolute(answer.getText().toString());
                }catch(Exception e){//don't reinsert data into the database that already exists//
                }
            }

            if(count == (soluType.getQuestions().length - 1)) {
                save();
            }
            else {
                count++;
                setEdit(answer, soluType.getAnswerValue(count));
                text.setText(soluType.getQuestion(count));
                changeHeader();
                changeSubHeader();
                if(soluType.getQuestion(count).toString().equals("What is the solvent?")||soluType.getQuestion(count).toString().equals("What is the solvent you are using?")) {
                    mDbHelper = new SolutionDBHelper(this);
                    autoComplete = mDbHelper.getSolventNames();
                }else if(soluType.getQuestion(count).toString().equals("What is the solute?")||soluType.getQuestion(count).toString().equals("What is the solute you are using?")) {
                    mDbHelper = new SolutionDBHelper(this);
                    autoComplete = mDbHelper.getSoluteNames();
                }
                else{
                    autoComplete = new String[]{};
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autoComplete);
                actv.setAdapter(adapter);
            }
            trys = 0;
            correct = false;
        }
    }


    //method to set the edit text
    public void setEdit(EditText answer, String input) {
        answer.setText(input);
        answer.setSelection(answer.getText().length());
        if(soluType.getAnswers()[count].getType().equals("String"))
            answer.setInputType(InputType.TYPE_CLASS_TEXT);
        else
            answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    //method to check info/answers
    public void check() {
        soluType.setValues(soluType.getAnswers(), count);
        if(soluType.getAnswers()[count].getTransfer()) {
            if(soluType.getAnswer() > soluType.getCompare(count))
                Toast.makeText(QuestionsActivity.this, "Please enter a volume lower than the total volume " + soluType.getAnswer() + " > " +  soluType.getCompare(count), Toast.LENGTH_SHORT).show();
            else if(soluType.getAnswer() > soluType.getCompare2())
                Toast.makeText(QuestionsActivity.this, "Need a bigger flask", Toast.LENGTH_SHORT).show();
                // else if(soluType.getAnswer() > Double.parseDouble(soluType.getAnswers()[0].getValue()))
                //  Toast.makeText(QuestionsActivity.this, "Not enough solution", Toast.LENGTH_SHORT).show();
            else

                correct = true;
        }
        else {
            compute();
            if (trys < 3 && soluType.getCompare(count) != soluType.getAnswer()) {
                Toast.makeText(QuestionsActivity.this, "Incorrect please try again", Toast.LENGTH_SHORT).show();
                trys++;
            } else if (soluType.getCompare(count) == soluType.getAnswer()) {
                Toast.makeText(QuestionsActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                correct = true;
            } else {
                Toast.makeText(QuestionsActivity.this, "Incorrect the correct answer is: " + soluType.getCompare(count), Toast.LENGTH_SHORT).show();
                soluType.setAnswerValue(count, String.valueOf(soluType.getCompare(count)));
                correct = true;
            }
        }
    }

    //method to compute solutions data
    public void compute() {
        soluType.compute(count);
    }

    //method to continue to save screen
    public void save() {
        compute();
        Intent nextScreen = new Intent(QuestionsActivity.this, SaveActivity.class);
        nextScreen.putExtra("solutionDetails", soluType.getDetails());
        nextScreen.putExtra("solutionData", soluType.getData());
        startActivityForResult(nextScreen, 1);
    }

    //method to create solution
    public void createSolutionType() {
        SolutionTypeFactory sFactory = new SolutionTypeFactory();
        if (file) {
            switch (id) {
                case 1:
                    soluType = sFactory.getSolutionSet("Dilution", sol, null);
                    break;
                case 2:
                    soluType = sFactory.getSolutionSet("Serial Dilution", sol, null);
                    break;
                case 3:
                    soluType = sFactory.getSolutionSet("External Standards", sol, null);
                    break;
                case 4:
                    soluType = sFactory.getSolutionSet("Internal Standards", sol, sol2);
                    break;
                case 5:
                    soluType = sFactory.getSolutionSet("Standard Addition", sol, sol2);
                    break;
            }
        } else {
            switch (id) {
                case 0:
                    soluType = sFactory.getSolutionSet("Solution", null, null);
                    break;
                case -1:
                    soluType = sFactory.getSolutionSet("Neat", null, null);
                    break;
                case -2:
                    soluType = sFactory.getSolutionSet("Concentrated", null, null);
                    break;
                case 1:
                    soluType = sFactory.getSolutionSet("Dilution", new Solution("stock solution"), null);
                    break;
                case 2:
                    soluType = sFactory.getSolutionSet("Serial Dilution", new Solution("stock solution"), null);
                    break;
                case 3:
                    soluType = sFactory.getSolutionSet("External Standards", new Solution("stock analyte solution"), null);
                    break;
                case 4:
                    soluType = sFactory.getSolutionSet("Internal Standards", new Solution("stock analyte solution"),new Solution("standard solution") );
                    break;
                case 5:
                    soluType = sFactory.getSolutionSet("Standard Addition", new Solution("stock analyte solution"), new Solution("standard solution"));
                    break;
            }
        }
    }

    //method to change header
    public void changeHeader() {
        TextView head = (TextView) findViewById(R.id.header);
        switch (id) {
            case 0:
                head.setText("Creating Solution: Solid Solute");
                break;
            case -1:
                head.setText("Creating Solution: Neat Solute");
                break;
            case -2:
                head.setText("Creating Solution: Concentrated Solute");
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

    //method to change subheader
    public void changeSubHeader() {
        TextView head2 = (TextView) findViewById(R.id.subheader);
        head2.setText("Step " + (count + 1) + ":");
    }
}
