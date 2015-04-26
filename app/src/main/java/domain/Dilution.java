package domain;

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

        this.solution = solution;

        String[] questions = super.concat(solution.getQUESTIONS(), new String[]{
                "What is the volume of the new dilution?",
                "What is the volume of the stock solution you are transferring?",
                "What is the molarity of the new dilution?"
        });

        Answer[] answers = super.concatAnsw(solution.getANSWERS(), new Answer[]{
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", true)
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
        isSerial(flag);
    }

    @Override
    public void compute(int count) {
        if(count == 5) {
            solution.setANSWERS(getANSWERS());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), stockSolVol, dilutionVol);

            Solution newSolution = new Solution("Dilution", dilutionVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), dilutionMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    public double getCompare(int count){
        if(count == 5)
            return solution.getCompare(count);
        return dilutionMolarity;
    }

    @Override
    public int getTypeCode() {
        return 0;
    }

    public void setValues(Answer[] answers, int count) {
        solution.setValues(answers, count);
        setAnsw(solution.getAnsw());
        if(count == 8) {
            for (int i = 6; i < answers.length; i++) {
                switch (i) {
                    case 6:
                        setDilutionVol(Double.parseDouble(answers[i].getVALUE()) / 100);
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
        dilutionMolarity = solutionMolarity * (volTran/vol);
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
            String[] newQuestions = super.concat(this.getQUESTIONS(), new String[]{"Would you like to dilute again?"});
            Answer[] newAnswers = super.concatAnsw(this.getANSWERS(), new Answer[]{new Answer("boolean", false)});
            this.setQUESTIONS(newQuestions);
            this.setANSWERS(newAnswers);
            return flag;
        }
        return flag;
    }
}
