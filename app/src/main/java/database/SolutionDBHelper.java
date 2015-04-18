package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 4/16/2015.
 */
public class SolutionDBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MySolutions.db";
    public static final String TABLE_NAME = "Solutions";

    public static final String COLUMN_NAME_ID = "_id ";
    public static final String COLUMN_NAME_NAME = "Name";
    public static final String COLUMN_NAME_VOLUME = "Volume";
    public static final String COLUMN_NAME_SOLVENT = "Solvent";
    public static final String COLUMN_NAME_SOLUTE = "Solute";
    public static final String COLUMN_NAME_MOLECWEIGHT = "Molecular Weight";
    public static final String COLUMN_NAME_MOLARITY = "Molarity";
    public static final String COLUMN_NAME_MOLES = "Moles";
    public static final String COLUMN_NAME_MASS = "Mass";

    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA = ", ";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SolutionDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA +
                        COLUMN_NAME_VOLUME + TEXT_TYPE + COMMA +
                        COLUMN_NAME_SOLVENT + TEXT_TYPE + COMMA +
                        COLUMN_NAME_SOLUTE + TEXT_TYPE + COMMA +
                        COLUMN_NAME_MOLECWEIGHT + TEXT_TYPE + COMMA +
                        COLUMN_NAME_MOLARITY + TEXT_TYPE + COMMA +
                        COLUMN_NAME_MOLES + TEXT_TYPE + COMMA +
                        COLUMN_NAME_MASS + TEXT_TYPE +
                        ")";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addSolution(String name, String [] data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, name);
        values.put(COLUMN_NAME_VOLUME, data[0]);
        values.put(COLUMN_NAME_SOLVENT, data[1]);
        values.put(COLUMN_NAME_SOLUTE, data[2]);
        values.put(COLUMN_NAME_MOLECWEIGHT, data[3]);
        values.put(COLUMN_NAME_MOLARITY, data[4]);
        values.put(COLUMN_NAME_MOLES, data[5]);
        values.put(COLUMN_NAME_MASS, data[6]);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}

