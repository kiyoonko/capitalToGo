package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class RequestCashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_cash);

        Typeface medium =  Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        Typeface condensed =  Typeface.createFromAsset(getAssets(),  "fonts/AvenirNextCondensed-DemiBold.ttf");

        final TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        final TextView tvDollarSign = (TextView) findViewById(R.id.tvDollar);
        final EditText etAmount = (EditText) findViewById(R.id.etAmount);
        final Button bOne = (Button) findViewById(R.id.bOne);
        final Button bFive = (Button) findViewById(R.id.bFive);
        final Button bTen = (Button) findViewById(R.id.bTen);
        final Button bTwenty = (Button) findViewById(R.id.bTwenty);
        final Button bFifty = (Button) findViewById(R.id.bFifty);
        final Button bHundred = (Button) findViewById(R.id.bHundred);
        final Button bSubmit = (Button) findViewById(R.id.bSubmit);
        final ImageButton bTalk = (ImageButton) findViewById(R.id.bTalk);

        tvAmount.setTypeface(medium);
        tvDollarSign.setTypeface(medium);
        etAmount.setTypeface(medium);
        bOne.setTypeface(condensed);
        bFive.setTypeface(condensed);
        bTen.setTypeface(condensed);
        bTwenty.setTypeface(condensed);
        bFifty.setTypeface(condensed);
        bHundred.setTypeface(condensed);

        bOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("1");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 1));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });
        bFive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("5");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 5));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });
        bTen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("10");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 10));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });
        bTwenty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("20");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 20));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });
        bFifty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("50");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 50));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });
        bHundred.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    etAmount.setText("100");
                }
                else {
                    etAmount.setText("" + (Integer.parseInt(etAmount.getText().toString()) + 100));
                }
                etAmount.setSelection(etAmount.getText().toString().length());
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!bSubmit.getText().toString().equals("Get $0")) {
                    Intent submitIntent = new Intent(RequestCashActivity.this, CodeActivity.class);
                    submitIntent.putExtra("amount", etAmount.getText().toString());
                    RequestCashActivity.this.startActivity(submitIntent);
                }
            }
        });

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    bSubmit.setText("Get $0");
                }
                else {
                    bSubmit.setText("Get $"+etAmount.getText().toString());
                }

                if(TextUtils.isEmpty(etAmount.getText().toString())){
                    bSubmit.setBackgroundColor(Color.parseColor("#A1A1A1"));
                    bSubmit.setTextColor(Color.parseColor("#D1C9C9"));
                }
                else if(Integer.parseInt(etAmount.getText().toString())>0){
                    bSubmit.setBackgroundColor(Color.parseColor("#509FF7"));
                    bSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else{
                    bSubmit.setBackgroundColor(Color.parseColor("#A1A1A1"));
                    bSubmit.setTextColor(Color.parseColor("#D1C9C9"));
                }
            }
        });

    }
}
