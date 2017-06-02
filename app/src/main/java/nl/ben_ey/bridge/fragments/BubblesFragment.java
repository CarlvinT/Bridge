package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.animations.BtnBounceInterpolator;

/**
 * Created by Ben on 1-6-2017.
 */

public class BubblesFragment extends Fragment {

    Activity referenceActivity;
    View parentHolder;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        // Set the reference activity and inflate the layout for this fragment
        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_bubbles, container, false);

        // Get the centre bubble and the text
        ImageButton centreBtn = (ImageButton) parentHolder.findViewById(R.id.CentreBubble);
        TextView yourName = (TextView) parentHolder.findViewById(R.id.UserName);

        // Set up the animation
        final Animation centreBtnEnter = AnimationUtils.loadAnimation(referenceActivity,
                R.anim.bubble_bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        //  Higher amplitude means more pronounced bounces
        //  Higher frequency means more wobbles during the animation
        BtnBounceInterpolator interpolator = new BtnBounceInterpolator(0.2, 20);

        // Couple the interpolator to the animation
        centreBtnEnter.setInterpolator(interpolator);

        // Couple the animation to the button and the text
        centreBtn.startAnimation(centreBtnEnter);
        yourName.startAnimation(centreBtnEnter);

        // Return the inflated layout
        return parentHolder;
    }
}
