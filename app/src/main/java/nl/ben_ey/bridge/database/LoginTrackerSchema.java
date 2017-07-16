package nl.ben_ey.bridge.database;

import android.provider.BaseColumns;

/**
 * Created by Ben-e on 16-7-2017.
 */

public class LoginTrackerSchema
{
    // Om te voorkomen dat de klas per ongeluk geïnstantieerd wordt maken we
    // de constructor private
    private LoginTrackerSchema(){}

    // Binnenste klas die de tabel uitlegt
    public static class LoginsTracker implements BaseColumns {
        public static final String TABLE_NAME = "logins";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_TIME = "time";
    }
}
