package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton bLogin = (ImageButton) findViewById(R.id.bLogin);
        ImageButton bRegister = (ImageButton) findViewById(R.id.bRegister);
        Button bRoot = (Button) findViewById(R.id.bRoot);
        final TextView tvSlogan = (TextView) findViewById(R.id.tvSlogan);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Book.ttf");

        tvSlogan.setTypeface(custom_font);


        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);

                MainActivity.this.startActivity(loginIntent);
            }
        });

        bRoot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent rootIntent = new Intent(MainActivity.this, HomeScreenActivity.class);

                MainActivity.this.startActivity(rootIntent);
            }
        });
    }
}
