package com.example.softwareengineering.softwareengineering;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Alexander on 10/20/2015.
 */
public class DefinitionsActivity extends Activity{
    /*
    This page displays definitions of terms selected from the GlossaryActivity page.
     */

    int id;//identifies what term was selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        //set font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/KGTenThousandReasons.ttf");
        TextView head = (TextView) findViewById(R.id.header);
        head.setTypeface(myTypeface);
        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(myTypeface);

        //Takes the id of what was pressed in the GlossaryActivity and passes the value to this
        Bundle type = getIntent().getExtras();
        //Sets id to the id from GlossaryActivity
        if(type != null) {
            this.id = type.getInt("id");
            changeTerm();
            changeDefinition();
        }

    }

    //Method that changes the term textview. Called from onCreate.
    //Can expand by adding a new case for each new term you add to the "glossary" String array in GlossaryActivity
    public void changeTerm() {
        TextView term = (TextView) findViewById(R.id.header);
        switch (id) {
            case 0:
                term.setText("Solution");
                break;
            case 1:
                term.setText("Substance");
                break;
            case 2:
                term.setText("Solvent");
                break;
            case 3:
                term.setText("Solute");
                break;
            case 4:
                term.setText("Concentration");
                break;
            case 5:
                term.setText("Analyte");
                break;
            case 6:
                term.setText("External Standard Calibration (Method of Standard Preparation)");
                break;
            case 7:
                term.setText("Internal Standard Calibration (Method of Standard Preparation)");
                break;
            case 8:
                term.setText("Standard Addition Method of Calibration (Method of Standard Preparation)");
                break;
            case 9:
                term.setText("Internal Standard (Chemical Species)");
                break;
            case 10:
                term.setText("Homogeneous");
                break;
            case 11:
                term.setText("Heterogeneous");
                break;
        }
    }

    //Method that changes the definition textview. Called from onCreate.
    //Can expand by adding a new case for each new term you add to the "glossary" String array in GlossaryActivity
    public void changeDefinition() {
        TextView definition = (TextView) findViewById(R.id.text);
        switch (id) {
            case 0:
                definition.setText("A physical mixture of two or more chemical substances. The substances simply physically exist together " +
                        "(i.e., do not chemically react), and retain their identities, The substances can vary in their relative proportions. " +
                        "The mixture can be decomposed into the separate substances by one or more physical methods of separation.");
                break;
            case 1:
                definition.setText("In chemistry, a substance is defined by a unique chemical composition (formula) and a chemical structure. " +
                        "A substance has a definite (i.e., fixed) composition which is defined by its formula. ");
                break;
            case 2:
                definition.setText("Solute definition");
                break;
            case 3:
                definition.setText("That component of a mixture that is present in the greatest abundance.");
                break;
            case 4:
                definition.setText("\n" +
                        "A measure of the abundance of a solute in a solution. Mathematically, a concentration is a ratio of the amount of the solute " +
                        "(expressed in either mass, moles, or number of fundamental particles), relative to the amount of solution (expressed in either mass, " +
                        "moles, or number of particles, or the volume of space occupied by the mixture). Common measures of concentration include molarity, molality, " +
                        "the mass fractions [parts per million (ppm), parts per billion (ppb) and parts per trillion (ppt)]. \n");
                break;
            case 5:
                definition.setText("A name given to a solute in the solution when the solution is used for purposes of measurement, i.e., when an analytical " +
                        "method will be used to determine the concentration of the solute. ");
                break;
            case 6:
                definition.setText("The preparation of a set of solutions (typically prepared for purposes of calibration), which have a range of concentrations " +
                        "of the solute dissolved in the solvent. The solute here is often called by ‘analyte’, if an analytical method will be used to measure the " +
                        "signal arising from the solute. The signal arising from the detection of the solute/analyte often depends linearly on the concentration of " +
                        "that species. The preparation of standards by the external standards method requires the analyst to have a stock solution of the analyte. " +
                        "This method comprises the simplest approach to calibration and is used whenever the two potential sources of error 1) sample-to-sample " +
                        "variability and 2) matrix effects are not of concern to the analyst. ");
                break;
            case 7:
                definition.setText("A set of solutions, prepared for purposes of calibration, which are prepared to contain two chemical species: 1) the analyte, " +
                        "and 2) the internal standard. Across the set of these standard solutions the analyte is present in a range of concentrations, but the internal " +
                        "standard is present in constant concentration. The preparation of a set of external standards solutions requires the analyst to have 1) a " +
                        "stock solution (relatively high concentration) of the analyte and 2) a stock solution of the internal standards solution. This method of " +
                        "preparing standards is used whenever the analyst is concerned about the potential for sample-to-sample variability in the analytical method.  ");
                break;
            case 8:
                definition.setText("The method of standard addition concerns the preparation of a set of standards which are used in a chemical analysis. By this " +
                        "method (variable volume) aliquots of a stock solution of the analyte are added directly to the (constant volume) aliquots of analyzed sample.");
                break;
            case 9:
                definition.setText("A chemical species that is added to sets of standards prepared according to the internal standard method of calibration. When the " +
                        "analytical method that is used to detect the analyte is applied to the solution, the internal standard also produces a signal. However, the " +
                        "signal from the IS can be differentiated from the signal arising from the analyte. ");
            case 10:
                definition.setText("An adjective, used to describe a solution, if the solution is spatially uniform throughout and no boundaries can be seen between " +
                        "different regions of it. For example, a glass of well mixed tea is homogeneous and the tea will have the same properties (including of course taste!) throughout. ");
                break;
            case 11:
                definition.setText("An adjective, used to describe a solution, if the solution is not spatially uniform and boundaries can be seen between different " +
                        "regions of it. An example of a heterogeneous mixture is a bowl of Oreo cookie ice cream.The exact properties of the mixture are not uniform, " +
                        "and often places can be found with above average concentrations of one or more of the mixture components. ");
                break;
        }
    }

}
