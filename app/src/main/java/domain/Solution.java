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

                        "What is the volume of your flask?",
                        "What is the solvent you are using?",
                        "What solute are you using?",
                        "What is the molecular weight of your solute?",
                        "What is the molarity of the solution?",
                        "What is the mass of the solute that you are adding?"
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

    @Override
    public double compute() {
        Answer[] answers = super.getANSWERS();
        for(int i = 0; i < answers.length; i++) {
            switch(i) {
                case 0:
                    volFlask = Double.parseDouble(answers[i].getVALUE());
                    break;
                case 1:
                    solvent = answers[i].getVALUE();
                    break;
                case 2:
                    solute = answers[i].getVALUE();
                    break;
                case 3:
                    soluteMolWeight = Double.parseDouble(answers[i].getVALUE());
                    break;
                case 4:
                    solMolarity = Double.parseDouble(answers[i].getVALUE());
                    break;
                case 5:
                    super.setAnsw(Double.parseDouble(answers[i].getVALUE()));
                    break;
            }
        }

        calcMol(solMolarity, volFlask);
        calcMass(solMol, soluteMolWeight);

        return solMass;
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
}
