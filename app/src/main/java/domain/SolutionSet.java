package domain;

/**
 * Created by User on 4/10/15.
 */
public abstract class SolutionSet {

    private String NAME = "";

    private String[] QUESTIONS = {};

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

    public SolutionSet(String name, String[] questions){
        setNAME(name);
        setQUESTIONS(questions);
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

    public String[] getQuestions(){
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

    public abstract void compute();



}
