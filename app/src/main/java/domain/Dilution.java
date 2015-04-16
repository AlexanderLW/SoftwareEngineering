package domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 4/11/15.
 */
public class Dilution extends SolutionSet {


    private Solution solution;
    private double dilutionVol = 0.0;
    private double stockSolVol = 0.0;
    private double dilutionMolarity = 0.0;


    public Dilution(Solution solution, boolean flag){

        super("Dilution");

        String[] questions = super.concat(solution.getQuestions(), new String[]{
                "What is the volume of the new dilution?",
                "What is the volume of the stock solution you are transferring?",
                "What is the molarity of the new dilution?"

        });

        super.setQUESTIONS(questions);
        isSerial(flag);
    }



    @Override
    public void compute() {

    }


    public Solution getSolution() {
        return solution;
    }

    public double getDilutionVol() {
        return dilutionVol;
    }

    public void setDilutionVol(double dilutionVol) {
        this.dilutionVol = dilutionVol;
    }

    public double getStockSolVol() {
        return stockSolVol;
    }

    public void setStockSolVol(double stockSolVol) {
        this.stockSolVol = stockSolVol;
    }

    public double getDilutionMolarity() {
        return dilutionMolarity;
    }

    public void setDilutionMolarity(double dilutionMolarity) {
        this.dilutionMolarity = dilutionMolarity;
    }

    private boolean isSerial(boolean flag){
        if (flag) {
            String[] newQuestions = super.concat(this.getQuestions(), new String[]{"Would you like to dilute again?"});
            this.setQUESTIONS(newQuestions);
            return flag;
        }
        return flag;
    }
}
