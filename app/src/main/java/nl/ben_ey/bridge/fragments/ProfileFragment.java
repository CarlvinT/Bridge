package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nl.ben_ey.bridge.LoginActivity;
import nl.ben_ey.bridge.MainActivity;
import nl.ben_ey.bridge.R;

/**
 * Created by Ben on 1-6-2017.
 */

public class ProfileFragment extends Fragment {

    private FragmentActivity listener;
    private Button mLogoutBtn;
    private Intent i;
    private TextView mLoggedIn;
    private FirebaseAuth mAuth;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mLogoutBtn = (Button) listener.findViewById(R.id.logout_button);
        mLoggedIn = (TextView) listener.findViewById(R.id.vUser);
        i = new Intent(listener, LoginActivity.class);
        mAuth = FirebaseAuth.getInstance();

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();

        mLoggedIn.setText("Current User: " + user.getEmail());

    }


    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(listener, "Signed out", Toast.LENGTH_LONG).show();

        startActivity(i);

    }
}
