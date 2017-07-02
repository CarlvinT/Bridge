package nl.ben_ey.bridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import nl.ben_ey.bridge.adapters.ChatAdapter;
import nl.ben_ey.bridge.models.ChatMessage;
import nl.ben_ey.bridge.models.CommonMethods;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText msg_input;
    private ImageButton msg_send;
    private TextView userName;
    private TextView userDistance;
    private final String userOne = "Yo money", userTwo = "Swalala";
    private Toolbar navBlock;
    private Random random;
    private static ArrayList<ChatMessage> chatList;
    private static ChatAdapter chatAdapter;
    private ListView msgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        random = new Random();
        msg_input = (EditText) findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) findViewById(R.id.send_button);
        msgListView = (ListView) findViewById(R.id.chat_msg_list);
        navBlock = (Toolbar) findViewById(R.id.toolbar);

        // Setup the back button on top of the UI to bring the user
        // back when they click on it
        navBlock.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Fill the username and distance with the data received from the intent
        Intent intent = getIntent();
        userName = (TextView) findViewById(R.id.chat_user_name);
        userName.setText(intent.getStringExtra("user_name"));

        userDistance = (TextView) findViewById(R.id.chat_user_distance);
        final String userDistanceString = intent.getStringExtra("user_distance") + " " +
                getResources().getString(R.string.chatUserDistance);

        userDistance.setText(userDistanceString);

        // Set an onClickListener on the message send button. Because we're
        // implementing the OnClickListener interface we can just type 'this' here
        msg_send.setOnClickListener(this);

        // Make sure a new item that gets added to the message listview always
        // starts on the bottom and works it's way to the top
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        // Create a new arrayList to be filled with the ChatMessage object
        chatList = new ArrayList<ChatMessage>();

        chatList.add(new ChatMessage(userOne, userTwo, "She said", "" + random.nextInt(1000), true));
        chatList.add(new ChatMessage(userOne, userTwo, "Boii", "" + random.nextInt(1000), true));
        chatList.add(new ChatMessage(userOne, userTwo, "Let's not talk too much", "" + random.nextInt(1000), false));
        chatList.add(new ChatMessage(userOne, userTwo, "Grab on my waist", "" + random.nextInt(1000), false));
        chatList.add(new ChatMessage(userOne, userTwo, "and", "" + random.nextInt(1000), false));
        chatList.add(new ChatMessage(userOne, userTwo, "Move that body like this", "" + random.nextInt(1000), true));

        // Create new instance of the ChatAdapter class
        chatAdapter = new ChatAdapter(this, chatList);

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
            }
        });
    }

    // This function
    public void sendTextMessage(View v) {
        String message = msg_input.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage(userOne, userTwo, message,
                    "" + random.nextInt(1000), false);
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
