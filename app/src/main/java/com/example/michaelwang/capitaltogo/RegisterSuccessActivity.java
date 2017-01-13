package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        final TextView success = (TextView) findViewById(R.id.success);
        final TextView print = (TextView) findViewById(R.id.print);
        final ImageButton bLogin = (ImageButton) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successIntent = new Intent(RegisterSuccessActivity.this, LoginActivity.class);
                RegisterSuccessActivity.this.startActivity(successIntent);
            }
        });

        Typeface custom_font_condense = Typeface.createFromAsset(getAssets(),  "fonts/AvenirNextCondensed-DemiBold.ttf");
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        success.setTypeface(custom_font_condense);
    }
}
