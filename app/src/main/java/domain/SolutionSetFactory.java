package domain;

/**
 * Created by User on 4/23/15.
 */
public class SolutionSetFactory {

    public static SolutionSet getSolutionSet(int typeCode){
        if (typeCode == 0)
            return new Solution();
        else if (typeCode == 1)
            return new Dilution(new Solution(), false);
        else if (typeCode == 2)
            return new Dilution(new Solution(), true);
        else if (typeCode == 3)
            return new ExternalStandards(new Solution());
        else if (typeCode == 4)
            return new InternalStandards(new ExternalStandards(new Solution()));


        return null;
    }

    public static SolutionSet getSolutionSet(int typeCode, int fileCode){

        if (typeCode == 0)
            return new Solution();
        else if (typeCode == 1)
            return new Dilution(new Solution(), false);
        else if (typeCode == 2)
            return new Dilution(new Solution(), true);
        else if (typeCode == 3)
            return new ExternalStandards(new Solution());
        else if (typeCode == 4)
            return new InternalStandards(new ExternalStandards(new Solution()));
        return null;
    }
}
