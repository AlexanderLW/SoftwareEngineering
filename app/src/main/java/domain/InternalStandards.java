package domain;

/**
 * Created by User on 4/11/15.
 */
public class InternalStandards extends SolutionSet {

    private double volInternalStandard = 0.0;
    private double molarityInternalStandard = 0.0;

    public InternalStandards(ExternalStandards externalStandards){
        super("Internal Standards");

        String[] questions = super.concat(externalStandards.getQUESTIONS(),
                new String[]{
                "What is the volume of the internal standard that you are transferring?(in mL)",
                "What is the molarity of the internal standard in the new standard?(round to the 2nd Decimal)"
        } );

        Answer[] answers = super.concatAnsw(externalStandards.getANSWERS(), new Answer[]{
                new Answer("double"),
                new Answer("double"),
                new Answer("double")
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    @Override
    public void compute() {

    }

    public void setValues(Answer[] answers) {

    }

    public double getCompare(){
        return molarityInternalStandard;
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
}
