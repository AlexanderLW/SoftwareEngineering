package domain;

/**
 * Created by User on 4/11/15.
 */
public class Dilution extends SolutionSet implements Type{
    private Solution solution, newSolution;
    private double dilutionVol = 0.0;
    private double stockVolT = 0.0;
    private double dilutionMolarity = 0.0;
    private boolean serial = false;

    //constructor
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

    //computes data
    public void compute(int count) {
        if(count == 5) {
            solution.setANSWERS(getANSWERS());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), stockVolT, dilutionVol);

            newSolution = new Solution("Dilution", dilutionVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), dilutionMolarity);
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
            return solution.getCompare2();
        return dilutionMolarity;
    }

    //get compare for volume
    public double getCompare2() {
        return dilutionVol;
    }

    //get dialog for alert
    public String getDialog() {
        return "Would you like to create another dilution?";
    }

    //get restart value and set the answers for the new dilution
    public int getRestart() {
        if(serial) {
            String[] questions = super.concat(newSolution.getQUESTIONS(), new String[]{
                    "What is the volume of the new dilution?",
                    "What is the volume of the last dilution you are transferring?",
                    "What is the molarity of the new dilution?"
            });
            setQUESTIONS(questions);
            Answer[] answers = super.concat(newSolution.getANSWERS(), new Answer[]{
                    new Answer("double", false),
                    new Answer("double", true, true),
                    new Answer("double", true)
            });
            setANSWERS(answers);

            solution.setNAME(newSolution.getNAME());
            solution.setVolFlask(newSolution.getVolFlask());
            solution.setSolvent(newSolution.getSolvent());
            solution.setSolute(newSolution.getSolute());
            solution.setSoluteMolWeight(newSolution.getSoluteMolWeight());
            solution.setSolMolarity(newSolution.getSolMolarity());
            solution.setSolMol(newSolution.getSolMol());
            solution.setSolMass(newSolution.getSolMass());
            solution.setQUESTIONS(solution.getQUESTIONS());
            solution.setANSWERS(solution.getANSWERS());
        }
        return 6;
    }

    //calculate molarity
    public void calcMolarity(double solutionMolarity, double volTran, double vol) {
        dilutionMolarity = (double)Math.round((solutionMolarity * (volTran/vol)) * 10000) / 10000;
    }

    //get and set
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
