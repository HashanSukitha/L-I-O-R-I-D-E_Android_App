package com.lioride.lioride;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME = "Student.db";

    private static final String TABLE_NAME = "Activation";
    private static final String COL1 = "ActivationStatus";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //==========================================================================================================
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 +" TEXT)";
        db.execSQL(createTable);
        //==========================================================================================================
        String createTable1 = "CREATE TABLE UserSettingsTable (IsPWDprotected TEXT, UserName TEXT,PWD TEXT,RecoveryEmail TEXT)";
        db.execSQL(createTable1);
        //==========================================================================================================
        String createTable2 = "CREATE TABLE UserLogTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EntryDate TEXT, " +
                "Entry TEXT)";

        db.execSQL(createTable2);
        //==========================================================================================================


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP IF TABLE EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        //contentValues.put(COL2, item);

        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean addPWDuserNameRecoveryEMail()
    {
        return true;
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getUserSettings()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM UserSettingsTable";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteUserSettings()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM UserSettingsTable");
    }


    public boolean SaveUserSettings(String isProtected,String unameTxt,String pwdTxt,String recoveryEmailTxt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("IsPWDprotected", isProtected);
        contentValues.put("UserName", unameTxt);
        contentValues.put("PWD", pwdTxt);
        contentValues.put("RecoveryEmail", recoveryEmailTxt);


        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert("UserSettingsTable", null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }





}
























