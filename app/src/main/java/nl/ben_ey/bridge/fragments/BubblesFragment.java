package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import nl.ben_ey.bridge.ChatActivity;
import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.animations.BtnBounceInterpolator;

/**
 * Created by Ben on 1-6-2017.
 */

public class BubblesFragment extends Fragment
{
    private FragmentActivity listener;
    private View layoutView;

    private int counter;
    private ConstraintLayout bubblesRoot;
    private ConstraintLayout centreBtnContainer;
    private BtnBounceInterpolator interpolator;
    private Animation centreBtnAnimation;
    private String userName;
    private String userDistance;

    @Override
    public void onAttach(Context context)
    {
        // Log.wtf("Ran method", "onAttach");
        super.onAttach(context);
        if (context instanceof Activity)
        {
            this.listener = (FragmentActivity) context;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // Log.wtf("Ran method", "onCreate");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Set up the animation
        centreBtnAnimation = AnimationUtils.loadAnimation(listener, R.anim.bubble_bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        //  Higher amplitude means more pronounced bounces
        //  Higher frequency means more wobbles during the animation
        interpolator = new BtnBounceInterpolator(0.2, 20);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Log.wtf("Ran method", "onCreateView");

        // Set the reference activity and inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_bubbles, container, false);

        // Get the root container
        bubblesRoot = (ConstraintLayout) layoutView.findViewById(R.id.pick_bubbles_container);

        // Get the centre button container
        centreBtnContainer = (ConstraintLayout) layoutView.findViewById(R.id.centre_bubble);

        // Couple the interpolator to the animation
        centreBtnAnimation.setInterpolator(interpolator);

        // Couple the animation to the button and the text
        centreBtnContainer.startAnimation(centreBtnAnimation);

        // Return the inflated layout
        return layoutView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // Log.wtf("Ran method", "onViewCreated");
        // Create an arraylist to house all the buttonpick views
        ArrayList<View> pickBubblesList = new ArrayList<>();

        // If there's no saved instance state, counter is 0 which means that there's bubbles
        // to create, otherwise don't create any bubbles


        // Get ever child of the bubblesrRoot constraintlayout and put it in the
        // arraylist
        for (counter = 0; counter < bubblesRoot.getChildCount(); counter++)
        {
            pickBubblesList.add(bubblesRoot.getChildAt(counter));

            // Set up a randomizer
            Random randomStart = new Random();

            // Create a bouncing animation with randomized factors for every buttonpick
            // view and apply it
            for (View btn : pickBubblesList)
            {
                Animation pickBtnAnimation = AnimationUtils.loadAnimation(listener,
                        R.anim.bubble_bounce);

                pickBtnAnimation.setStartOffset(randomStart.nextInt(1000) + 700);
                pickBtnAnimation.setDuration(randomStart.nextInt(1000) + 500);

                pickBtnAnimation.setInterpolator(interpolator);
                btn.startAnimation(pickBtnAnimation);

                final ImageButton button = (ImageButton)((ViewGroup) btn).getChildAt(0);
                final TextView textPlate = (TextView)((ViewGroup) btn).getChildAt(1);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(listener, ChatActivity.class);

                        userName = textPlate.getText().toString();
                        userDistance = "12";

                        i.putExtra("user_name", userName);
                        i.putExtra("user_distance", userDistance);
                        listener.startActivity(i);
                    }
                });
            }
        }
    }


    @Override
    public void onDetach()
    {
        // Log.wtf("Ran method", "onDetach");
        super.onDetach();
        this.listener = null;
        this.layoutView = null;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        // Log.wtf("Ran method", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }
}


    //    public void imgViewEngine(Name n, DisplayMetrics mDisplay, ArrayList<Bubble> pick_items,
//                              RelativeLayout rootContainer, LayoutInflater inflater, int try_count,
//                              CentreBubble centreBubble){
//
//        // @@Todo Sommige items worden tegen de bottom navbar aangedrukt
//        // @@Todo add animations
//
//        // Create the layout from file (inflate it)
//        RelativeLayout pick_btn_container = (RelativeLayout)
//                inflater.inflate(R.layout.pick_button, rootContainer, false);
//
//        // Get the display size
//        int displayWidth = mDisplay.widthPixels;
//        int displayHeight = mDisplay.heightPixels;
//
//        // Log it
//        //Log.wtf("Display width and height", displayWidth + " " + displayHeight);
//
//        // Find the textfield representing the user name
//        TextView userName = (TextView) pick_btn_container.findViewById(R.id.user_name);
//
//        // Set the username
//        userName.setText(n.getName());
//
//        // Get the size of each bubble
//        pick_btn_container.measure(displayWidth, displayHeight);
//        int bubbleWidth = pick_btn_container.getMeasuredWidth();
//        int bubbleHeight = pick_btn_container.getMeasuredHeight();
//
//        // Log that
//        //Log.wtf("Bubble width and height", bubbleWidth + " " + bubbleHeight);
//
//        // Create a new random object
//        Random randMargin = new Random();
//
//        // Create a new layout param for the bubble
//        // het moet op de layoutparams, dus pickbtncontainerlayout in dit geval. dat zijn ook de rode vlakken hier
//
//        RelativeLayout.LayoutParams pick_btn_container_layout = new RelativeLayout.LayoutParams(
//                                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                                                        RelativeLayout.LayoutParams.WRAP_CONTENT );
//
//        // Set the margins
//        // min = 0
//        // max = display size minus widget size
//        // @@ToDo Make sure 56dp is translated to pixels and repaplaces '100' in topmargin
//        pick_btn_container_layout.leftMargin = randMargin.nextInt((displayWidth - bubbleWidth) + 1);
//        pick_btn_container_layout.topMargin = randMargin.nextInt(((displayHeight - 100) - bubbleHeight) + 1);
//
//        // Apply the new layout
//        pick_btn_container.setLayoutParams(pick_btn_container_layout);
//
//        // Create a new bubble object and add that to an arraylist
//        Bubble pickItem = new Bubble
//                                (
//                                        pick_btn_container_layout.topMargin,
//                                        pick_btn_container_layout.leftMargin,
//                                        bubbleWidth,
//                                        bubbleHeight,
//                                        n.getName(),
//                                        pick_btn_container_layout
//                                );
//
//
//        // Create an instance of the collision class
//        Collision collision = new Collision(pick_items, pickItem, centreBubble);
//
//        // If a colission is detected it get's logged and the bubble will be placed
//        // elsewhere
//        if (!collision.checkOverlap()) {
//
//            Log.wtf("There's overlap for ", n.getName());
//            if (try_count < 100) {
//                imgViewEngine(n, mDisplay, pick_items, rootContainer, inflater, try_count + 1,
//                        centreBubble);
//                return;
//            } else {
//                Log.wtf("Too many faulty placements. This bubble won't be placed: ", n.getName());
//                return;
//            }
//        }
//
//        // Add this bubble to the list of already existing bubbles
//        pick_items.add(pickItem);
//
//        // Finished, add the view to the container
//        rootContainer.addView(pick_btn_container);
//
//    }



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




























