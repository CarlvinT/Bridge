package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.adapters.ChatListAdapter;
import nl.ben_ey.bridge.models.ChatListItem;

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatlistFragment extends Fragment {

    private ListView chatListView;
    private Activity activity;
    private View view;

    private FragmentActivity listener;


    @Override
    public void onAttach(Context context)
    {
        // Log.wtf("Ran method", "onAttach");
        super.onAttach(context);
        if (context instanceof Activity)
        {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_chatlist, container, false);


        ChatListItem chatListItemData[] = new ChatListItem[]
        {
            new ChatListItem("Global Chat", "12", "1455"),
        };

        ChatListAdapter adapter = new ChatListAdapter(activity, R.layout.listrow, chatListItemData);

        chatListView = (ListView) view.findViewById(R.id.chatList);
        chatListView.setAdapter(adapter);

        return view;
    }
}
