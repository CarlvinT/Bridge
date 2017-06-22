package nl.ben_ey.bridge.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import nl.ben_ey.bridge.R;

/**
 * Created by Ben on 22/06/2017.
 */

public class ChatListAdapter extends ArrayAdapter<ChatListItem> {
    private Context context;
    private int layoutResourceId;
    private ChatListItem data[] = null;

    public ChatListAdapter(Context context, int layoutResourceId, ChatListItem data[]) {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ChatHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ChatHolder();
            holder.userName = (TextView) row.findViewById(R.id.userName);
            holder.userDistance = (TextView) row.findViewById(R.id.userDistance);
            holder.userLastOnline = (TextView) row.findViewById(R.id.lastOnlineTime);

            row.setTag(holder);

        }
        else
        {
            holder = (ChatHolder) row.getTag();

        }

        ChatListItem chatListItem = data[position];
        holder.userName.setText(chatListItem.getName());
        holder.userDistance.setText(chatListItem.getDistance());
        holder.userLastOnline.setText(chatListItem.getLastOnline());

        return row;

    }


    static class ChatHolder
    {
        TextView userName;
        TextView userDistance;
        TextView userLastOnline;
    }

}
