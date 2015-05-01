package domain;

/**
 * Created by User on 4/10/15.
 */
public class Solution extends SolutionSet {


    private double volFlask = 0.0;
    private String solvent = "";
    private String solute = "";
    private double soluteMolWeight = 0.0;
    private double solMolarity = 0.0;
    private double solMass = 0.0;
    private double solMol = 0.0;

    public Solution(){
        super("Solution");
        String[] questions = {
            "What is the volume of the solution you are preparing? (in mL)",
            "What is the solvent you are using?",
            "What is the solute?",
            "What is the molecular weight(g/mol) of your solute?",
            "What is the molarity of the solution?",
            "What is the mass of the solute that you are adding? (round to the 2nd Decimal)"
        };

        Answer[] answers = {
            new Answer("double", false),
            new Answer("String", false),
            new Answer("String", false),
            new Answer("double", false),
            new Answer("double", false),
            new Answer("double", true)
        };

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    public Solution(String type) {
        super("Solution");
        String[] questions = {
                "What is the volume of the " + type + " you are preparing? (in mL)",
                "What is the solvent you are using?",
                "What is the solute?",
                "What is the molecular weight(g/mol) of your solute?",
                "What is the molarity of the " + type + "?",
                "What is the mass of the solute that you are adding? (round to the 2nd Decimal)"
        };

        Answer[] answers = {
                new Answer("double", false),
                new Answer("String", false),
                new Answer("String", false),
                new Answer("double", false),
                new Answer("double", false),
                new Answer("double", true)
        };

        super.setQUESTIONS(questions);
        super.setANSWERS(answers);
    }

    public Solution(String type, double volFlask, String solvent, String solute, double soluteMolWeight, double solMolarity) {
        super(type,
                new String[] {
                        "Change the vol of the last Dilution? (in mL)",
                        "Change the solvent?",
                        "Change the solute?",
                        "Change the molecular weight(g/mol) of your solute?",
                        "Change the molarity of the solution?",
                        "Change the mass of the solute that you are adding? (round to the 2nd Decimal)"
                },
                new Answer[] {
                        new Answer("double", false, String.valueOf(volFlask)),
                        new Answer("String", false, solvent),
                        new Answer("String", false, solute),
                        new Answer("double", false, String.valueOf(soluteMolWeight)),
                        new Answer("double", false, String.valueOf(solMolarity)),
                        new Answer("double", true)
                });
        setVolFlask(volFlask);
        setSolvent(solvent);
        setSolute(solute);
        setSoluteMolWeight(soluteMolWeight);
        setSolMolarity(solMolarity);
        calcMol(solMolarity, volFlask);
        calcMass(solMol, soluteMolWeight);

        setAnswerValue(5, String.valueOf(solMass));
    }

    public Solution(String[] data) {
        super("Solution",
                new String[] {
                        "What is the volume of the stock solution you are preparing? (in mL)",
                        "What is the solvent you are using?",
                        "What is the solute?",
                        "What is the molecular weight(g/mol) of your solute?",
                        "What is the molarity of the solution?",
                        "What is the mass of the solute that you are adding? (round to the 2nd Decimal)"
                },
                new Answer[] {
                        new Answer("double", false, String.valueOf(Double.valueOf(data[0])*1000)),
                        new Answer("String", false, data[1]),
                        new Answer("String", false, data[2]),
                        new Answer("double", false, data[3]),
                        new Answer("double", false, data[4]),
                        new Answer("double", true, data[6])
                });
    }

    @Override
    public void compute(int count) {
        calcMol(solMolarity, volFlask);
        calcMass(solMol, soluteMolWeight);

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
                String.valueOf(soluteMolWeight),
                String.valueOf(solMolarity),
                String.valueOf(solMol),
                String.valueOf(solMass)
        });
    }

    public double getCompare(int count){
        return solMass;
    }

    public double getCompare2() {
        return volFlask;
    }

    public String getDialog() {
        return "Would you like to add another solute?";
    }

    public int getRestart() {
        return 2;
    }

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
                    setSoluteMolWeight(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 4:
                    setSolMolarity(Double.parseDouble(answers[i].getVALUE()));
                    break;
                case 5:
                    super.setAnsw(Double.parseDouble(answers[i].getVALUE()));
                    break;
            }
        }
    }

    public void calcMol(double molarity, double volume) {
        solMol = molarity*volume;
    }

    public void calcMass(double mols, double molecularW) {
        solMass = (double)Math.round((mols*molecularW) * 100) / 100;
    }

    public double getVolFlask() {
        return volFlask;
    }

    public void setVolFlask(double volFlask) {
        this.volFlask = volFlask;
    }

    public String getSolvent() {
        return solvent;
    }

    public void setSolvent(String solvent) {
        this.solvent = solvent;
    }

    public String getSolute() {
        return solute;
    }

    public void setSolute(String solute) {
        this.solute = solute;
    }

    public double getSoluteMolWeight() {
        return soluteMolWeight;
    }

    public void setSoluteMolWeight(double soluteMolWeight) {
        this.soluteMolWeight = soluteMolWeight;
    }

    public double getSolMolarity() {
        return solMolarity;
    }

    public void setSolMolarity(double solMolarity) {
        this.solMolarity = solMolarity;
    }

    public double getSolMass() {
        return solMass;
    }

    public void setSolMass(double solMass) {
        this.solMass = solMass;
    }

    public double getSolMol() {
        return solMol;
    }

    public void setSolMol(double solMol) {
        this.solMol = solMol;
    }
}
