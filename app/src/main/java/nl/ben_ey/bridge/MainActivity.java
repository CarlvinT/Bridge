package nl.ben_ey.bridge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import nl.ben_ey.bridge.fragments.BubblesFragment;
import nl.ben_ey.bridge.fragments.ChatFragment;
import nl.ben_ey.bridge.fragments.ProfileFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Fragment nextFragment;
    private FragmentManager fManger;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bubbles:
                    nextFragment = new BubblesFragment();
                    break;
                case R.id.navigation_chat:
                    nextFragment = new ChatFragment();
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

        // Start the application with the bubbles fragment active
        BubblesFragment bFrag = new BubblesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, bFrag).commit();

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
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
