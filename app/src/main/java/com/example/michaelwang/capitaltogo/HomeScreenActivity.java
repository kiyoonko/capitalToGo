package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        final Typeface custom_font_medium = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        final TextView choose = (TextView) findViewById(R.id.choose);
        final RelativeLayout checking = (RelativeLayout) findViewById(R.id.checking);
        final TextView checkingName = (TextView) findViewById(R.id.tvChecking);
        final TextView checkingNum = (TextView) findViewById(R.id.tvCheckingNum);
        final TextView checkingAmt = (TextView) findViewById(R.id.tvCheckingAmt);
        final RelativeLayout savings = (RelativeLayout) findViewById(R.id.savings);
        final TextView savingsName = (TextView) findViewById(R.id.tvSavings);
        final TextView savingsNum = (TextView) findViewById(R.id.tvSavingsNum);
        final TextView savingsAmt = (TextView) findViewById(R.id.tvSavingsAmt);
        final RelativeLayout emergency = (RelativeLayout) findViewById(R.id.emergency);
        final TextView emergencyName = (TextView) findViewById(R.id.tvEmergency);
        final TextView emergencyNum = (TextView) findViewById(R.id.tvEmergencyAmt);
        final TextView emergencyAmt = (TextView) findViewById(R.id.tvEmergencyNum);

        choose.setTypeface(custom_font_medium);
        checkingName.setTypeface(custom_font_medium);
        checkingNum.setTypeface(custom_font_medium);
        checkingAmt.setTypeface(custom_font_medium);
        savingsName.setTypeface(custom_font_medium);
        savingsNum.setTypeface(custom_font_medium);
        savingsAmt.setTypeface(custom_font_medium);
        emergencyName.setTypeface(custom_font_medium);
        emergencyNum.setTypeface(custom_font_medium);
        emergencyAmt.setTypeface(custom_font_medium);

        //get all name acc numbers and balances
        checking.setOnClickListener(new View.OnClickListener() {
            //pass the information too!
            @Override
            public void onClick(View v) {
                Intent choseBank = new Intent(HomeScreenActivity.this, RequestCashActivity.class);
                HomeScreenActivity.this.startActivity(choseBank);
            }
        });
        savings.setOnClickListener(new View.OnClickListener() {
            //pass the information too!
            @Override
            public void onClick(View v) {
                Intent choseBank = new Intent(HomeScreenActivity.this, RequestCashActivity.class);
                HomeScreenActivity.this.startActivity(choseBank);
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            //pass the information too!
            @Override
            public void onClick(View v) {
                Intent choseBank = new Intent(HomeScreenActivity.this, RequestCashActivity.class);
                HomeScreenActivity.this.startActivity(choseBank);
            }
        });
    }
}
