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
      "Solution",
      "Dilution",
      "Serial Dilution",
      "External Standards",
      "Internal Standards"
    };

    public SolutionSet(String name){
        setNAME(name);
    }

    public SolutionSet(String name, String[] questions, Answer[] answers){
        setNAME(name);
        setQUESTIONS(questions);
        setANSWERS(answers);
    }

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

    public String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public String getQuestion(int id) {
        return QUESTIONS[id];
    }

    public void setANSWERS(Answer[] ANSWERS) {
        this.ANSWERS = ANSWERS;
    }

    public Answer[] getANSWERS() {
        return ANSWERS;
    }

    public Answer[] concatAnsw(Answer[] a, Answer[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Answer[] c= new Answer[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public void setAnswerValue(int id, String value) {
        ANSWERS[id].setVALUE(value);
    }

    public String getAnswerValue(int id) {
        return ANSWERS[id].getVALUE();
    }

    public double getAnsw() {
        return answ;
    }

    public void setAnsw(double answ) {
        this.answ = answ;
    }

    public abstract void compute(int count);

    public abstract void setValues(Answer[] answers, int count);

    public abstract double getCompare(int count);

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

    public abstract int getTypeCode();

}
