package domain;

/**
 * Created by User on 4/11/15.
 */
public class ExternalStandards extends SolutionSet implements Type {

    private Solution solution;

    //New variables
    private String unknownName = "";
    private int numberOfStandards = 0;
    private double flaskVolume = 0.0;
    private String volumeAnalyteTransferred = "";

    //This is the string that is used for the listview when you click internal standards
    public static final String[] values = {
            "Unknown Standard",
            "Standard"
    };

    //External Standards constructor. This is set to give you the questions and answer related to external standards on the app
    public ExternalStandards(Solution solution){

        super("External Standards");

        this.solution = solution;

        String[] questions = solution.getQuestions();

        questions = super.concat(questions, new String[]{
                "What is the name of the unknown solution?",
                "What is the volume of the volumetric flask within which you will prepare the unknown that is analyzed and the standards (V_tot in mL)?",
                "How many standards, including the unknown, are you going to prepare(no more than 10)?",
                "What are the volumes of the stock analyte solution added to each one of the standards(V_a1, ect. in mL)?"
        });
/*
External standards, Internal standards, and standard addition do not make sense in the current question format. Need to ask Kreller if he wants multiple choice style step through
of process as an alternative.

 */
        Answer[] answers = solution.getAnswers();
        answers = super.concat(answers, new Answer[]{
                new Answer("String", false),
                new Answer("double", false),
                new Answer("int", false),
                new Answer("String", false)
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
        else {
            for (int i = 6; i <= count; i++) {
                switch (i) {
                    case 6:
                        setUnknownName(answers[i].getValue());
                        break;
                    case 7:
                        setFlaskVolume(Double.parseDouble(answers[i].getValue()));
                        break;
                    case 8:
                        setNumberOfStandards(Integer.parseInt(answers[i].getValue()));
                        break;
                    case 9:
                        setVolumeAnalyteTransferred(answers[i].getValue());
                        break;
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

            setData(new String[] {
                    String.valueOf(this.getUnknownName()),
                    String.valueOf(this.getFlaskVolume()),
                    String.valueOf(this.getNumberOfStandards()),
                    String.valueOf(this.getVolumeAnalyteTransferred())
            });
            setDetails(new String[] {
                    "1"
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


    //Set and get for new variables
    public String getUnknownName() {return unknownName;}

    public void setUnknownName(String unknownName) {this.unknownName = unknownName;}

    public int getNumberOfStandards() {return numberOfStandards;}

    public void setNumberOfStandards(int numberOfStandards) {this.numberOfStandards = numberOfStandards;}

    public double getFlaskVolume() {return flaskVolume;}

    public void setFlaskVolume(double flaskVolume) {this.flaskVolume = flaskVolume;}

    public String getVolumeAnalyteTransferred() {return volumeAnalyteTransferred;}

    public void setVolumeAnalyteTransferred(String volumeAnalyteTransferred) {this.volumeAnalyteTransferred = volumeAnalyteTransferred;}

}
