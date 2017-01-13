package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    public final static String TAG = "LoginActivity.java";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(null);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivity.java", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LoginActivity.java", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        final ImageButton bLogin = (ImageButton) findViewById(R.id.bLogin);
        final ImageButton bCancel = (ImageButton) findViewById(R.id.bCancel);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        Typeface custom_font_medium = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Book.ttf");

        final TextView welcome = (TextView) findViewById(R.id.welcome);
        final TextView block = (TextView) findViewById(R.id.block);
        etEmail.setTypeface(custom_font);
        etPassword.setTypeface(custom_font);
        welcome.setTypeface(custom_font_medium);
        block.setTypeface(custom_font);

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent homeScreenIntent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                                    LoginActivity.this.startActivity(homeScreenIntent);
                                }

                                // ...
                            }
                        });
            }
        });


        bCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //make sure to change this back
                //Intent cancelIntent = new Intent(LoginActivity.this, MainActivity.class);
                Intent cancelIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(cancelIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
