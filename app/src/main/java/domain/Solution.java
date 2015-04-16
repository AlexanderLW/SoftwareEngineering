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

    public Solution(){
        super("Solution",
                new String[] {

                        "What is the volume of your flask?",
                        "What is the solvent you are using?",
                        "What solute are you using?",
                        "What is the molecular weight of your solute?",
                        "What is the molarity of the solution?",
                        "What is the mass of the solute that you are adding?"
        });
    }

    @Override
    public void compute() {

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
