package domain;

/**
 * Created by User on 4/10/15.
 */
public abstract class SolutionSet {

    /*
    Parent class for all solution types.
     */
    private String name = "";
    private String[] questions = {};
    private Answer[] answers;
    double answer;
    private String[] details = {};
    private String[] data = {};
    private String NAME = "";
    private String[] QUESTIONS = {};
    private Answer[] ANSWERS;
    double answ;
    private String[] DETAILS = {};
    private String[] DATA = {};
    private String[] DATA2 = {};

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
        setName(name);
    }

    public SolutionSet(String name, String[] questions, Answer[] answers){
        setName(name);
        setQuestions(questions);
        setAnswers(answers);
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
        return questions[id];
    }

    //set answer
    public void setAnswerValue(int id, String value) {
        answers[id].setValue(value);
    }

    //get answer
    public String getAnswerValue(int id) {
        return answers[id].getValue();
    }

    //get the user answer
    public double getAnswer() {
        return answer;
    }

    //set the user answer
    public void setAnswer(double answer) {
        this.answer = answer;
    }

    //erase answers from array
    public void eraseAnswers(int j) {
        for(int i = j; i < answers.length; i++) {
            answers[i].setValue(null);
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
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setQuestions(String[] questions){
        this.questions = questions;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String[] getDATA2() {
        return DATA2;
    }

    public void setDATA2(String[] DATA2) {
        this.DATA2 = DATA2;
    }



}
