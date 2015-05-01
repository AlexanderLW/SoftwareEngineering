package domain;

/**
 * Created by User on 4/11/15.
 */
public class Dilution extends SolutionSet {

    private Solution solution;
    private double dilutionVol = 0.0;
    private double stockVolT = 0.0;
    private double dilutionMolarity = 0.0;
    private boolean serial = false;

    public Dilution(Solution solution, boolean flag){

        super("Single Dilution");

        this.solution = solution;

        serial = flag;

        String[] questions = super.concat(solution.getQUESTIONS(), new String[]{
                "What is the volume of the new dilution?",
                "What is the volume of the stock solution you are transferring?",
                "What is the molarity of the new dilution?"
        });

        Answer[] answers = super.concat(solution.getANSWERS(), new Answer[]{
                new Answer("double", false),
                new Answer("double", true, true),
                new Answer("double", true)
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
        if(flag) super.setNAME("Serial Dilution");
    }

    public void compute(int count) {
        if(count == 5) {
            solution.setANSWERS(getANSWERS());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), stockVolT, dilutionVol);

            Solution newSolution = new Solution("Dilution", dilutionVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), dilutionMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());

            if(serial) {
                solution = newSolution;
                String[] questions = super.concat(solution.getQUESTIONS(), new String[]{
                        "What is the volume of the new dilution?",
                        "What is the volume of the last dilution you are transferring?",
                        "What is the molarity of the new dilution?"
                });
                super.setQUESTIONS(questions);
                Answer[] answers = super.concat(solution.getANSWERS(), new Answer[]{
                        new Answer("double", false),
                        new Answer("double", true, true),
                        new Answer("double", true)
                });
                super.setANSWERS(answers);
            }
        }
    }

    public double getCompare(int count){
        if(count == 5)
            return solution.getCompare(count);
        else if(count == 7)
            return solution.getVolFlask();
        return dilutionMolarity;
    }

    public double getCompare2() {
        return dilutionVol;
    }

    public String getDialog() {
        return "Would you like to create another dilution?";
    }

    public int getRestart() {
        if(serial)
            return 7;
        return 6;
    }

    public void setValues(Answer[] answers, int count) {
        solution.setValues(answers, count);
        setAnsw(solution.getAnsw());
        if(count <= 8) {
            for (int i = 6; i <= count; i++) {
                switch (i) {
                    case 6:
                        setDilutionVol(Double.parseDouble(answers[i].getVALUE()) / 1000);
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

    public double getStockVolT() {
        return stockVolT;
    }

    public void setStockVolT(double stockVolT) {
        this.stockVolT = stockVolT;
    }

    public double getDilutionMolarity() {
        return dilutionMolarity;
    }

    public void setDilutionMolarity(double dilutionMolarity) {
        this.dilutionMolarity = dilutionMolarity;
    }
}
