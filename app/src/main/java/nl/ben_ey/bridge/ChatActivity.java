package nl.ben_ey.bridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import nl.ben_ey.bridge.adapters.ChatAdapter;
import nl.ben_ey.bridge.models.ChatMessage;
import nl.ben_ey.bridge.models.CommonMethods;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private static ArrayList<ChatMessage> chatList;
    private static ChatAdapter chatAdapter;
    private final String userOne = "Yo money";
    private final String userTwo = "Swalala";
    private EditText msg_input;
    private ImageButton msg_send;
    private TextView userName;
    private TextView userDistance;
    private Toolbar navBlock;
    private Random random;
    private ListView msgListView;
    private DatabaseReference mDatabaseMessages;
    private DatabaseReference mDatabaseSenders;
    private DatabaseReference mDatabaseFlex;
    private String fBuserId;
    private String fBuserEmail;
    private FirebaseAuth mAuth;
    private String uEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        random = new Random();
        msg_input = (EditText) findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) findViewById(R.id.send_button);
        msgListView = (ListView) findViewById(R.id.chat_msg_list);
        navBlock = (Toolbar) findViewById(R.id.toolbar);

        mDatabaseMessages = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Messages")
                .child("Message");

        mAuth = FirebaseAuth
                .getInstance();

        mDatabaseSenders = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Messages")
                .child("Sender");

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


        // Get FireBase user id and email from the Intent
        fBuserId = intent.getStringExtra("user_id");
        fBuserEmail = intent.getStringExtra("user_email");

        // Toast.makeText(this, "User ID: " + fBuserId + " | User Email: " + fBuserEmail, Toast.LENGTH_LONG).show();

        // Set an onClickListener on the message send button. Because we're
        // implementing the OnClickListener interface we can just type 'this' here
        msg_send.setOnClickListener(this);

        // Make sure a new item that gets added to the message listview always
        // starts on the bottom and works it's way to the top
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        // Create a new arrayList to be filled with the ChatMessage object
        chatList = new ArrayList<ChatMessage>();

//        chatList.add(new ChatMessage(userOne, userTwo, "She said", "" + random.nextInt(1000), true));
//        chatList.add(new ChatMessage(userOne, userTwo, "Boii", "" + random.nextInt(1000), true));
//        chatList.add(new ChatMessage(userOne, userTwo, "Let's not talk too much", "" + random.nextInt(1000), false));
//        chatList.add(new ChatMessage(userOne, userTwo, "Grab on my waist", "" + random.nextInt(1000), false));
//        chatList.add(new ChatMessage(userOne, userTwo, "and", "" + random.nextInt(1000), false));
//        chatList.add(new ChatMessage(userOne, userTwo, "Move that body like this", "" + random.nextInt(1000), true));

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


        final ArrayList<String> msgBlocks = new ArrayList<>();
        final ArrayList<String> senderBlocks = new ArrayList<>();



        mDatabaseMessages.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue().toString();

                final ChatMessage chatMessage = new ChatMessage(userOne, userTwo, message,
                        "" + random.nextInt(1000), true);
                chatMessage.setMsgID();
                chatMessage.setBody(message);
                chatMessage.setDate(CommonMethods.getCurrentDate());
                chatMessage.setTime(CommonMethods.getCurrentTime());
                chatAdapter.add(chatMessage);
                chatAdapter.notifyDataSetChanged();
                //msgBlocks.add(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*mDatabaseSenders.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String sender = dataSnapshot.getValue().toString();
                senderBlocks.add(sender);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*int count = 0;
        for(String msg : msgBlocks) {
            System.out.println(senderBlocks.get(count) + " sent the following message: " + msg);
            count++;
        }

        System.out.println("done");*/




    }

    // This function will format a ChatMessage object and add it to the adapter
    public void sendTextMessage(View v) {
        FirebaseUser user = mAuth.getCurrentUser();
        uEmail = user.getEmail();

//        String message = msg_input.getEditableText().toString();
//        if (!message.equalsIgnoreCase("")) {
//            final ChatMessage chatMessage = new ChatMessage(userOne, userTwo, message,
//                    "" + random.nextInt(1000), true);
//            chatMessage.setMsgID();
//            chatMessage.setBody(message);
//            chatMessage.setDate(CommonMethods.getCurrentDate());
//            chatMessage.setTime(CommonMethods.getCurrentTime());
//            msg_input.setText("");
//            chatAdapter.add(chatMessage);
//            chatAdapter.notifyDataSetChanged();
//
//            chatList.add(new ChatMessage(userOne, userTwo, message, "" + random.nextInt(1000), true));
//        }

        String message = uEmail + ": " + msg_input.getText().toString().trim();
        mDatabaseFlex = FirebaseDatabase.getInstance().getReference().child("Messages").child("Message");
        mDatabaseFlex.push().setValue(message);
        msg_input.setText("");
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
