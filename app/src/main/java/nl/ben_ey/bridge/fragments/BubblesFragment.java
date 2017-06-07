package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.animations.BtnBounceInterpolator;
import nl.ben_ey.bridge.models.Name;

/**
 * Created by Ben on 1-6-2017.
 */

public class BubblesFragment extends Fragment {

    Activity referenceActivity;
    View parentHolder;

    private int counter;
    //BubblePicker picker = new BubblePicker(referenceActivity);

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        // Set the reference activity and inflate the layout for this fragment
        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_bubbles, container, false);

        // Get the centre bubble and the text
        //ImageButton centreBtn = (ImageButton) parentHolder.findViewById(R.id.CentreBubble);
        //TextView yourName = (TextView) parentHolder.findViewById(R.id.UserName);

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
        //centreBtn.startAnimation(centreBtnEnter);
        //yourName.startAnimation(centreBtnEnter);

        // Store all the names for now
        List<Name> names = new LinkedList<>();
        names.add(new Name("Carlvin"));
        names.add(new Name("Carlvi"));
        names.add(new Name("Carlv"));
        names.add(new Name("Carl"));
        names.add(new Name("Car"));
        names.add(new Name("Vroem Vroem"));

        // Print all the names
        for (Name n : names) {
            System.out.println(n.getName() + " heet: " + n.getName());
        }

        // Add button at random position
        RelativeLayout mBubblesContainer =
                (RelativeLayout) parentHolder.findViewById(R.id.bubbles_container);

        Button mNewButton = new Button(referenceActivity);
        DisplayMetrics metrics = new DisplayMetrics();
        referenceActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT);

        rlp.leftMargin = new Random().nextInt(metrics.widthPixels - mNewButton.getWidth());
        rlp.topMargin = new Random().nextInt(metrics.heightPixels - 2*mNewButton.getHeight());
        mNewButton.setLayoutParams(rlp);

        mNewButton.setText("I am a button");
        mNewButton.setId(22);

        mBubblesContainer.addView(mNewButton);

        // Return the inflated layout
        return parentHolder;
    }

}
