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
        this.bubbles = bubbles; // de huidige bubbel
        this.pick_item = pick_item; // de lijst met al bestaande bubbels
    }


    public boolean checkOverlap(){

        for( Bubble b : bubbles ){
            //waarom maken al die relative dingen het zo overbodig lastig... ;x?
            // omdat android kut is, net als java
            // heb geprobeert met andere layouts, dit is de enige waarin het mogelijk is,
            // om de positie te forceren, k

            b.getlPars().get

            //als jij vind hoe je left, right enz berekent, zijn we klaar (yay)
            // wat betekent left rigth enzo hier? borders
            Rect R1=new Rect(b.getLeft(), b.getTop(), b.getRight(), b.getBottom());
            Rect R2=new Rect(pick_item.getLeft(), pick_item.getTop(), pick_item.getRight(), pick_item.getBottom());
            if (R1.intersect(R2)) {
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
