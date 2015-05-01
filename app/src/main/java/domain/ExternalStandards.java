package domain;

/**
 * Created by User on 4/11/15.
 */
public class ExternalStandards extends SolutionSet implements Type {

    private Solution solution;
    private double standardVol = 0.0;
    private double stockVolT = 0.0;
    private double standardMolarity = 0.0;
    private boolean anotherStandard = false;

    //constructor
    public ExternalStandards(Solution solution){

        super("External Standards");

        this.solution = solution;

        String[] questions = super.concat(solution.getQUESTIONS(), new String[]{
                "What is the volume of the new standard? (in mL)",
                "What is the volume of the stock analyte that you are transferring into the new standard? (in mL)",
                "What is the molarity of the stock analyte in the new standard? (round to the 2nd Decimal)"
        });

        Answer[] answers = super.concat(solution.getANSWERS(), new Answer[]{
                new Answer("double", false),
                new Answer("double", true, true),
                new Answer("double", true)
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {
        if(count <= 5) {
            solution.setValues(answers, count);
            setAnsw(solution.getAnsw());
        }
        if(count <= 8) {
            for (int i = 6; i <= count; i++) {
                switch (i) {
                    case 6:
                        setStandardVol(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 7:
                        setStockVolT(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        setAnsw(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 8:
                        setAnsw(Double.parseDouble(answers[i].getVALUE()));
                        break;
                }
            }
        }
    }

    //computes data
    public void compute(int count) {
        if(count == 5) {
            solution.setANSWERS(getANSWERS());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), stockVolT, standardVol);

            Solution newSolution = new Solution("External Standard", standardVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    //get compare for checks
    public double getCompare(int count){
        if(count == 5)
            return solution.getCompare(count);
        else if(count == 7)
            return solution.getVolFlask();
        return standardMolarity;
    }

    //get compare for volume
    public double getCompare2() {
        return standardVol;
    }

    //get dialog for alert
    public String getDialog() {
        return "Would you like to create another external standard?";
    }

    //get restart value
    public int getRestart() {
        return 6;
    }

    //calculate molarity
    public void calcMolarity(double solutionMolarity, double volTran, double vol) {
        standardMolarity = solutionMolarity * (volTran/vol);
    }

    //get and set
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public double getStandardVol() {
        return standardVol;
    }

    public void setStandardVol(double standardVol) {
        this.standardVol = standardVol;
    }

    public double getStockVolT() {
        return stockVolT;
    }

    public void setStockVolT(double stockVolT) {
        this.stockVolT = stockVolT;
    }

    public double getStandardMolarity() {
        return standardMolarity;
    }

    public void setStandardMolarity(double standardMolarity) {
        this.standardMolarity = standardMolarity;
    }

    public boolean isAnotherStandard() {
        return anotherStandard;
    }

    public void setAnotherStandard(boolean anotherStandard) {
        this.anotherStandard = anotherStandard;
    }
}
