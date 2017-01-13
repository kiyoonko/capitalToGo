package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RequestCashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_cash);

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
        final Button bTalk = (Button) findViewById(R.id.bTalk);

        bSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginIntent = new Intent(RequestCashActivity.this, CodeActivity.class);

                RequestCashActivity.this.startActivity(loginIntent);
            }
        });
    }
}
