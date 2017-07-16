package nl.ben_ey.bridge.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nl.ben_ey.bridge.ChatActivity;
import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.models.ChatListItem;

/**
 * Created by Ben on 22/06/2017.
 */

public class ChatListAdapter extends ArrayAdapter<ChatListItem> {
    private Context context;
    private int layoutResourceId;
    private ChatListItem data[] = null;
    private String userName;
    private String userDistance;
    private FirebaseAuth mAuth;

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

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ChatHolder();
            holder.userName = (TextView) row.findViewById(R.id.user_name);
            holder.userDistance = (TextView) row.findViewById(R.id.user_distance);
            holder.userLastOnline = (TextView) row.findViewById(R.id.last_online_time);

            row.setTag(holder);

        } else {
            holder = (ChatHolder) row.getTag();
        }

        final ChatListItem chatListItem = data[position];
        String userDistanceString = chatListItem.getDistance() + " " +
                context.getResources().getString(R.string.chatUserDistance);

        holder.userName.setText(chatListItem.getName());
        holder.userDistance.setText(userDistanceString);
        holder.userLastOnline.setText(chatListItem.getLastOnline());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChatActivity.class);

                userName = chatListItem.getName();
                userDistance = chatListItem.getDistance();

                i.putExtra("user_name", userName);
                i.putExtra("user_distance", userDistance);

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser fBuser = mAuth.getCurrentUser();

                final String FBUSER_UID = fBuser.getUid();
                final String FBUSER_EMAIL = fBuser.getEmail();

                i.putExtra("user_id", FBUSER_UID);
                i.putExtra("user_email", FBUSER_EMAIL);

                context.startActivity(i);
            }
        });

        return row;
    }

    private static class ChatHolder
    {
        TextView userName;
        TextView userDistance;
        TextView userLastOnline;
    }

}
