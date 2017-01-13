package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TransactionSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);
        final Typeface custom_font_medium = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        final Typeface custom_font_roman = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Roman.ttf");
        final Typeface custom_font_bold = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Heavy.ttf");
        final TextView thank = (TextView) findViewById(R.id.thank);
        final TextView account = (TextView) findViewById(R.id.account);
        final TextView balance = (TextView) findViewById(R.id.balance);
        final ImageButton bAccount = (ImageButton) findViewById(R.id.bAccount);
        thank.setTypeface(custom_font_medium);
        account.setTypeface(custom_font_roman);
        balance.setTypeface(custom_font_bold);
        //get balance and replace text in balance
        bAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeScreenIntent = new Intent(TransactionSuccessActivity.this, HomeScreenActivity.class);
                TransactionSuccessActivity.this.startActivity(homeScreenIntent);
            }
        });
    }
}
