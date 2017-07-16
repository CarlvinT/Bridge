package nl.ben_ey.bridge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import nl.ben_ey.bridge.fragments.BubblesFragment;
import nl.ben_ey.bridge.fragments.ChatlistFragment;
import nl.ben_ey.bridge.fragments.ProfileFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Fragment nextFragment;
    private FragmentManager fManger;
    private Intent chatView;
    private FirebaseAuth mAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bubbles:
                    nextFragment = new BubblesFragment();
                    break;
                case R.id.navigation_chat:
                    nextFragment = new ChatlistFragment();
                    break;
                case R.id.navigation_profile:
                    nextFragment = new ProfileFragment();
                    break;
            }
            final FragmentTransaction fTransa = fManger.beginTransaction();
            fTransa.replace(R.id.fragment_container, nextFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fManger = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Start the application with the bubbles fragment active
            BubblesFragment bFrag = new BubblesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, bFrag).commit();
        }

        chatView = new Intent(this, ChatActivity.class);
        chatView.putExtra("user_name", "Stevie Wonder");
        chatView.putExtra("user_distance", "69");


        // Set up the switching between fragments via the bottom navigation
        BottomNavigationView bNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bNavigation.setOnNavigationItemSelectedListener(bottomNavigationListener);


        // Set up font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
