package nl.ben_ey.bridge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Intent i;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.name_input);
        mPassword = (EditText) findViewById(R.id.password_input);

        i = new Intent(this, MainActivity.class);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();

        if (user == null) {
            Toast.makeText(LoginActivity.this, "not signed in",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "signed in",
                    Toast.LENGTH_LONG).show();

            registerSignIn();
            startActivity(i);
        }
    }

    public void loginUser(View view) {


        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this, "Input Email and password", Toast.LENGTH_LONG).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                // getUserInformation();
                                Toast.makeText(LoginActivity.this, "Welcome login succesfull ",
                                        Toast.LENGTH_SHORT).show();

                                registerSignIn();
                                startActivity(i);

                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }

    public void registerSignIn()
    {

    }

}
