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
import domain.*;

import java.util.ArrayList;


public class QuestionsActivity extends Activity {
    int count = 0;
    boolean file = false;
    double vol, molecW, molarity, mol, mass, vol2, volT, molarity2, molarity3, volT2, answ;
    String solvent, solute;
    boolean again;
    int id;
    Class<?> soluType;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_questions);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView mytextView =   (TextView)    findViewById(R.id.text);
        mytextView.setTypeface  (myTypeface);
        TextView myeditText =       (TextView)    findViewById(R.id.answer);
        myeditText.setTypeface      (myTypeface);
        TextView myprevButton =     (TextView)  findViewById(R.id.prev);
        myprevButton.setTypeface    (myTypeface);
        TextView mycontButton =     (TextView)  findViewById(R.id.cont);
        mycontButton.setTypeface(myTypeface);

        Bundle type = getIntent().getExtras();

        if(type != null) {
            this.id = type.getInt("id");

            createSolutionType();

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
            finish();
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
            soluType
            Toast.makeText(QuestionsActivity.this, String.valueOf(answ), Toast.LENGTH_SHORT).show();
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

    public void createSolutionType() {
        switch(id) {
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


        }
//        if(file) {
//
//        }
//        else{
//            if (id.equals("Solution"))
//                questions.addAll(Arrays.asList(solution));
//            else if (id.equals("Dilution")) {
//                questions.addAll(Arrays.asList(solution));
//                questions.addAll(Arrays.asList(dilution));
//            } else if (id.equals("Serial Dilution")) {
//                questions.addAll(Arrays.asList(solution));
//                questions.addAll(Arrays.asList(dilution));
//                questions.addAll(Arrays.asList(serialDilution));
//            } else if (id.equals("External Standards")) {
//                questions.addAll(Arrays.asList(solution));
//                questions.addAll(Arrays.asList(externalStandards));
//            } else if (id.equals("Internal Standards")) {
//                questions.addAll(Arrays.asList(solution));
//                questions.addAll(Arrays.asList(internalStandards));
//                questions.addAll(Arrays.asList(externalStandards));
//            }
//        }
    }

//    public void computeSolution(){
//        if (id.equals("Solution")) {
//            for(int i = 0; i < answers.size(); i++) {
//                if(i == 0) {
//                    this.vol = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 1) {
//                    this.solvent = answers.get(i);
//                } else if(i == 2) {
//                    this.solute = answers.get(i);
//                } else if(i == 3) {
//                    this.molecW = Double.parseDouble(answers.get(i));
//                } else if(i == 4) {
//                    this.molarity = Double.parseDouble(answers.get(i));
//                } else if(i == 5) {
//                    this.mass = Double.parseDouble(answers.get(i));
//                }
//            }
//            calcMol(vol, molarity);
//            calcMass(mol, molecW);
//        } else if (id.equals("Dilution")) {
//            for(int i = 0; i < answers.size(); i++) {
//                if(i == 0) {
//                    this.vol2 = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 1) {
//                    this.solvent = answers.get(i);
//                } else if(i == 2) {
//                    this.solute = answers.get(i);
//                } else if(i == 3) {
//                    this.molecW = Double.parseDouble(answers.get(i));
//                } else if(i == 4) {
//                    this.molarity2 = Double.parseDouble(answers.get(i));
//                } else if(i == 5) {
//                    this.mass = Double.parseDouble(answers.get(i));
//                } else if(i == 6) {
//                    this.vol = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 7) {
//                    this.volT = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 8) {
//                    this.molarity = Double.parseDouble(answers.get(i));
//                }
//            }
//            calcDilMolarity(molarity2, molarity, volT);
//        } else if (id.equals("Serial Dilution")) {
//            for(int i = 0; i < answers.size(); i++) {
//                if(i == 0) {
//                    this.vol2 = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 1) {
//                    this.solvent = answers.get(i);
//                } else if(i == 2) {
//                    this.solute = answers.get(i);
//                } else if(i == 3) {
//                    this.molecW = Double.parseDouble(answers.get(i));
//                } else if(i == 4) {
//                    this.molarity2 = Double.parseDouble(answers.get(i));
//                } else if(i == 5) {
//                    this.mass = Double.parseDouble(answers.get(i));
//                } else if(i == 6) {
//                    this.vol = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 7) {
//                    this.volT = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 8) {
//                    this.molarity = Double.parseDouble(answers.get(i));
//                }
//            }
//            calcDilMolarity(molarity2, volT, vol);
//        } else if (id.equals("External Standards")) {
//            for(int i = 0; i < answers.size(); i++) {
//                if(i == 0) {
//                    this.vol2 = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 1) {
//                    this.solvent = answers.get(i);
//                } else if(i == 2) {
//                    this.solute = answers.get(i);
//                } else if(i == 3) {
//                    this.molecW = Double.parseDouble(answers.get(i));
//                } else if(i == 4) {
//                    this.molarity2 = Double.parseDouble(answers.get(i));
//                } else if(i == 5) {
//                    this.mass = Double.parseDouble(answers.get(i));
//                } else if(i == 6) {
//                    this.vol = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 7) {
//                    this.volT = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 8) {
//                    this.molarity = Double.parseDouble(answers.get(i));
//                }
//            }
//            calcDilMolarity(molarity2, volT, vol);
//        } else if (id.equals("Internal Standards")) {
//            for(int i = 0; i < answers.size(); i++) {
//                if(i == 0) {
//                    this.vol2 = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 1) {
//                    this.solvent = answers.get(i);
//                } else if(i == 2) {
//                    this.solute = answers.get(i);
//                } else if(i == 3) {
//                    this.molecW = Double.parseDouble(answers.get(i));
//                } else if(i == 4) {
//                    this.molarity3 = Double.parseDouble(answers.get(i));
//                } else if(i == 5) {
//                    this.mass = Double.parseDouble(answers.get(i));
//                } else if(i == 6) {
//                    this.vol = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 7) {
//                    this.volT = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 8) {
//                    this.molarity = Double.parseDouble(answers.get(i));
//                }else if(i == 9) {
//                    this.volT2 = Double.parseDouble(answers.get(i))/1000;
//                } else if(i == 10) {
//                    this.molarity2 = Double.parseDouble(answers.get(i));
//                }
//            }
//        }
//    }

    public void calcMol(double molar, double volume) {
        this.mol = molar*volume;
    }

    public void calcMass(double mols, double molecularW) {
        this.answ = (double)Math.round((mols*molecularW) * 100) / 100;
    }

    public void calcDilMolarity(double molar1, double volumeT, double volume) {
        this.answ = (double)Math.round((molar1 * (volumeT/volume)) * 100) / 100;;
    }
}
