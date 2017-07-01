package nl.ben_ey.bridge.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import nl.ben_ey.bridge.R;
import nl.ben_ey.bridge.models.ChatAdapter;
import nl.ben_ey.bridge.models.ChatMessage;
import nl.ben_ey.bridge.models.CommonMethods;

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatFragment extends Fragment implements View.OnClickListener {

    private FragmentActivity listener;

    private EditText msg_input;
    private ImageButton msg_send;
    private String userOne = "Yo money", userTwo = "Swalala";
    private Random random;
    private static ArrayList<ChatMessage> chatList;
    private static ChatAdapter chatAdapter;
    private ListView msgListView;


    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        //TODO: Create chat bubbles system using this tutorial: http://www.tutorialsface.com/2015/08/building-your-own-android-chat-messenger-app-similar-to-whatsapp-using-xmpp-smack-4-1-api-from-scratch-part-1/
        //TODO: When you click on the input message bar the soft keyboard pushes away the top part of the UI. Keep that!


        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        random = new Random();
        msg_input = (EditText) listener.findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) listener.findViewById(R.id.send_button);
        msgListView = (ListView) listener.findViewById(R.id.chat_msg_list);

        // Set an onClickListener on the message send button. Because we're
        // implementing the OnClickListener interface we can just type 'this' here
        msg_send.setOnClickListener(this);

        // Make sure a new item that gets added to the message listview always
        // starts on the bottom and works it's way to the top
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        // Create a new arrayList to be filled with the ChatMessage object
        chatList = new ArrayList<ChatMessage>();

        // Create new instance of the ChatAdapter class
        chatAdapter = new ChatAdapter(listener, chatList);

        // Apply the custom ChatAdapter class to the listview that will house
        // the messages
        msgListView.setAdapter(chatAdapter);

        // Get the measured initial height of the EditText for message typing
        // and copy that height to the message sending button
        final ViewTreeObserver observer = msg_input.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                msg_send.requestLayout();
                msg_send.getLayoutParams().height = msg_input.getHeight();
                observer.removeGlobalOnLayoutListener(this);
            }
        });
    }


    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }


    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    // This function
    public void sendTextMessage(View v) {
        String message = msg_input.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage(userOne, userTwo, message,
                    "" + random.nextInt(1000), true);
            chatMessage.setMsgID();
            chatMessage.setBody(message);
            chatMessage.setDate(CommonMethods.getCurrentDate());
            chatMessage.setTime(CommonMethods.getCurrentTime());
            msg_input.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();

        }
    }


    // When a click event is received this function will check if the view that
    // fired the event is in fact the send button. If it is the sendTextMessage
    // function will be called
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.send_button : sendTextMessage(v);
        }
    }

}
