package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.animations.BtnBounceInterpolator;
import nl.ben_ey.bridge.logarithmics.Collision;
import nl.ben_ey.bridge.models.Bubble;
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

        // Get the display size
        DisplayMetrics mDisplay = new DisplayMetrics();
        referenceActivity.getWindowManager().getDefaultDisplay().getMetrics(mDisplay);

        // Create an arraylist of the pick-items
        ArrayList<Bubble> pick_items = new ArrayList<Bubble>();

        // For every name create an image bubble
        for (Name n : names){
            imgViewEngine(n, mDisplay, pick_items, rootContainer, inflater, 0);
        }

        // Return the inflated layout
        return parentHolder;
    }


    public void imgViewEngine(Name n, DisplayMetrics mDisplay, ArrayList<Bubble> pick_items,
                              RelativeLayout rootContainer, LayoutInflater inflater, int try_count) {

        // Create the layout from file (inflate it)
        RelativeLayout pick_btn_container = (RelativeLayout)
                inflater.inflate(R.layout.pick_button, rootContainer, false);

        // Get the display size
        int displayWidth = mDisplay.widthPixels;
        int displayHeight = mDisplay.heightPixels;

        // Log it
        //Log.wtf("Display width and height", displayWidth + " " + displayHeight);


        // Find the textfield representing the user name
        TextView userName = (TextView) pick_btn_container.findViewById(R.id.user_name);

        // Set the username
        userName.setText(n.getName());

        // Get the size of each bubble
        pick_btn_container.measure(displayWidth, displayHeight);
        int bubbleWidth = pick_btn_container.getMeasuredWidth();
        int bubbleHeight = pick_btn_container.getMeasuredHeight();

        // Log that
        //Log.wtf("Bubble width and height", bubbleWidth + " " + bubbleHeight);

        // Create a new random object
        Random randMargin = new Random();

        // Create a new layout param for the bubble
        // het moet op de layoutparams, dus pickbtncontainerlayout in dit geval. dat zijn ook de rode vlakken hier

        RelativeLayout.LayoutParams pick_btn_container_layout = new RelativeLayout.LayoutParams(
                                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                        RelativeLayout.LayoutParams.WRAP_CONTENT );

        // Set the margins
        // min = 0
        // max = display size minus widget size
        pick_btn_container_layout.leftMargin = randMargin.nextInt((displayWidth - bubbleWidth) + 1);
        pick_btn_container_layout.topMargin = randMargin.nextInt(((displayHeight - 56) - bubbleHeight) + 1);

        // Apply the new layout
        pick_btn_container.setLayoutParams(pick_btn_container_layout);

        // Create a new bubble object and add that to an arraylist
        Bubble pickItem = new Bubble
                                (
                                        pick_btn_container_layout.topMargin,
                                        pick_btn_container_layout.leftMargin,
                                        bubbleWidth,
                                        bubbleHeight,
                                        n.getName(),
                                        pick_btn_container_layout
                                );


        // Run through the list to check for colissions

        //test m nu eens,.... want de 1e zou nooit collision moten he
        // Call the collision class, check if therimgViewEe's no overlap
        //en dit is dus altijd true?. Ik run m ff dan zie je. elk item heeft overlap, zonder uitzondering
        Collision collision = new Collision(pick_items, pickItem);


        //done, anyway.... jouw collisionscode is gebaseerd op vierkanten, niet op cirkels.... ;x
        // nee dat klopt ook, want de containers waar zowel het tekstvak als de image in zitten zijn
        // vierkant =p


        if (!collision.checkOverlap()) {
            System.out.println("There's overlap for " + n.getName());

            //ik kan het resultaat niet zien, maar dit zou moeten voldoen?
            // kan wel. virtual device zit hierachter

            if (try_count > 100) {
                System.err.println("fuck this shit, no circle for you.");
                return;
            }
            // ik denk dat ie alle bubbles opnieuw plaatst wanneer je deze aanroept
            // dat sws niet
            // waar is je logvenster?
            // hiero

            imgViewEngine(n,mDisplay,pick_items,rootContainer, inflater, try_count + 1);
            return;

        } else {
            System.out.println("There's no overlap for " + n.getName());
        }

        pick_items.add(pickItem);

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