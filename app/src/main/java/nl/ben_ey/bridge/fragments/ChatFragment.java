package nl.ben_ey.bridge.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.ben_ey.bridge.R;

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        //TODO: Create chat bubbles system using this tutorial: http://www.tutorialsface.com/2015/08/building-your-own-android-chat-messenger-app-similar-to-whatsapp-using-xmpp-smack-4-1-api-from-scratch-part-1/
        //TODO: When you click on the input message bar the soft keyboard pushes away the top part of the UI. Keep that!



        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}
