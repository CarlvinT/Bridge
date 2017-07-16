package nl.ben_ey.bridge.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.ben_ey.bridge.R;

import static nl.ben_ey.bridge.database.LoginTrackerSchema.LoginsTracker;

/**
 * Created by Ben-e on 16-7-2017.
 */

public class LoginsCursorAdapter extends CursorAdapter {
    public LoginsCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // Deze newView methode wordt gebruikt om een view te inflaten en terug te geven
    // er wordt nog geen data gebonden op dit punt
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.logins_item, parent, false);
    }

    // Deze bindView methode wordt gebruikt om de data te binden aan een bepaalde view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // View lookup
        TextView loginsUsername = (TextView) view.findViewById(R.id.logins_user_name);
        TextView loginsUserTime = (TextView) view.findViewById(R.id.logins_user_time);

        // Haal de data uit de cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(LoginsTracker.COLUMN_NAME_USERNAME));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(LoginsTracker.COLUMN_NAME_TIME));

        // Stop de data in de views
        loginsUsername.setText(name);
        loginsUserTime.setText(time);

    }
}
