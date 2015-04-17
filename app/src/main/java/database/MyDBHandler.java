package database;

import android.provider.BaseColumns;

/**
 * Created by Alex on 4/16/2015.
 */
public final class MyDBHandler {
    public MyDBHandler(){};

    public static abstract class SolutionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Solutions";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_VOLUME = "Volume";
        public static final String COLUMN_NAME_SOLVENT = "Solvent";
        public static final String COLUMN_NAME_SOLUTE = "Solute";
        public static final String COLUMN_NAME_MOLECWEIGHT = "Molecular Weight";
        public static final String COLUMN_NAME_MOLARITY = "Molarity";
        public static final String COLUMN_NAME_MOLES = "Moles";
        public static final String COLUMN_NAME_MASS = "Mass";
    }
}
