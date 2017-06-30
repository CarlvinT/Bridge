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

import nl.ben_ey.bridge.R;

/**
 * Created by Ben on 1-6-2017.
 */

public class ChatFragment extends Fragment {

    private Context context;
    private Activity activity;

    private EditText msg_input;
    private ImageButton msg_send;


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




        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        msg_input = (EditText) activity.findViewById(R.id.chat_message_input);
        msg_send = (ImageButton) activity.findViewById(R.id.send_button);

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

}
