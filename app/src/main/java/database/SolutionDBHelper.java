package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 4/16/2015.
 */
public class SolutionDBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MySolutions.db";

    private static final String TEXT_TYPE = " TEXT ";
    private static final String DOUBLE_TYPE = " DOUBLE ";
    private static final String COMMA = ", ";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyDBHandler.SolutionEntry.TABLE_NAME + " (" +
                    MyDBHandler.SolutionEntry._ID + " INTEGER PRIMARY KEY," +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME + DOUBLE_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT + DOUBLE_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY + DOUBLE_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES + DOUBLE_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MASS + DOUBLE_TYPE +
                    ") ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDBHandler.SolutionEntry.TABLE_NAME;

    public SolutionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
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
}

