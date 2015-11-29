
package domain;

/**
 * Created by User on 4/11/15.
 */
public class ExternalStandards extends SolutionSet implements Type {

    private Solution solution;
    private double unknownVol = 0.0; //changed from standardVolume to unknownVolume
    private double analyteVol = 0.0; // changed from stockVolT to analyteVolume
    private double standardMolarity = 0.0;
    private boolean anotherStandard = false;

    //External Standards constructor. This is set to give you the questions and answer related to external standards on the app
    public ExternalStandards(Solution solution){

        super("External Standards");

        this.solution = solution;

        String[] questions = super.concat(solution.getQuestions(), new String[]{
                "What is the name of the unknown solution?",
                "Stock solution of the analyte - create new or use saved?",
                "How many standards are you going to prepare?",
                "What is the volume of the volumetric flask within which you will prepare the unknown that is analyzed and the standards?",
                "What are the volumes of the stock analyte solution added to each one of the standards?"

        });
/*
External standards, Internal standards, and standard addition do not make sense in the current question format. Need to ask Kreller if he wants multiple choice style step through
of process as an alternative.

 */
        Answer[] answers = super.concat(solution.getAnswers(), new Answer[]{
                new Answer("String", false),
                new Answer("int", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", false)
        });

        super.setQuestions(questions);
        super.setAnswers(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {
        if(count <= 5) {
            solution.setValues(answers, count);
            setAnswer(solution.getAnswer());
        }
        if(count <= 8) {
            for (int i = 7; i <= count; i++) {
                switch (i) {
                    case 7:
                        setUnknownVol(Double.parseDouble(answers[i].getValue()) / 1000);
                        break;
                    case 8:
                        setAnalyteVol(Double.parseDouble(answers[i].getValue()) / 1000);
                        setAnswer(Double.parseDouble(answers[i].getValue()) / 1000);
                }
            }
        }
    }

    //computes data
    public void compute(int count) {
        if(count == 5) {
            solution.setAnswers(getAnswers());
            solution.compute(count);
        }
        else {
            calcMolarity(solution.getSolMolarity(), analyteVol, unknownVol);

            Solution newSolution = new Solution("External Standard", unknownVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDetails(newSolution.getDetails());
            setData(newSolution.getData());
        }
    }

    //get compare for checks
    public double getCompare(int count){
        if(count == 5)
            return solution.getCompare(count);
        else if(count == 7)
            return solution.getVolFlask();
        return unknownVol;
    }

    //get compare for volume
    public double getCompare2() {
        return unknownVol;
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
        standardMolarity = (double)Math.round((solutionMolarity * (volTran/vol)) * 1000) / 1000;
    }

    //get and set
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public double getUnknownVol() {
        return unknownVol;
    }

    public void setUnknownVol(double unknownVol) {
        this.unknownVol = unknownVol;
    }

    public double getAnalyteVol() {
        return analyteVol;
    }

    public void setAnalyteVol(double analyteVol) {
        this.analyteVol = analyteVol;
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
