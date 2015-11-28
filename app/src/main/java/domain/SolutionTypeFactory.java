package domain;

/**
 * Created by Alex on 5/1/2015.
 */
public class SolutionTypeFactory {
    public SolutionSet getSolutionSet(String solutionType, Solution sol, Solution sol2) {
        if(solutionType == null)
            return null;
        else if(solutionType.equalsIgnoreCase("Solution"))
            return new Solution();
        else if(solutionType.equalsIgnoreCase("Neat"))
            return new NeatSolution();
        else if(solutionType.equalsIgnoreCase("Concentrated"))
            return new ConcentratedSolution();
        else if(solutionType.equalsIgnoreCase("Dilution"))
            return new Dilution(sol, false);
        else if(solutionType.equalsIgnoreCase("Serial Dilution"))
            return new Dilution(sol, true);
        else if(solutionType.equalsIgnoreCase("External Standards"))
            return new ExternalStandards(sol);
        else if(solutionType.equalsIgnoreCase("Internal Standards"))
            return new InternalStandards(sol, sol2);
        else if(solutionType.equalsIgnoreCase("Standard Addition"))
            return new StandardAddition(sol, sol2);
        return null;
    }
}
