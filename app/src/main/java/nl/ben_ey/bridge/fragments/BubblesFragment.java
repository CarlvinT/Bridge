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
        names.add(new Name("Vroem Vroem"));

        // Create one container that will be used in each pick-button
        RelativeLayout pickBubbleContainer =
                (RelativeLayout) parentHolder.findViewById(R.id.bubbles_container);

        // For every name create an image bubble

            imgViewEngine(names, pickBubbleContainer, inflater);


        // Return the inflat//ed layout
        return parentHolder; //
    }
//dat is dus bijna hetzelfde als ik nu wil doen =p lol great minds think alike
    // Pseudo:
    // When placing an element, record the element's position,
    // when placing the next element, go through the array of positions
    // checking the current element's position against every other position.
    // If this element's position is either within positive or negative range
    // of (another element's position + or - half of that element's width/height)
    // restart,

    public void imgViewEngine(List<Name> names, RelativeLayout pickBubblesContainer,
                              LayoutInflater inflater) {

        List<BubbleHistory> lelijk = new ArrayList<>();
//lelijk.add(new BubbleHistory(1,1,1)); //center bubble @@TODO

        for (Name _name : names) {
            String name = _name.getName();
            // Create the image button and establish the size of the display
            ImageButton mPickImage = new ImageButton(referenceActivity);

            DisplayMetrics metrics = new DisplayMetrics();
            referenceActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            // Make the imagebutton 250dp x 250dp in size
            //sure it is dp?
            RelativeLayout.LayoutParams larp = new RelativeLayout.LayoutParams(250,250);




            int maxTries = 100;
            boolean found = false;

            for (int i = 0; i < maxTries; i++) {

                int r = 250 * (int) referenceActivity.getResources().getDisplayMetrics().density;

                larp.leftMargin = mPickImage.getWidth() // is dit in pixels of dpi?
                        + new Random().nextInt(metrics.widthPixels - 2 * mPickImage.getWidth());
                larp.topMargin = mPickImage.getHeight()
                        + new Random().nextInt(metrics.heightPixels - 3 * mPickImage.getHeight());


                boolean valid = true;
                for (BubbleHistory first : lelijk) {

                   /* int dx = first.x - larp.leftMargin;
                    int dy = first.y - larp.rightMargin;
                    dx = Math.abs(dx);
                    dy = Math.abs(dy);
                    int ra = first.r + larp.height/2;

                    if (dx-ra < 0 || dy-ra < 0) {
                        valid = false;
                        break;
                    }*/
                    int dx = first.x - (larp.leftMargin+(int)(larp.height/4.0));
                    int dy = first.y - (larp.rightMargin+(int)(larp.height/4.0));

                    int ra = first.r + (int)(0.7*larp.height/2.0);

                    if ( dx*dx+dy*dy < ra * ra) {
                        valid = false;
                        break;
                    }



                    // Markeer zometeen jouw code ff want credit where credit's due je weet =)
                    //njah, weet niet of ik hier straks trots op kan zijn xD


                }
if (valid) {
    found = true;


    lelijk.add(new BubbleHistory(larp.leftMargin+(int)(larp.height/4.0),larp.rightMargin+(int)(larp.height/4.0),(int)(0.7*larp.height/2.0)));
    break;
}



            }

if (!found) {

    System.out.println("fuck it =(");
}


            mPickImage.setLayoutParams(larp);

            System.out.println("Left margin " + larp.leftMargin);
            System.out.println("Top margin " + larp.leftMargin);


            // Set the image for the image button
            mPickImage.setImageResource(R.drawable.pick_bubble);

            // Set the background the same as the rest of the app
            mPickImage.setBackgroundColor(getResources().getColor(R.color.btnBackground));

            // Set the scaletype to fit_centre to prevent the image from being cut of
            mPickImage.setScaleType(ImageButton.ScaleType.FIT_CENTER);

            // Add the image to the container
            pickBubblesContainer.addView(mPickImage);


            // Create the text view
            TextView mPickName = new TextView(referenceActivity);
            RelativeLayout.LayoutParams pral = new RelativeLayout.LayoutParams(
                    0, RelativeLayout.LayoutParams.WRAP_CONTENT);


           int cx = (larp.leftMargin+(int)(larp.height/4.0));
              int cy = (larp.rightMargin+(int)(larp.height/4.0));

            //dit is eerste waarover ik twijfel, of dit het coordinaat is van het midden van de cirkel, kan jij op precies
            //deze x/y een puntje tekenen? Wat? In de app?
            ///ja, gewoon op deze locatie een letter of iets, om te kijken of die in het midden van de cirkels komt
            // uhh kan proberen, gwn dus int cx en int cy van jou gebruiken?
            //ja, ben benieuwd wat daaruit komt, het is OF dit, OF de radius moet nog een deler die ik over het hoofd gezien heb
            //afk 2miwna
            //wwacht\
            // ik zie miss wat eris
            // jij gebruikt coordinaten, maar de layout waarin alles gebouwd is ondersteunt geen coordinaten,

            // vandaar de margins. Hij schuift het zegmaar vanuit de linkerbovenhoek elke bubbel naar zn plek
            // Dus als int cx en cy coordinaten zijn kan het heel goed dat het puntje op een compleet random plek
            // uitkomt
            // ... dus left/right margin zijn geen posities maar afstanden relatief van elkaar?
            // yea....
            // dacht dat je dat wist my bad ;
            //( dit verklaart een hele hoop... euh.. een manier om de positie van de cirkel te krijgen/bepalen? xd
            // yea.. vandaag geprobeert, in een relative layout (die ik hier gebruik) kan dat niet
            // alleen bij een absolute layout kan dat, daar gebruik je oook coordinaten
            // MAAR, die wordt op allle fora en op de developers.android.com zelf erg afgeraden
            // en die is bovendien deprecated
            // doe toch maar even om te testen xd deze code raad ik je namelijk ook af <3
            // aight






// moet dit ff bouwen en dan addView plegen
        }
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