package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nl.ben_ey.bridge.LoginActivity;
import nl.ben_ey.bridge.MainActivity;
import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.adapters.LoginsCursorAdapter;
import nl.ben_ey.bridge.database.LoginTrackerDBHandler;
import nl.ben_ey.bridge.database.LoginTrackerSchema;
import nl.ben_ey.bridge.database.LoginTrackerSchema.LoginsTracker;

/**
 * Created by Ben on 1-6-2017.
 */

public class ProfileFragment extends Fragment {

    private FragmentActivity listener;
    private Button mLogoutBtn;
    private Intent i;
    private TextView mLoggedIn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private LoginTrackerDBHandler mDbHelper;
    private Cursor cursor;
    private ListView dbEntries;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDbHelper = new LoginTrackerDBHandler(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        i = new Intent(listener, LoginActivity.class);

        mLogoutBtn = (Button) listener.findViewById(R.id.logout_button);
        mLoggedIn = (TextView) listener.findViewById(R.id.vUser);

        mLoggedIn.setText("Current User: " + user.getEmail());
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        ListView timesLoggedIn = (ListView) listener.findViewById(R.id.times_logged_in);
        Cursor listCursor = callSignIns();

        LoginsCursorAdapter loginsCursorAdapter = new LoginsCursorAdapter(listener, listCursor);
        timesLoggedIn.setAdapter(loginsCursorAdapter);
        loginsCursorAdapter.changeCursor(listCursor);

    }


    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(listener, "Signed out", Toast.LENGTH_LONG).show();

        startActivity(i);
    }


    private Cursor callSignIns()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                LoginsTracker._ID,
                LoginsTracker.COLUMN_NAME_USERNAME,
                LoginsTracker.COLUMN_NAME_TIME
        };

        final String sortOrder =
                LoginsTracker.COLUMN_NAME_TIME + " DESC";

        cursor = db.query(
                LoginsTracker.TABLE_NAME,   // De tabel waarop gequeried wordt
                projection,                 // De kolommen die teruggekeerd worden
                null,                       // Geen kolommen voor de WHERE clausule
                null,                       // Geen waarden voor de WHERE clausule
                null,                       // Geen filter op de rijen
                null,                       // Geen filter op rijgroepen
                sortOrder                   // Sorteervolgorde
        );

        return cursor;
    }
}
