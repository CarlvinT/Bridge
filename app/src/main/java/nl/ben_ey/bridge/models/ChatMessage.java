package nl.ben_ey.bridge.models;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Ben-e on 30-6-2017.
 */

public class ChatMessage {
    public String body, sender, receiver, senderName;
    public String Date, Time;
    public String msgid;
    public boolean isMine;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
