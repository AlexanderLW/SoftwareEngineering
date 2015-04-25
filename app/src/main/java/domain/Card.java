package domain;

/**
 * Created by User on 4/24/15.
 */
public class Card {

    private String data;

    private boolean solutionType;

    public Card(String data){
        this.data = data;

    }

    public boolean isSolutionType(String type){
        switch(type){
            case "Solution":
                solutionType = isSolution();
                break;
            case "External Standards":
                solutionType = isExternalStandard();
                break;
            default:
                return false;
        }
        return false;
    }

    public boolean isSolution(){ return true; }
    public boolean isExternalStandard(){ return true; }


    public String getData(){
        return data;
    }






}
