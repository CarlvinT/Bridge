package nl.ben_ey.bridge.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.ben_ey.bridge.R;

/**
 * Created by Ben on 1-6-2017.
 */

public class BubblesFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bubbles, container, false);
    }
}
