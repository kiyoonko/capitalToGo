package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final ImageButton bCancel = (ImageButton) findViewById(R.id.bCancel);
        final ImageButton bContinue = (ImageButton) findViewById(R.id.bContinue);

        final TextView credentials = (TextView) findViewById(R.id.credentials);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/AvenirNextCondensed-Medium.ttf");
        credentials.setTypeface(custom_font);

        bCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(cancelIntent);
            }
        });

        bContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent nextIntent = new Intent(RegisterActivity.this, RegisterPrintActivity.class);
                RegisterActivity.this.startActivity(nextIntent);
            }
        });
    }
}
