package domain;

/**
 * Created by Samuel on 11/2/2015.
 */
public class NeatSolution extends Solution implements Type {

    private double volFlask = 0.0;
    private String solvent = "";
    private String solute = "";
    private double solVol = 0.0;
    private double solMolarity = 0.0;
    private double solDensity = 0.0;
    private double solMol = 0.0;
    private double molWeight = 0.0;
    private double solMass = 0.0;


    //constructors
    public NeatSolution(){
        super("NeatSolution");
        String[] questions = {
                "What is the volume of the solution you are preparing? (in mL)",
                "What is the solvent you are using?",
                "What is the solute?",
                "What is the molecular weight(g/mol) of your solute?",
                "What is the molarity of the solution?",
                "What is the density of the solute",
                "What is the volume of the solute that you are adding? (in mL)",

        };

        Answer[] answers = {
                new Answer("double", false),
                new Answer("String", false),
                new Answer("String", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", true)
        };

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    public NeatSolution(String type){
        super("NeatSolution");
        String[] questions = {
                "What is the volume of the" + type + "you are preparing? (in mL)",
                "What is the solvent you are using?",
                "What is the solute?",
                "What is the molecular weight(g/mol) of your solute?",
                "What is the molarity of the"+ type +"?",
                "What is the density of the solute",
                "What is the volume of the solute that you are adding? (in mL)"

        };

        Answer[] answers = {
                new Answer("double", false),
                new Answer("String", false),
                new Answer("String", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", true)
        };

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {
        for(int i = 0; i <= count; i++) {
            switch(i) {
                case 0:
                    setVolFlask(Double.parseDouble(answers[i].getVALUE())/1000);
                    break;
                case 1:
                    setSolvent(answers[i].getVALUE());
                    break;
                case 2:
                    setSolute(answers[i].getVALUE());
                    break;
                case 3:
                    setMolWeight(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 4:
                    setSolMolarity(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 5:
                    setSolDensity(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 6:
                    compute(0);
                    setAnswerValue(6, String.valueOf(solMass));
                    super.setAnsw(Double.parseDouble(answers[i].getVALUE()));
                    break;
            }
        }
    }

    //computes data
    public void compute(int count) {
        calcMol();
        calcVol();


        setDETAILS(new String[]{
                (volFlask * 1000) + "ml",
                solMolarity + " molar solution",
                solvent + " as the solvent",
                solVol + "mL of " + solute + " as a solute"
        });

        setDATA(new String[]{
                String.valueOf(volFlask),
                solvent,
                solute,
                String.valueOf(molWeight),
                String.valueOf(solMolarity),
                String.valueOf(solMol),
                String.valueOf(solVol)
        });
    }

    //calculate moles
    public void calcMol() {
        solMol = solMolarity*getVolFlask();
    }

    //calculate volume needed
    public void calcVol() {
        solVol = (double) Math.round(volFlask*solMolarity*molWeight/solDensity);
    }

    //get compare for checks
    public double getCompare(int count){
        return solVol;
    }

    //get compare for volume
    public double getCompare2() {
        return volFlask;
    }

    //get dialog for alert
    public String getDialog() {
        return "Would you like to add another solute?";
    }

    //get restart value
    public int getRestart() {
        return 2;
    }

    public void calcMass(){
        solMass = solDensity*solVol;
    }

    public String getSolvent() {
        return solvent;
    }

    public void setSolvent(String solvent) {
        this.solvent = solvent;
    }

    public double getVolFlask() {
        return volFlask;
    }

    public void setVolFlask(double volFlask) {
        this.volFlask = volFlask;
    }


    public String getSolute() {
        return solute;
    }

    public void setSolute(String solute) {
        this.solute = solute;
    }

    public double getSolVol() {
        return solVol;
    }

    public void setSolVol(double solVol) {
        this.solVol = solVol;
    }

    public double getSolMolarity() {
        return solMolarity;
    }

    public void setSolMolarity(double solMolarity) {
        this.solMolarity = solMolarity;
    }

    public double getSolDensity() {
        return solDensity;
    }

    public void setSolDensity(double solDensity) {
        this.solDensity = solDensity;
    }

    public double getSolMol() {
        return solMol;
    }

    public void setSolMol(double solMol) {
        this.solMol = solMol;
    }

    public double getMolWeight() {
        return molWeight;
    }

    public void setMolWeight(double molWeight) {
        this.molWeight = molWeight;
    }
}

