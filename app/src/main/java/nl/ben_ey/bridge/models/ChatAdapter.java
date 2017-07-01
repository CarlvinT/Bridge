package nl.ben_ey.bridge.models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nl.ben_ey.bridge.R;

/**
 * Created by Ben-e on 30-6-2017.
 */

public class ChatAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList<ChatMessage> chatMessageList;

    public ChatAdapter(Activity activity, ArrayList<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = (ChatMessage) chatMessageList.get(position);
        View view = convertView;

        if (convertView == null) {
            view = inflater.inflate(R.layout.chat_bubble_user, null);
        }

        TextView msg = (TextView) view.findViewById(R.id.user_bubble);
        msg.setText(message.body);

        LinearLayout bubbleLayout =
                (LinearLayout) view.findViewById(R.id.user_container);

        LinearLayout bubbleLayoutContainer =
                (LinearLayout) view.findViewById(R.id.chat_user_container);

        if (message.isMine) {
            bubbleLayout.setBackgroundResource(R.drawable.chat_bubble_user);
            bubbleLayoutContainer.setGravity(Gravity.RIGHT);
            msg.setTextColor(Color.BLACK);
        } else {
            bubbleLayout.setBackgroundResource(R.drawable.chat_bubble_stranger);
            bubbleLayoutContainer.setGravity(Gravity.LEFT);
            msg.setTextColor(Color.WHITE);
        }

        return view;
    }

    public void add(ChatMessage chatMessage) {
        chatMessageList.add(chatMessage);
    }
}
