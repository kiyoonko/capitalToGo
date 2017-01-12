package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
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

        final TextView tvPrompt = (TextView) findViewById(R.id.tvPrompt);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/AvenirNextCondensed-Medium.ttf");
        tvPrompt.setTypeface(custom_font);

        bCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(cancelIntent);
            }
        });
    }
}
