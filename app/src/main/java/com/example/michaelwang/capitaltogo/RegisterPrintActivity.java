package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_print);

        final Button moveOn = (Button) findViewById(R.id.deleteLater);
        final TextView printPrompt = (TextView) findViewById(R.id.printPrompt);

        moveOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successIntent = new Intent(RegisterPrintActivity.this, RegisterSuccessActivity.class);
                RegisterPrintActivity.this.startActivity(successIntent);
            }
        });


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        printPrompt.setTypeface(custom_font);

    }
}
