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
        super("Solution",
                new String[] {

                        "What is the volume of the solution you are preparing? (in mL)",
                        "What is the solvent you are using?",
                        "What is the solute?",
                        "What is the molecular weight(g/mol) of your solute?",
                        "What is the molarity of the solution?",
                        "What is the mass of the solute that you are adding? (round to the 2nd Decimal)"
        },
               new Answer[] {
                       new Answer("double"),
                       new Answer("String"),
                       new Answer("String"),
                       new Answer("double"),
                       new Answer("double"),
                       new Answer("double")
               });
    }

    public Solution(String type, double volFlask, String solvent, String solute, double soluteMolWeight, double solMolarity) {
        super(type);
        setVolFlask(volFlask);
        setSolvent(solvent);
        setSolute(solute);
        setSoluteMolWeight(soluteMolWeight);
        setSolMolarity(solMolarity);
    }

    public Solution(String[] data) {
        super("Solution");
        setVolFlask(Double.parseDouble(data[0]));
        setSolvent(data[1]);
        setSolute(data[2]);
        setSoluteMolWeight(Double.parseDouble(data[3]));
        setSolMolarity(Double.parseDouble(data[4]));
        setSolMol(Double.parseDouble(data[5]));
        setSolMass(Double.parseDouble(data[6]));
    }

    @Override
    public void compute() {
        calcMol(solMolarity, volFlask);
        calcMass(solMol, soluteMolWeight);

        setDETAILS(new String[]{
                (volFlask * 100) + "ml",
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

    public double getCompare(){
        return solMass;
    }

    public void setValues(Answer[] answers) {
        for(int i = 0; i < answers.length; i++) {
            switch(i) {
                case 0:
                    setVolFlask(Double.parseDouble(answers[i].getVALUE())/100);
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
