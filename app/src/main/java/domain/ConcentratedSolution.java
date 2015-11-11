package domain;

/**
 * Created by Samuel on 11/3/2015.
 */

public class ConcentratedSolution extends Solution implements Type {

    private double volFlask = 0.0;
    private String solvent = "";
    private String solute = "";
    private double solVol = 0.0;
    private double solMolarity = 0.0;
    private double solDensity = 0.0;
    private double solMol = 0.0;
    private double molWeight = 0.0;
    private double solMass = 0.0;
    private double massMix = 0.0;



    private double massPercent = 0.0;


    //constructors
    public ConcentratedSolution(){
        super("NeatSolution");
        String[] questions = {
                "What is the volume of the solution you are preparing? (in mL)",
                "What is the solvent you are using?",
                "What is the solute?",
                "What is the molecular weight(g/mol) of your solute?",
                "What is the molarity of the solution?",
                "What is the mass % of the solute that you are adding? (in mL)",
                "What is the mass of the solute needed?"
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

    public ConcentratedSolution(String type){
        super("NeatSolution");
        String[] questions = {
                "What is the volume of the" + type + "you are preparing? (in mL)",
                "What is the solvent you are using?",
                "What is the solute?",
                "What is the molecular weight(g/mol) of your solute?",
                "What is the molarity of the"+ type +"?",
                "What is the mass % of the solute that you are adding? (in mL)",
                "What is the mass of the solute needed?"
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
                    setMassPercent(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 6:
                    super.setAnsw(Double.parseDouble(answers[i].getVALUE()));
                    break;
            }
        }
    }

    //computes data
    public void compute(int count) {
        calcMol();
        calcSolMass();
        calcMassMix();


        setDETAILS(new String[]{
                (volFlask * 1000) + "ml",
                solMolarity + " molar solution",
                solvent + " as the solvent",
                solMass + "g of " + solute + " as a solute"
        });

        setDATA(new String[]{
                String.valueOf(volFlask),
                solvent,
                solute,
                String.valueOf(molWeight),
                String.valueOf(solMolarity),
                String.valueOf(solMol),
                String.valueOf(solMass)
        });
    }

    //calculate moles
    public void calcMol() {
        solMol = solMolarity*getVolFlask();
    }

    public void calcSolMass(){
        solMass = solMolarity/molWeight;
    }

    public void calcMassMix(){
        setMassMix(solMass/massPercent);
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


    public void setMolWeight(double molWeight) {
        this.molWeight = molWeight;
    }

    public void setMassPercent(double massPercent) {
        this.massPercent = massPercent;
    }

    public void setMassMix(double massMix) {
        this.massMix = massMix;
    }
}


