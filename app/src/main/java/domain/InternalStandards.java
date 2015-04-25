package domain;

/**
 * Created by User on 4/11/15.
 */
public class InternalStandards extends SolutionSet {

    Solution solution;
    ExternalStandards externalStandards;
    private double volInternalStandard = 0.0;
    private double molarityInternalStandard = 0.0;

    public InternalStandards(ExternalStandards externalStandards, Solution solution){
        super("Internal Standards");

        this.solution = solution;
        this.externalStandards = externalStandards;

        String[] questions = super.concat(externalStandards.getQUESTIONS(), solution.getQUESTIONS());
        questions = super.concat(questions, new String[]{
                "What is the molarity of the standard being transferred",
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
//        if(count == 8) {
//            externalStandards.setANSWERS(getANSWERS());
//            externalStandards.compute(count);
//        }
//        else {
//            calcMolarity(externalStandards.getSolution().getSolMolarity(), stockSolVol, standardVol);
//
//            Solution newSolution = new Solution("Dilution", standardVol, solution.getSolvent(), solution.getSolute(), solution.getSoluteMolWeight(), standardMolarity);
//            newSolution.compute(count);
//            setDETAILS(newSolution.getDETAILS());
//            setDATA(newSolution.getDATA());
//        }
    }

    public double getCompare(int count){
        return molarityInternalStandard;
    }

    public void setValues(Answer[] answers, int count) {
        externalStandards.setValues(answers, count);
        setAnsw(externalStandards.getAnsw());
        if(count == 9) {
            for (int i = 6; i < answers.length; i++) {
                switch (i) {
                    case 9:
                        setVolInternalStandard(Double.parseDouble(answers[i].getVALUE()) / 100);
                        break;
                    case 10:
                        setMolarityInternalStandard(Double.parseDouble(answers[i].getVALUE()) / 100);
                        break;
                }
            }
        }
    }

    public void calcMolarity(double solutionMolarity, double volTran, double vol) {
        molarityInternalStandard = solutionMolarity * (volTran/vol);
    }

    public double getVolInternalStandard() {
        return volInternalStandard;
    }

    public void setVolInternalStandard(double volInternalStandard) {
        this.volInternalStandard = volInternalStandard;
    }

    public double getMolarityInternalStandard() {
        return molarityInternalStandard;
    }

    public void setMolarityInternalStandard(double molarityInternalStandard) {
        this.molarityInternalStandard = molarityInternalStandard;
    }

    @Override
    public int getTypeCode() {
        return 5;
    }
}
