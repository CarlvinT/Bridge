package nl.ben_ey.bridge.models;

/**
 * Created by ben-e on 20-6-17.
 */

public class ChatListItem {
    private String name;
    private String distance;
    private String lastOnline;

    public ChatListItem() {
        super();
    }

    public ChatListItem(String name, String distance, String lastOnline) {
        super();
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLastOnline() {
        String output = lastOnline.replaceAll("..(?!$)", "$0:");
        return output;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }
}
