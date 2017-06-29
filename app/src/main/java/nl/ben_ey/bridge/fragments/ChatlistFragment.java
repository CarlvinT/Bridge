package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.models.ChatListAdapter;
import nl.ben_ey.bridge.models.ChatListItem;

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatlistFragment extends Fragment {

    private ListView chatListView;

    private Activity activity;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState){

        //TODO: Create 9patch for message input so to scale that
        //TODO: Create 9patch for chat bubbles
        //TODO: Create chat bubbles system using this tutorial: http://www.tutorialsface.com/2015/08/building-your-own-android-chat-messenger-app-similar-to-whatsapp-using-xmpp-smack-4-1-api-from-scratch-part-1/

        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_chatlist, container, false);


        ChatListItem chatListItemData[] = new ChatListItem[]
        {
            new ChatListItem("Johann", "12", "1455"),
            new ChatListItem("Boris", "4", "1911"),
            new ChatListItem("Katy", "6", "2122"),
            new ChatListItem("Lida", "1", "1242"),
            new ChatListItem("James", "11", "1831"),
            new ChatListItem("Jenny", "22", "1842"),
        };

        ChatListAdapter adapter = new ChatListAdapter(activity, R.layout.listrow, chatListItemData);

        chatListView = (ListView) view.findViewById(R.id.chatList);

        chatListView.setAdapter(adapter);

        return view;
    }
}
