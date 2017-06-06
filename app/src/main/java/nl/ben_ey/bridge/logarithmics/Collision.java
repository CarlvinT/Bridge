package nl.ben_ey.bridge.logarithmics;

import android.graphics.Rect;

import java.util.ArrayList;

import nl.ben_ey.bridge.models.Bubble;

/**
 * Created by Ben on 6-6-2017.
 */

public class Collision {
    private ArrayList<Bubble> bubbles;
    private Bubble pick_item;

    private int piLeft;
    private int piTop;
    private int piRight;
    private int piBottom;

    private Rect piRect;

    public Collision(ArrayList<Bubble> bubbles, Bubble pick_item){
        this.bubbles = bubbles; // de lijst met al bestaande bubbels
        this.pick_item = pick_item; // de huidige bubbel

        // Set the four corner coordinates of the current item
        this.piLeft = pick_item.getLeftMargin();
        this.piTop = pick_item.getTopMargin();
        this.piRight = pick_item.getLeftMargin() + pick_item.getWidth();
        this.piBottom = pick_item.getTopMargin() + pick_item.getHeight();

        // Now we have a rectangle of the current item
        this.piRect = new Rect(piLeft, piTop, piRight, piBottom);
    }


    public boolean checkOverlap(){
        for( Bubble b : bubbles ){
            // Set the four corner coordinates of the next bubble in the list
            int bLeft = b.getLeftMargin();
            int bTop = b.getTopMargin();
            int bRight = b.getLeftMargin() + b.getWidth();
            int bBottom = b.getTopMargin() + b.getHeight();

            // Now we have a rectangle of the next bubble in the list
            Rect bRect = new Rect(bLeft, bTop, bRight, bBottom);

            // If there's an intersection this function will prevent the bubble
            // from being placed
            if (piRect.intersect(bRect)) {
                return false;
            }
        }

        // Nothing's gone wrong, green light to place the item
        return true;
    }

    public ArrayList<Bubble> getBubbles() {
        return bubbles;
    }

    public void setBubbles(ArrayList<Bubble> bubbles) {
        this.bubbles = bubbles;
    }

    public Bubble getPick_item() {
        return pick_item;
    }

    public void setPick_item(Bubble pick_item) {
        this.pick_item = pick_item;
    }
}
