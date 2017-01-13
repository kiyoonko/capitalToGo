package com.example.michaelwang.capitaltogo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        RequestQueue queue = Volley.newRequestQueue(HomeScreenActivity.this);
        String url = "http://api.reimaginebanking.com/customers/58788eb81756fc834d8eb492/accounts?key=80ad40cebb5f5f11cf1cc45d39a1eb1e";

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        String checkingAcctName;
                        String checkingAcctAmt;
                        String savingsAcctName;
                        String savingsAcctAmt;
                        String emergencyAcctName;
                        String emergencyAcctAmt;
                        try {
                            checkingAcctName = response.getJSONObject(0).getString("nickname");
                            checkingAcctAmt = response.getJSONObject(0).getString("balance");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            savingsAcctName = response.getJSONObject(1).getString("nickname");
                            savingsAcctAmt = response.getJSONObject(0).getString("balance");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            emergencyAcctName = response.getJSONObject(2).getString("nickname");
                            emergencyAcctAmt = response.getJSONObject(2).getString("balance");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("HomeScreenActivity", response.toString());

                        
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        queue.add(jsObjRequest);

    }
}
