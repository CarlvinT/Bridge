package nl.ben_ey.bridge.logarithmics;

import java.util.ArrayList;

import nl.ben_ey.bridge.models.Bubble;

/**
 * Created by Ben on 6-6-2017.
 */

public class Collision {
    private ArrayList<Bubble> bubbles;
    private Bubble pick_item;
    private int viewHeight;
    private int viewWidth;
    private int topMargin;
    private int leftMargin;

    public Collision(ArrayList<Bubble> bubbles, Bubble pick_item){
        this.bubbles = bubbles;
        this.pick_item = pick_item;
    }


    public boolean checkOverlap(){

        for( Bubble b : bubbles ){
            viewHeight = b.getHeight();
            viewWidth = b.getWidth();

            topMargin = b.getTopMargin();
            leftMargin = b.getLeftMargin();

            // If pick items margin is bigger than b's margin plus half it's size
            // or smaller than b's margin minus half it's size it's overlap
            if (pick_item.getTopMargin() >= (topMargin + (viewHeight / 2)) ||
                    pick_item.getTopMargin() <= (topMargin + (viewHeight / 2)) ) {

                return false;
            }

            // If pick items margin is bigger than b's margin plus half it's size
            // or smaller than b's margin minus half it's size it's overlap
            if (pick_item.getLeftMargin() >= (leftMargin + (viewWidth / 2)) ||
                    pick_item.getTopMargin() <= (topMargin + (viewHeight / 2)) ) {

                return false;
            }

        }

        return true;
    }




    public ArrayList<Bubble> getBubbles() {
        return bubbles;
    }

    public void setBubbles(ArrayList<Bubble> bubbles) {
        this.bubbles = bubbles;
    }

}
