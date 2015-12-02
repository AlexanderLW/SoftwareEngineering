package domain;

/**
 * Created by User on 4/11/15.
 */
public class InternalStandards extends SolutionSet implements Type {

    Solution internalStandard, analyte;

    //New variables
    private String unknownSolutionName = "";
    private double flaskVolume = 0.0;
    private int numberOfStandards = 0;
    private double volumeUnknownTransferred = 0.0;
    private String volumeAnalyteTransferred = "";
    private double volumeInternalTransferred = 0.0;

    //This is the string that is used for the listview when you click internal standards
    public static final String[] values = {
            "Unknown Standard",
            "Standard"
    };

    //constructor
    public InternalStandards(Solution analyte, Solution internalStandard){
        super("Internal Standards");

        this.analyte = analyte;
        this.internalStandard = internalStandard;

        String[] questions = super.concat(analyte.getQuestions(), internalStandard.getQuestions());

        questions = super.concat(questions, new String[]{
                "What is the name of the unknown?",
                "What is the volume of the volumetric flasks within which you will prepare the unknown solution that is analyzed and the standards(V_tot in mL)?",
                "How many standards, including the unknown, are you going to prepare? (no more than 10)",
                "What volume of the Unknown solution will be added to the unknown solution that is analyzed (in mL)?",
                "What are the volumes of the stock analyte solution added to each of the standards (V_a1,V_a2, ect. in mL. Please separate with a single comma)?",
                "What is the volume of the Internal Standard solution added to each of the standards (in mL)?",

        } );

        Answer[] answers = super.concat(analyte.getAnswers(), internalStandard.getAnswers());
        answers = super.concat(answers, new Answer[]{
                new Answer("String", false),
                new Answer("double", false),
                //This will ideally need a check to make sure it is less than 5
                new Answer("int", false),
                new Answer("double", false),
                //This will need a check to make sure split of this string matches the number of standards
                new Answer("String", false),
                new Answer("double", false)
        });

        super.setQuestions(questions);
        super.setAnswers(answers);
    }

    //set values of answers
    //This method was originally not called unless the second value of the answers concatenation was true
    public void setValues(Answer[] answers, int count) {

        //Set the values from the loading (or creating) of an analyte.
        if(count <= 5) {
            analyte.setValues(answers, count);
            setAnswer(analyte.getAnswer());
        }

        //Set the values from the loading (or creating) of an internal standard.
        if(count <= 11) {
            internalStandard.setValues(answers, count);
            setAnswer(internalStandard.getAnswer());
        }

        //Set the values for the questions in this class
        else {
            for(int i = 12; i <= count; i++) {
                switch(i) {
                    case 12:
                        //The parse double is setting the variable to being a double
                        setUnknownSolutionName(answers[i].getValue());
                        break;
                    case 13:
                        setFlaskVolume(Double.parseDouble(answers[i].getValue()));
                        break;
                    case 14:
                        setNumberOfStandards(Integer.parseInt(answers[i].getValue()));
                        break;
                    case 15:
                        setVolumeUnknownTransferred(Double.parseDouble(answers[i].getValue()));
                        break;
                    case 16:
                        setVolumeAnalyteTransferred(answers[i].getValue());
                        break;
                    case 17:
                        setVolumeInternalTransferred(Double.parseDouble(answers[i].getValue()));
                        break;
                }
            }
        }
    }



    //computes data from the questions asked. Same method name as compute from the solution class.
    //Sets the values for each section, with the else using set data to pass the values to the next screen
    public void compute(int count) {
        if(count == 5) {
            analyte.setAnswers(getAnswers());
            analyte.compute(count);
        }
        if(count == 11) {
            internalStandard.setAnswers(getAnswers());
            internalStandard.compute(count);
        }
        else {
            setData(new String[]{
                    String.valueOf(this.getUnknownSolutionName()),
                    String.valueOf(this.getFlaskVolume()),
                    String.valueOf(this.getNumberOfStandards()),
                    String.valueOf(this.getVolumeAnalyteTransferred()),
                    String.valueOf(this.getVolumeUnknownTransferred()),
                    String.valueOf(this.getVolumeInternalTransferred())
            });
            //Set to in integer to distinguish between the 3 types of standard calibrations
            setDetails(new String[]{
                    "2"
            });
        }
    }



    //None of these are being used since this class is displaying different information from a regular solution
    @Override
    public double getCompare(int count) {return 0;}

    @Override
    public double getCompare2() {return 0;}

    @Override
    public String getDialog() {return null;}

    @Override
    public int getRestart() {return 0;}



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

    public static String[] getValues() {return values;}


}
