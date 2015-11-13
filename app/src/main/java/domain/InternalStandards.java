package domain;

/**
 * Created by User on 4/11/15.
 */
public class InternalStandards extends SolutionSet implements Type {

    Solution internalStandard, analyte;

    //Old variables
    private double standardVol = 0.0;
    private double standardVolT = 0.0;
    private double standardMolarity = 0.0;
    private double analyteVolT = 0.0;
    private double analyteMolarity = 0.0;

    //New variables
    private String unknownSolutionName = "";
    private double flaskVolume = 0.0;
    private int numberOfStandards = 0;
    private double volumeUnknownTransferred = 0.0;
    private String volumeAnalyteTransferred = "";
    private double volumeInternalTransferred = 0.0;

    //This is the array that will be made from the volumeAnalyteTransferred.
    //volumeAnalyteTransferred will be read in and separated by comma and space delimited.
    private double[] volumeAnalyteTransferredArray = {};


    //constructor
    public InternalStandards(Solution analyte, Solution internalStandard){
        super("Internal Standards");

        this.analyte = analyte;
        this.internalStandard = internalStandard;

        String[] questions = super.concat(analyte.getQUESTIONS(), internalStandard.getQUESTIONS());
        questions = super.concat(questions, new String[]{
                "What is the name of the unknown?",
                "What is the volume of the volumetric flasks within which you will prepare the unknown solution that is analyzed and the standards(V_tot in mL)?",
                "How many internal standards are you going to prepare?",
                "What volume of the Unknown solution will be added to the unknown solution that is analyzed (in mL)?",

                //Try to make a loop that runs through the value of the internal standards.
                //Break out of the questions value change to initialize a variable that is equal to the number of internal standards
                //Then use concatenation to get back into the questions value.
                "What are the volumes of the stock analyte solution added to each of the standards (V_a1, V_a2, ect. in mL)?",

                "What are the volume of the Internal Standard solution added to each of the standards (in mL)?",
        } );

        Answer[] answers = super.concat(analyte.getANSWERS(), internalStandard.getANSWERS());
        answers = super.concat(answers, new Answer[]{
                new Answer("String", false),
                new Answer("double", false),
                new Answer("int", true),
                new Answer("double", false),
                new Answer("String", true),
                new Answer("double", false)
        });

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {

        //Set the values from the loading (or creating) of an analyte.
        if(count <= 5) {
            analyte.setValues(answers, count);
            setAnsw(analyte.getAnsw());
        }

        //Set the values from the loading (or creating) of an internal standard.
        if(count <= 11) {
            internalStandard.setValues(answers, count);
            setAnsw(internalStandard.getAnsw());
        }

        //Set the values for the questions in this class
        else {
            for(int i = 12; i <= count; i++) {
                switch(i) {
                    case 12:
                        //The parse double is setting the variable to being a double
                        setStandardVol(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 13:
                        setStandardVolT(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        setAnsw(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 14:
                        setAnsw(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 15:
                        setAnalyteVolT(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        setAnsw(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                    case 16:
                        setAnsw(Double.parseDouble(answers[i].getVALUE()) / 1000);
                        break;
                }
            }
        }
    }

    //computes data from the questions asked. Same method name as compute from the solution class.
    public void compute(int count) {
        if(count == 5) {
            analyte.setANSWERS(getANSWERS());
            analyte.compute(count);
        }
        if(count == 11){
            internalStandard.setANSWERS(getANSWERS());
            internalStandard.compute(count);
        }
        if(count == 14) {
            calcStandardMolarity(internalStandard.getSolMolarity(), standardVolT, standardVol);
        }
        else {
            calcAnalyteMolarity(analyte.getSolMolarity(), analyteVolT, standardVol);

            Solution newSolution = new Solution("Internal Standard", standardVolT, internalStandard.getSolvent(), internalStandard.getSolute(), internalStandard.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    //get compare for checks
    public double getCompare(int count){
        if(count == 5)
            return analyte.getCompare(count);
        else if(count == 13)
            return internalStandard.getCompare(count);
        else if(count == 14)
            return internalStandard.getCompare2();
        else if(count == 15)
            return standardMolarity;
        else if(count == 16)
            return analyte.getCompare2();
        return analyteMolarity;
    }

    //get compare for volume
    public double getCompare2() {
        return standardVol;
    }

    //get dialog for alert
    public String getDialog() {
        return "Would you like to create another standard?";
    }

    //get restart value
    public int getRestart() {
        return 18;
    }

    //calculate standard molarity
    public void calcStandardMolarity(double solutionMolarity, double volTran, double vol) {
        standardMolarity = (double)Math.round((solutionMolarity * (volTran/vol)) * 10000) / 10000;
    }

    //calculate analyte molarity
    public void calcAnalyteMolarity(double solutionMolarity, double volTran, double vol) {
        analyteMolarity = (double)Math.round((solutionMolarity * (volTran/vol)) * 10000) / 10000;
    }



    //Setters and getters for the new variables
    public String getUnknownSolutionName() {return unknownSolutionName;}

    public void setUnknownSolutionName(String unknownSolutionName) {this.unknownSolutionName = unknownSolutionName;}

    public double getFlaskVolume() {return flaskVolume;}

    public void setFlaskVolume(double flaskVolume) {this.flaskVolume = flaskVolume;}

    public int getNumberOfStandards() {return numberOfStandards;}

    public void setNumberOfStandards(int numberOfStandards) {this.numberOfStandards = numberOfStandards;}

    public double getVolumeUnknownTransferred() {return volumeUnknownTransferred;}

    public void setVolumeUnknownTransferred(double volumeUnknownTransferred) {this.volumeUnknownTransferred = volumeUnknownTransferred;}

    public String getVolumeAnalyteTransferred() {return volumeAnalyteTransferred;}

    public void setVolumeAnalyteTransferred(String volumeAnalyteTransferred) {this.volumeAnalyteTransferred = volumeAnalyteTransferred;}

    public double getVolumeInternalTransferred() {return volumeInternalTransferred;}

    public void setVolumeInternalTransferred(double volumeInternalTransferred) {this.volumeInternalTransferred = volumeInternalTransferred;}

    public double[] getVolumeAnalyteTransferredArray() {return volumeAnalyteTransferredArray;}

    public void setVolumeAnalyteTransferredArray(double[] volumeAnalyteTransferredArray) {this.volumeAnalyteTransferredArray = volumeAnalyteTransferredArray;}




    //Old Setters and Getters
    //get and set
    public double getStandardVol() {
        return standardVol;
    }

    public void setStandardVol(double standardVol) {this.standardVol = standardVol;}

    public double getStandardVolT() {
        return standardVolT;
    }

    public void setStandardVolT(double standardVolT) {
        this.standardVolT = standardVolT;
    }

    public double getStandardMolarity() {
        return standardMolarity;
    }

    public void setStandardMolarity(double standardMolarity) {this.standardMolarity = standardMolarity;}

    public double getAnalyteVolT() {
        return analyteVolT;
    }

    public void setAnalyteVolT(double analyteVolT) {
        this.analyteVolT = analyteVolT;
    }

    public double getAnalyteMolarity() {
        return analyteMolarity;
    }

    public void setAnalyteMolarity(double analyteMolarity) {this.analyteMolarity = analyteMolarity;}

}
