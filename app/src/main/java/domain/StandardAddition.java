package domain;

/**
 * Created by Alex on 4/25/2015.
 */
public class StandardAddition extends SolutionSet implements Type {


    Solution unknownAnalyte, analyte;

    //New variables
    private String unknownName = "";
    private double flaskVolume = 0.0;
    private int numberOfVolumes = 0;
    private double volumeUnknownTransferred = 0.0;
    private String volumeAnalyteTransferred = "";

    //This is the string that is used for the listview when you click internal standards
    public static final String[] values = {
            "Unknown Standard",
            "Standard"
    };

    //constructor
    public StandardAddition(Solution analyte, Solution unknownAnalyte){
        super("Standard Addition");

        this.analyte = analyte;
        this.unknownAnalyte = unknownAnalyte;

        String[] questions = super.concat(analyte.getQuestions(), unknownAnalyte.getQuestions());
        questions = super.concat(questions, new String[]{
                "What is the name of the unknown?",
                "What is the volume of the volumetric flasks within which you will prepare the standard (V_tot)?",
                "How many standard solutions are you going to prepare (this includes the standard that only has unknown added to it)?",
                "What volume of the Unknown solution will be added to each of the standards?",
                "What are the volumes of the stock Analyte solution that are added to the standards (V_a1, V_a2, etc.)?"
        } );

        Answer[] answers = super.concat(analyte.getAnswers(), unknownAnalyte.getAnswers());
        answers = super.concat(answers, new Answer[]{
                new Answer("String", false),
                new Answer("double", false),
                new Answer("int", false),
                new Answer("double", false),
                new Answer("String", false)
        });

        super.setQuestions(questions);
        super.setAnswers(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {
        if(count <= 5) {
            analyte.setValues(answers, count);
            setAnswers(analyte.getAnswers());
        }
        if(count <= 11) {
            unknownAnalyte.setValues(answers, count);
            setAnswers(unknownAnalyte.getAnswers());
        }
        else {
            for(int i = 12; i <= count; i++) {
                switch(i) {
                    case 12:
                        setUnknownName(answers[i].getValue());
                        break;
                    case 13:
                        setFlaskVolume(Double.parseDouble(answers[i].getValue()));
                        break;
                    case 14:
                        setNumberOfVolumes(Integer.parseInt(answers[i].getValue()));
                        break;
                    case 15:
                        setVolumeUnknownTransferred(Double.parseDouble(answers[i].getValue()));
                        break;
                    case 16:
                        setVolumeAnalyteTransferred(answers[i].getValue());
                        break;
                }
            }
        }
    }

    //computes data
    public void compute(int count) {
        if(count == 5) {
            analyte.setAnswers(getAnswers());
            analyte.compute(count);
        }
        if(count == 11){
            unknownAnalyte.setAnswers(getAnswers());
            unknownAnalyte.compute(count);
        }
        else {

            setData(new String[]{
                    String.valueOf(this.getUnknownName()),
                    String.valueOf(this.getFlaskVolume()),
                    String.valueOf(this.getNumberOfVolumes()),
                    String.valueOf(this.getVolumeUnknownTransferred()),
                    String.valueOf(this.getVolumeAnalyteTransferred())

            });
            //Set to in integer to distinguish between the 3 types of standard calibrations
            setDetails(new String[] {
                    "3"
            });

        }
    }

    @Override
    public double getCompare(int count) {return 0;}

    @Override
    public double getCompare2() {return 0;}

    @Override
    public String getDialog() {return null;}

    @Override
    public int getRestart() {return 0;}


    //New getters and setters
    public String getUnknownName() {return unknownName;}

    public void setUnknownName(String unknownName) {this.unknownName = unknownName;}

    public double getFlaskVolume() {return flaskVolume;}

    public void setFlaskVolume(double flaskVolume) {this.flaskVolume = flaskVolume;}

    public int getNumberOfVolumes() {return numberOfVolumes;}

    public void setNumberOfVolumes(int numberOfVolumes) {this.numberOfVolumes = numberOfVolumes;}

    public double getVolumeUnknownTransferred() {return volumeUnknownTransferred;}

    public void setVolumeUnknownTransferred(double volumeUnknownTransferred) {this.volumeUnknownTransferred = volumeUnknownTransferred;}

    public String getVolumeAnalyteTransferred() {return volumeAnalyteTransferred;}

    public void setVolumeAnalyteTransferred(String volumeAnalyteTransferred) {this.volumeAnalyteTransferred = volumeAnalyteTransferred;}

}
