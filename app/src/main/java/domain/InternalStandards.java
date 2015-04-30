package domain;

/**
 * Created by User on 4/11/15.
 */
public class InternalStandards extends SolutionSet {

    Solution solution;
    ExternalStandards externalStandards;
    private double standardSolVol;
    private double standardVol = 0.0;
    private double standardMolarity = 0.0;

    public InternalStandards(ExternalStandards externalStandards, Solution solution){
        super("Internal Standards");

        this.solution = solution;
        this.externalStandards = externalStandards;

        String[] questions = super.concat(externalStandards.getQUESTIONS(), solution.getQUESTIONS());
        questions = super.concat(questions, new String[]{
                "What is the volume of the internal standard that you are transferring? (in mL)",
                "What is the molarity of the internal standard in the standard? (round to the 2nd Decimal)"
        } );

        Answer[] answers = super.concatAnsw(externalStandards.getANSWERS(), new Answer[]{
                new Answer("double", false),
                new Answer("double", true),
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    @Override
    public void compute(int count) {
        if(count <= 8) {
            externalStandards.setANSWERS(getANSWERS());
            externalStandards.compute(count);
        }
        else {
            calcMolarity(externalStandards.getSolution().getSolMolarity(), standardSolVol, externalStandards.getStandardVol());

            Solution newSolution = new Solution("Internal Standard", standardVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    public double getCompare(int count){
        return standardMolarity;
    }

    public String getDialog() {
        return "Would you like to create another internal standard?";
    }

    public int getRestart() {
        return 6;
    }

    public void setValues(Answer[] answers, int count) {
        externalStandards.setValues(answers, count);
        setAnsw(externalStandards.getAnsw());
        if(count == 9) {
            for (int i = 9; i < answers.length; i++) {
                switch (i) {
                    case 9:
                        setStandardSolVol(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 10:
                        setStandardVol(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                }
            }
        }
    }

    public void calcMolarity(double solutionMolarity, double volTran, double vol) {
        standardMolarity = solutionMolarity * (volTran/vol);
    }

    public double getStandardSolVol() {
        return standardSolVol;
    }

    public void setStandardSolVol(double standardSolVol) {
        this.standardSolVol = standardSolVol;
    }

    public double getStandardVol() {
        return standardVol;
    }

    public void setStandardVol(double standardVol) {
        this.standardVol = standardVol;
    }

    public double getStandardMolarity() {
        return standardMolarity;
    }

    public void setStandardMolarity(double standardMolarity) {
        this.standardMolarity = standardMolarity;
    }
}
