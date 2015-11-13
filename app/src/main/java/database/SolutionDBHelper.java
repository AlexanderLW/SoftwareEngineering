package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alex on 4/16/2015.
 */
public class SolutionDBHelper extends SQLiteOpenHelper{
    //creates database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MySolutions.db";

    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA = ", ";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyDBHandler.SolutionEntry.TABLE_NAME + "(" +
                    MyDBHandler.SolutionEntry._ID + " INTEGER PRIMARY KEY, " +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_SOLUTE + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES + TEXT_TYPE + COMMA +
                    MyDBHandler.SolutionEntry.COLUMN_NAME_MASS + TEXT_TYPE +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDBHandler.SolutionEntry.TABLE_NAME + ";";


    public void removeAllSolutions(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

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

    //adds a solution to the database
    public void addSolution(String name, String [] data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_NAME, name);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME, data[0]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT, data[1]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLUTE, data[2]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT, data[3]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY, data[4]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES, data[5]);
        values.put(MyDBHandler.SolutionEntry.COLUMN_NAME_MASS, data[6]);

        db.insert(MyDBHandler.SolutionEntry.TABLE_NAME, null, values);
        db.close();
    }

    //gets all the solution names from the database
    public String[] getSolutionNames() {
        String[] columns = {MyDBHandler.SolutionEntry.COLUMN_NAME_NAME};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(MyDBHandler.SolutionEntry.TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<String> listNames = new ArrayList<>();

        if(c.moveToNext()) {
            for (int i = 0; i < c.getCount(); i++) {
                listNames.add(c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_NAME)));
                c.moveToNext();
            }
        }

        db.close();

        String[] lNames = new String[listNames.size()];
        lNames = listNames.toArray(lNames);

        return lNames;
    }

    //gets data from a specific solution
    public String[] getSolutionData(int id) {
        String[] columns = {MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME, MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT, MyDBHandler.SolutionEntry.COLUMN_NAME_SOLUTE, MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT, MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY, MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES, MyDBHandler.SolutionEntry.COLUMN_NAME_MASS};
        SQLiteDatabase db = this.getReadableDatabase();

        String[] listData = new String[7];

        Cursor c = db.query(MyDBHandler.SolutionEntry.TABLE_NAME, columns, null,null,null,null,null);
        int index = 1;
        while(index<id){
            c.moveToNext();
            index++;
        }


        if(c.moveToNext()) {
            listData[0] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_VOLUME));
            listData[1] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLVENT));
            listData[2] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_SOLUTE));
            listData[3] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLECWEIGHT));
            listData[4] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLARITY));
            listData[5] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_MOLES));
            listData[6] = c.getString(c.getColumnIndexOrThrow(MyDBHandler.SolutionEntry.COLUMN_NAME_MASS));
        }else{
            listData=null;
        }
        db.close();
        return listData;
    }



    //remove a specific solution
    public void removeSolutionData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyDBHandler.SolutionEntry.TABLE_NAME, "_id in (SELECT _id FROM Solutions LIMIT 1 OFFSET "+(id-1)+")",
                null);
        db.close();
    }



    //gets the number of solutions
    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount= db.rawQuery("select count(*) from " + MyDBHandler.SolutionEntry.TABLE_NAME, null);
        mCount.moveToNext();
        int count= mCount.getInt(0);
        mCount.close();
        db.close();

        return count;
    }
}

