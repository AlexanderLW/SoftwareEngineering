package domain;

/**
 * Created by User on 4/11/15.
 */
public class ExternalStandards extends SolutionSet {

    private Solution solution;
    private double standardVol = 0.0;
    private double stockSolVol = 0.0;
    private double standardMolarity = 0.0;
    private boolean anotherStandard = false;

    public ExternalStandards(Solution solution){

        super("External Standards");

        this.solution = solution;

        String[] questions = super.concat(solution.getQUESTIONS(), new String[]{
                "What is the volume of the standard? (in mL)",
                "What is the volume of the stock solution you are transferring? (in mL)",
                "What is the molarity of the standard? (round to the 2nd Decimal)"
        });

        Answer[] answers = super.concatAnsw(solution.getANSWERS(), new Answer[]{
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", true)
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    @Override
    public void compute(int count) {
        if(count == 5) {
            solution.setANSWERS(getANSWERS());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), stockSolVol, standardVol);

            Solution newSolution = new Solution("Dilution", standardVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    public double getCompare(int count){
        if(count == 5)
            return solution.getCompare(count);
        return standardMolarity;
    }

    public void setValues(Answer[] answers, int count) {
        solution.setValues(answers, count);
        setAnsw(solution.getAnsw());
        if(count == 8) {
            for (int i = 6; i < answers.length; i++) {
                switch (i) {
                    case 6:
                        setStandardVol(Double.parseDouble(answers[i].getVALUE()) / 100);
                        break;
                    case 7:
                        setStockSolVol(Double.parseDouble(answers[i].getVALUE()) / 100);
                        break;
                    case 8:
                        setAnsw(Double.parseDouble(answers[i].getVALUE()));
                        break;
                }
            }
        }
    }

    public void calcMolarity(double solutionMolarity, double volTran, double vol) {
        standardMolarity = solutionMolarity * (volTran/vol);
    }

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

    public double getStockSolVol() {
        return stockSolVol;
    }

    public void setStockSolVol(double stockSolVol) {
        this.stockSolVol = stockSolVol;
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

    @Override
    public int getTypeCode() {
        return 4;
    }
}
