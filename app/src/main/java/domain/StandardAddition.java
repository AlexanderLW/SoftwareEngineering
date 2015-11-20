package domain;

/**
 * Created by Alex on 4/25/2015.
 */
public class StandardAddition extends SolutionSet implements Type {

    Solution standardAddition, analyte;
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
    private String[] AnalyteTransferredString = new String[10];
    private double[] AnalyteTransferredDouble = new double[AnalyteTransferredString.length];


    //constructor
    public StandardAddition(Solution analyte, Solution StandardAddition){
        super("Standard Addition");

        this.analyte = analyte;
        this.standardAddition = standardAddition;

        String[] questions = super.concat(analyte.getQUESTIONS(), standardAddition.getQUESTIONS());
        questions = super.concat(questions, new String[]{
                "What is the volume of the new standard? (in mL)",
                "What is the volume of the internal standard that you are transferring into the new standard? (in mL)",
                "What is the molarity of the internal standard in the new standard? (round to the 4th Decimal)",
                "What is the volume of the stock analyte that you are transferring into the new standard? (in mL)",
                "What is the molarity of the stock analyte in the new standard? (round to the 4th Decimal)"
        } );

        Answer[] answers = super.concat(analyte.getANSWERS(), standardAddition.getANSWERS());
        answers = super.concat(answers, new Answer[]{
                new Answer("double", false),
                new Answer("double", true, true),
                new Answer("double", true),
                new Answer("double", true, true),
                new Answer("double", true)
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
            standardAddition.setValues(answers, count);
            setAnsw(standardAddition.getAnsw());
        }

        //Set the values for the questions in this class
        else {
            for(int i = 12; i <= count; i++) {
                switch(i) {
                    case 12:
                        //The parse double is setting the variable to being a double
                        setUnknownSolutionName(answers[i].getVALUE());
                        break;
                    case 13:
                        setFlaskVolume(Double.parseDouble(answers[i].getVALUE()));
                        break;
                    case 14:
                        setNumberOfStandards(Integer.parseInt(answers[i].getVALUE()));
                        break;
                    case 15:
                        setVolumeUnknownTransferred(Double.parseDouble(answers[i].getVALUE()));
                        //Might need to be divided by 1000 ??? Don't know the purpose of that yet
                        break;
                    case 16:
                        setVolumeAnalyteTransferred(answers[i].getVALUE());

                        //This is where the string to array transference will occur
                        AnalyteTransferredString = getVolumeAnalyteTransferred().split(",");

                        //Loop that sets all the values of from the user input to the new variable with doubles
                        for(int j = 0; j < AnalyteTransferredString.length; j++) {
                            AnalyteTransferredDouble[i] = Double.parseDouble(AnalyteTransferredString[i]);
                        }

                        break;
                    case 17:
                        setVolumeInternalTransferred(Double.parseDouble(answers[i].getVALUE()));
                        break;
                }
            }
        }
    }

    //Probably not needed since there are no computations
    //computes data from the questions asked. Same method name as compute from the solution class.
    public void compute(int count) {
        if(count == 5) {
            analyte.setANSWERS(getANSWERS());
            analyte.compute(count);
        }
        if(count == 11){
            standardAddition.setANSWERS(getANSWERS());
            standardAddition.compute(count);
        }
        if(count == 14) {
            calcStandardMolarity(standardAddition.getSolMolarity(), standardVolT, standardVol);
        }
        else {
            calcAnalyteMolarity(analyte.getSolMolarity(), analyteVolT, standardVol);

            Solution newSolution = new Solution("Standard Addition", standardVolT, standardAddition.getSolvent(), standardAddition.getSolute(), standardAddition.getSoluteMolWeight(), standardMolarity);
            newSolution.compute(count);
            setDETAILS(newSolution.getDETAILS());
            setDATA(newSolution.getDATA());
        }
    }

    //Probably not needed since there are no checks
    //get compare for checks
    public double getCompare(int count){
        if(count == 5)
            return analyte.getCompare(count);
        else if(count == 13)
            return standardAddition.getCompare(count);
        else if(count == 14)
            return standardAddition.getCompare2();
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

    public String[] getAnalyteTransferredString() {return AnalyteTransferredString;}

    public void setAnalyteTransferredString(String[] analyteTransferredString) {AnalyteTransferredString = analyteTransferredString;}

    public double[] getAnalyteTransferredDouble() {return AnalyteTransferredDouble;}

    public void setAnalyteTransferredDouble(double[] analyteTransferredDouble) {AnalyteTransferredDouble = analyteTransferredDouble;}





    //get and set
    public double getStandardVol() {
        return standardVol;
    }

    public void setStandardVol(double standardVol) {
        this.standardVol = standardVol;
    }

    public double getStandardVolT() {
        return standardVolT;
    }

    public void setStandardVolT(double standardVolT) {
        this.standardVolT = standardVolT;
    }

    public double getStandardMolarity() {
        return standardMolarity;
    }

    public void setStandardMolarity(double standardMolarity) {
        this.standardMolarity = standardMolarity;
    }

    public double getAnalyteVolT() {
        return analyteVolT;
    }

    public void setAnalyteVolT(double analyteVolT) {
        this.analyteVolT = analyteVolT;
    }

    public double getAnalyteMolarity() {
        return analyteMolarity;
    }

    public void setAnalyteMolarity(double analyteMolarity) {
        this.analyteMolarity = analyteMolarity;
    }
}
