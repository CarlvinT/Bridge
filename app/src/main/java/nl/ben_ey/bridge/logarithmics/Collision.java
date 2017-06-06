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
        this.bubbles = bubbles; // de huidige bubbel
        this.pick_item = pick_item; // de lijst met al bestaande bubbels
    }


    public boolean checkOverlap(){

        for( Bubble b : bubbles ){
            if (pick_item == b) {
                continue;
                // okej, als het nu werkt was dat het, als het niet werkt is het het algo xd we runnen
                // bij mij staat carlvi vrij maar hier staat dat ie overlap heeft. Algo is dus kut
            }
            viewHeight = b.getHeight();
            viewWidth = b.getWidth();

            topMargin = b.getTopMargin();
            leftMargin = b.getLeftMargin();

            // mag ik ervan uit gaan dat dit algo klopt & gemeten is vanaf het midden van elke cirkel?
            // jep. Er staat diverse log.d's die in pixels hebben uitgemeten wat de grootte van elk bubbel-item
            // is. In DP werk ik niet. overigens heb ik vandaag wel een formule gevonden voor DP naar pixel
            // If pick items margin is bigger than b's margin plus half it's size
            // or smaller than b's margin minus half it's size it's overlap
            // ddddddduuuuuuuuddddeee
            //weet je wat hier staat xD?

            /// pick_item.getTopMargin() >= (topMargin + (viewHeight / 2))
            /// pick_item.getTopMargin() <= (topMargin + (viewHeight / 2))
            ///                           ^
            /// wat is hier mis? xd
            /// oh
            // OH
            /// OHHHHH!!!!

            // ZELFDE
            // je zegt hier... als kleiner, OF gelijk, OF groter, dan ...... dus het is altijd true xd ik denk dat je 1 van de 2 de bubbel bedoelt
            // dus

            // Dit was het geloof ik
            // want ik probeer zegmaar ervoor te zorgen dat ie controleert of ie binnen de range van de bubbel valt zegmaar
            // gij snap?
            if (pick_item.getTopMargin() >= (topMargin + (viewHeight / 2)) ||
                    pick_item.getTopMargin() <= (topMargin - (viewHeight / 2)) ) {
                System.err.println(pick_item.getTopMargin());
                System.err.println(topMargin + (viewHeight / 2));
                System.err.println(topMargin);
                System.err.println(viewHeight);
                return false;
            }

            // het klopt toch nog niet want carl en car staan vrij maar hij zegt nog steeds overlap
            // ik kijk ff of ik iets van borders kan fixen. uno momento

            // If pick items margin is bigger than b's margin plus half it's size
            // or smaller than b's margin minus half it's size it's overlap
            if (pick_item.getLeftMargin() >= (leftMargin + (viewWidth / 2)) ||
                    pick_item.getTopMargin() <= (topMargin - (viewHeight / 2)) ) {

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
