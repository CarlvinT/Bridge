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

    private Context context;
    private Activity activity;

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
            this.context = (FragmentActivity) context;
        }
    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        //TODO: Create chat bubbles system using this tutorial: http://www.tutorialsface.com/2015/08/building-your-own-android-chat-messenger-app-similar-to-whatsapp-using-xmpp-smack-4-1-api-from-scratch-part-1/
        //TODO: When you click on the input message bar the soft keyboard pushes away the top part of the UI. Keep that!

        activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        return view;
    }


    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        msg_input = (EditText) activity.findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) activity.findViewById(R.id.send_button);

        random = new Random();
        msg_input = (EditText) activity.findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) activity.findViewById(R.id.send_button);
        msgListView = (ListView) activity.findViewById(R.id.chat_msg_list);

        msg_send.setOnClickListener(this);
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        chatList = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(activity, chatList);
        msgListView.setAdapter(chatAdapter);

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
        this.context = null;
    }


    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) { }


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

        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.send_button : sendTextMessage(v);
        }
    }

}
