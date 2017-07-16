package nl.ben_ey.bridge.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.ben_ey.bridge.database.LoginTrackerSchema.*;

/**
 * Created by Ben-e on 16-7-2017.
 */

public class LoginTrackerDBHandler extends SQLiteOpenHelper
{
    // Wanneer je het database schema verandert moet je de database versie met
    // 1 omhoog brengnen
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoginTracker.db";

    // De sql queries voor het maken van de Database
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE" + LoginsTracker.TABLE_NAME   + "(" +
                    LoginsTracker._ID                   + " INTEGER PRIMARY KEY, " +
                    LoginsTracker.COLUMN_NAME_USERNAME  + " TEXT, " +
                    LoginsTracker.COLUMN_NAME_TIME      + " DATETIME" +
                                                        ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LoginsTracker.TABLE_NAME;

    public LoginTrackerDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }
}



















