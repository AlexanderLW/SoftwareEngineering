package domain;

/**
 * Created by Alex on 5/1/2015.
 */
public class SolutionTypeFactory {
    public SolutionSet getSolutionSet(String solutionType, Solution sol) {
        if(solutionType == null)
            return null;
        else if(solutionType.equalsIgnoreCase("Solution"))
            return new Solution();
        else if(solutionType.equalsIgnoreCase("Dilution"))
            return new Dilution(sol, false);
        else if(solutionType.equalsIgnoreCase("Serial Dilution"))
            return new Dilution(sol, true);
        else if(solutionType.equalsIgnoreCase("External Standards"))
            return new ExternalStandards(sol);
        else if(solutionType.equalsIgnoreCase("Internal Standards"))
            return new InternalStandards(sol, new Solution("internal standard"));
        else if(solutionType.equalsIgnoreCase("Standard Addition"))
            return new StandardAddition(sol, new Solution("internal standard"));
        return null;
    }
}
