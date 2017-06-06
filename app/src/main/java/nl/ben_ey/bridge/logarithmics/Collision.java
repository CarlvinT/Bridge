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
    private int viewHeight;
    private int viewWidth;
    private int topMargin;
    private int leftMargin;

    public Collision(ArrayList<Bubble> bubbles, Bubble pick_item){
        this.bubbles = bubbles; // de lijst met al bestaande bubbels
        this.pick_item = pick_item; // de huidige bubbel
    }


    public boolean checkOverlap(){

        for( Bubble b : bubbles ){
            // @@@ - Conversation - @@@
            // waarom maken al die relative dingen het zo overbodig lastig... ;x?
            // omdat android kut is, net als java
            // heb geprobeert met andere layouts, dit is de enige waarin het mogelijk is,
            // om de positie te forceren, k
            // @@@ - Conversation - @@@

            // Get the margins of the next bubble in the list
            int listBblMarginTop = b.getTopMargin();
            int listBblMarginLeft = b.getLeftMargin();

            // Get the margins of the current item
            int pickItemMarginTop = pick_item.getTopMargin();
            int pickItemMarginLeft = pick_item.getLeftMargin();

            // Set the four corner coordinates of the next bubble in the list
            int bLeft = b.getLeftMargin();
            int bTop = b.getTopMargin();
            int bRight = b.getLeftMargin() + b.getWidth();
            int bBottom = b.getTopMargin() + b.getHeight();

            // Set the four corner coordinates of the current item
            int piLeft = pick_item.getLeftMargin();
            int piTop = pick_item.getTopMargin();
            int piRight = pick_item.getLeftMargin() + pick_item.getWidth();
            int piBottom = pick_item.getTopMargin() + pick_item.getHeight();

            // Now we have a rectangle of the next bubble in the list
            Rect bRect = new Rect(bLeft, bTop, bRight, bBottom);

            // Now we have a rectangle of the current item
            Rect piRect = new Rect(piLeft, piTop, piRight, piBottom);

            if (piRect.intersect(bRect)) {
                System.out.println("Warning! Intercourse");
                return false;
            }

            // @@@ - Conversation - @@@
            // als jij vind hoe je left, right enz berekent, zijn we klaar (yay)
            // wat betekent left rigth enzo hier? borders
            // @@@ - Conversation - @@@
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
