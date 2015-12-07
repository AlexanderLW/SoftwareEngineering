package domain;

/**
 * Created by Samuel on 11/3/2015.
 */

public class ConcentratedSolution extends SolutionSet implements Type {
/*
same as solution except for form of solute
 */
    private double volFlask = 0.0;
    private String solvent = "";
    private String solute = "";
    private double solVol = 0.0;
    private double solMolarity = 0.0;
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
                "What is the molarity (mol/L) of the solution you want to make?",
                "What is the mass % of the solute that you are adding? (in mL)",
                "What mass (in g) of the concentrated solution should be added (Round to two decimal places)?"
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

        super.setQuestions(questions);
        super.setAnswers(answers);
    }

    //set values of answers
    public void setValues(Answer[] answers, int count) {
        for(int i = 0; i <= count; i++) {
            switch(i) {
                case 0:
                    setVolFlask(Double.parseDouble(answers[i].getValue())/1000);
                    break;
                case 1:
                    setSolvent(answers[i].getValue());
                    break;
                case 2:
                    setSolute(answers[i].getValue());
                    break;
                case 3:
                    setMolWeight(Double.parseDouble(answers[i].getValue()));
                    break;
                case 4:
                    setSolMolarity(Double.parseDouble(answers[i].getValue()));
                    break;
                case 5:
                    setMassPercent(Double.parseDouble(answers[i].getValue()));
                    break;
                case 6:
                    super.setAnswer(Double.parseDouble(answers[i].getValue()));
                    break;
            }
        }
    }

    //computes data
    public void compute(int count) {
        calcMassMix();
        calcMol();
        calcSolMass();


        setDetails(new String[]{
                (volFlask * 1000) + "ml",
                solMolarity + " molar solution",
                solvent + " as the solvent",
                massMix + "g of " + massPercent + "% " + solute + " as a solute"
        });

        setData(new String[]{
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
        solMass = solMol*molWeight;
    }

    public void calcMassMix(){
        massMix = Math.round(100*solMolarity*volFlask*molWeight*100/(massPercent))/100;
    }

    //get compare for checks
    public double getCompare(int count){
        return massMix;
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


