package nl.ben_ey.bridge.models;

/**
 * Created by ben-e on 20-6-17.
 */

public class ChatListITem {
    private String name;
    private int distance;
    private int lastOnline;

    public ChatListITem(String name, int distance, int lastOnline) {
        this.name = name;
        this.distance = distance;
        this.lastOnline = lastOnline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(int lastOnline) {
        this.lastOnline = lastOnline;
    }
}
