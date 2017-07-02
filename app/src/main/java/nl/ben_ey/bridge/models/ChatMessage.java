package nl.ben_ey.bridge.models;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Ben-e on 30-6-2017.
 */

public class ChatMessage {
    public boolean isMine;
    private String body, sender, receiver, senderName;
    private String Date, Time;
    private String msgid;

    public ChatMessage(String sender, String receiver, String messageString,
            String ID, boolean isMine) {

        this.body = messageString;
        this.isMine = isMine;
        this.sender = sender;
        this.msgid = ID;
        this.receiver = receiver;
        this.senderName = sender;
    }

    public void setMsgID() {
        msgid += "-" + String.format(Locale.getDefault(), "%02d", new Random().nextInt(100));
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTime(String time) {
        Time = time;
    }
}
