package nl.ben_ey.bridge;

import android.content.Context;
import android.net.ParseException;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import nl.ben_ey.bridge.database.Chats;
import nl.ben_ey.bridge.fragments.BubblesFragment;
import nl.ben_ey.bridge.fragments.ChatFragment;
import nl.ben_ey.bridge.fragments.ChatlistFragment;
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
                    nextFragment = new ChatlistFragment();
                    break;
                case R.id.navigation_profile:
                    nextFragment = new ProfileFragment();
                    break;
            }
            final FragmentTransaction fTransa = fManger.beginTransaction();
            fTransa.replace(R.id.fragment_container, nextFragment).addToBackStack(null).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fManger = getSupportFragmentManager();

        // TODO: Set up navigation
        // TODO: Invert launcher icon and create round version

        if (savedInstanceState != null) {
            Log.wtf("Rebuilt", "Fragment already exists");
        } else {
            // Start the application with the bubbles fragment active
            BubblesFragment bFrag = new BubblesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, bFrag).commit();
        }

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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putBoolean("boole", true);
    }


//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            if (position == 1) {
//                return new ChatActivity();
//            } else {
//                return PlaceholderFragment.newInstance(position + 1);
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
//
//
//
//    public static class PlaceholderFragment extends Fragment {
//
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//
//        }
//
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//
//            View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
//            return rootView;
//        }
//    }
}
