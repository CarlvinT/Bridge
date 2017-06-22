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

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatlistFragment extends Fragment {

    private ListView chatListView;
    private ArrayAdapter<String> listAdapter;

    private Activity activity;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState){

        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_chatlist, container, false);


        chatListView = (ListView) view.findViewById(R.id.chatList);




        return view;
    }
}
