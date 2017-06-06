package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Set the reference activity and inflate the layout for this fragment
        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_bubbles, container, false);

        // Get the centre bubble and the text
        ImageButton centreBtn = (ImageButton) parentHolder.findViewById(R.id.CentreBubble);
        TextView yourName = (TextView) parentHolder.findViewById(R.id.UserName);
        ConstraintLayout centerBtnContainer =
                (ConstraintLayout) parentHolder.findViewById(R.id.centre_button_container);

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
        yourName.startAnimation(centreBtnEnter);
        centerBtnContainer.startAnimation(centreBtnEnter);

        // Store all the names for now
        List<Name> names = new LinkedList<>();
        names.add(new Name("Carlvin"));
        names.add(new Name("Carlvi"));
        names.add(new Name("Carlv"));
        names.add(new Name("Carl"));
        names.add(new Name("Car"));
        names.add(new Name("Vroem"));

        // Create one container that will be used in each pick-button
        RelativeLayout rootContainer =
                (RelativeLayout) parentHolder.findViewById(R.id.bubbles_container);

        // For every name create an image bubble
        for (Name n : names){
            imgViewEngine(n, rootContainer, inflater);
        }

        // Return the inflated layout
        return parentHolder;
    }

    public void imgViewEngine(Name n, RelativeLayout rootContainer,
                              LayoutInflater inflater) {

        // Create the layout from file (inflate it)
        RelativeLayout pick_btn_container = (RelativeLayout)
                inflater.inflate(R.layout.pick_button, rootContainer, false);

        // Find the textfield representing the user name
        TextView userName = (TextView) pick_btn_container.findViewById(R.id.user_name);

        // Set the username
        userName.setText(n.getName());




        // Finished, add the view to the container
        rootContainer.addView(pick_btn_container);


    }
}




























//    // Pseudo:
//    // When placing an element, record the element's position,
//    // when placing the next element, go through the array of positions
//    // checking the current element's position against every other position.
//    // If this element's position is either within positive or negative range
//    // of (another element's position + or - half of that element's width/height)
//    // restart,
//
//    public void imgViewEngine(String name, RelativeLayout pickBubblesContainer,
//                              LayoutInflater inflater) {
//
//        // Create the image button and establish the size of the display
//        ImageButton mPickImage = new ImageButton(referenceActivity);
//        DisplayMetrics metrics = new DisplayMetrics();
//        referenceActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        // Make the imagebutton 250dp x 250dp in size
//        RelativeLayout.LayoutParams larp = new RelativeLayout.LayoutParams(250, 250);
//
//
//        // Randomize the top and left margin of the image button to place it randomly
//        // on the screen
//        larp.leftMargin = mPickImage.getWidth()
//                + new Random().nextInt(metrics.widthPixels - 2 * mPickImage.getWidth());
//        larp.topMargin = mPickImage.getHeight()
//                + new Random().nextInt(metrics.heightPixels - 3 * mPickImage.getHeight());
//        mPickImage.setLayoutParams(larp);
//
//        System.out.println("Left margin " + larp.leftMargin);
//        System.out.println("Top margin " + larp.leftMargin);
//
//
//        // Set the image for the image button
//        mPickImage.setImageResource(R.drawable.pick_bubble);
//
//        // Set the background the same as the rest of the app
//        mPickImage.setBackgroundColor(getResources().getColor(R.color.btnBackground));
//
//        // Set the scaletype to fit_centre to prevent the image from being cut of
//        mPickImage.setScaleType(ImageButton.ScaleType.FIT_CENTER);
//
//        // Add the image to the container
//        pickBubblesContainer.addView(mPickImage);
//
//    }