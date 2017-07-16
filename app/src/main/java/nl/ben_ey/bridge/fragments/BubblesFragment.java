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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import nl.ben_ey.bridge.ChatActivity;
import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.animations.BtnBounceInterpolator;

/**
 * Created by Ben on 1-6-2017.
 */

public class    BubblesFragment extends Fragment
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

    private DatabaseReference mDatabase;

    private TextView user1;
    private TextView user2;
    private TextView user3;
    private TextView user4;
    private TextView user5;
    private TextView user6;
    private TextView user7;
    private TextView user8;
    private TextView user9;
    private int count = 1;

    TextView[] bubbles = new TextView[9];





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

        mDatabase = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("People");

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

        // get user text
        user1 = (TextView) layoutView.findViewById(R.id.other_name_1);
        user2 = (TextView) layoutView.findViewById(R.id.other_name_2);
        user3 = (TextView) layoutView.findViewById(R.id.other_name_3);
        user4 = (TextView) layoutView.findViewById(R.id.other_name_4);
        user5 = (TextView) layoutView.findViewById(R.id.other_name_5);
        user6 = (TextView) layoutView.findViewById(R.id.other_name_6);
        user7 = (TextView) layoutView.findViewById(R.id.other_name_7);
        user8 = (TextView) layoutView.findViewById(R.id.other_name_8);
        user9 = (TextView) layoutView.findViewById(R.id.other_name_9);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String data = dataSnapshot.getValue().toString();

                // kijk hier niet het is echt shamefull. moest gedaan worden met een array maar het werkte niet en ik was te lazy om het te doen. -carlvin
                if (count == 1)
                {
                    user1.setText(data);
                    count = count + 1;

                }
                else if (count == 2){
                    user2.setText(data);
                    count = count + 1;
                }
                else if (count == 3){
                    user3.setText(data);
                    count = count + 1;
                }
                else if (count == 4){
                    user4.setText(data);
                    count = count + 1;
                }
                else if (count == 5){
                    user5.setText(data);
                    count = count + 1;
                }
                else if (count == 6){
                    user6.setText(data);
                    count = count + 1;
                }
                else if (count == 7){
                    user7.setText(data);
                    count = count + 1;
                }
                else if (count == 8){
                    user8.setText(data);
                    count = count + 1;
                }
                else if(count == 9){
                    user9.setText(data);
                    count = 0;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        user1.setText("hello");
        user2.setText("Working");

        // Return the inflated layout
        return layoutView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // Create an arraylist to house all the buttonpick views
        ArrayList<View> pickBubblesList = new ArrayList<>();

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