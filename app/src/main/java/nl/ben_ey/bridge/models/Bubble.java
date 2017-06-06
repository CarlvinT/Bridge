package nl.ben_ey.bridge.models;

/**
 * Created by Ben on 6-6-2017.
 */

public class Bubble {
    private int topMargin;
    private int leftMargin;
    private int width;
    private int height;
    private String nameApplied;

    public Bubble(int topMargin, int leftMargin, int width, int height, String nameApplied){
        this.topMargin = topMargin;
        this.leftMargin = leftMargin;
        this.width = width;
        this.height = height;

        this.nameApplied = nameApplied;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public String getNameApplied() {
        return nameApplied;
    }

    public void setNameApplied(String nameApplied) {
        this.nameApplied = nameApplied;
    }
}
