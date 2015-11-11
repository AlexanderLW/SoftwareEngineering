package domain;

/**
 * Created by User on 4/10/15.
 */
public abstract class SolutionSet {
    private String NAME = "";
    private String[] QUESTIONS = {};
    private Answer[] ANSWERS;
    double answ;
    private String[] DETAILS = {};
    private String[] DATA = {};

    public static final String[] SOLUTIONTYPES = {
            "Single Solution",
            "Single Dilution",
            "Serial Dilution",
            "External Standards",
            "Internal Standards",
            "Standard Addition"
    };

    //constructors
    public SolutionSet(String name){
        setNAME(name);
    }

    public SolutionSet(String name, String[] questions, Answer[] answers){
        setNAME(name);
        setQUESTIONS(questions);
        setANSWERS(answers);
    }

    //concatenate arrays
    public Answer[] concat(Answer[] a, Answer[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Answer[] c= new Answer[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    //get specific question
    public String getQuestion(int id) {
        return QUESTIONS[id];
    }

    //set answer
    public void setAnswerValue(int id, String value) {
        ANSWERS[id].setVALUE(value);
    }

    //get answer
    public String getAnswerValue(int id) {
        return ANSWERS[id].getVALUE();
    }

    //get the user answer
    public double getAnsw() {
        return answ;
    }

    //set the user answer
    public void setAnsw(double answ) {
        this.answ = answ;
    }

    //erase answers from array
    public void eraseAnswers(int j) {
        for(int i = j; i < ANSWERS.length; i++) {
            ANSWERS[i].setVALUE(null);
        }
    }

    //abstract methods for classes
    public abstract void setValues(Answer[] answers, int count);

    public abstract void compute(int count);

    public abstract double getCompare(int count);

    public abstract double getCompare2();

    public abstract String getDialog();

    public abstract int getRestart();

    //get and set
    public void setNAME(String name){
        NAME = name;
    }

    public String getNAME(){
        return NAME;
    }

    public void setQUESTIONS(String[] questions){
        QUESTIONS = questions;
    }

    public String[] getQUESTIONS() {
        return QUESTIONS;
    }

    public void setANSWERS(Answer[] ANSWERS) {
        this.ANSWERS = ANSWERS;
    }

    public Answer[] getANSWERS() {
        return ANSWERS;
    }

    public String[] getDETAILS() {
        return DETAILS;
    }

    public void setDETAILS(String[] DETAILS) {
        this.DETAILS = DETAILS;
    }

    public String[] getDATA() {
        return DATA;
    }

    public void setDATA(String[] DATA) {
        this.DATA = DATA;
    }
}
