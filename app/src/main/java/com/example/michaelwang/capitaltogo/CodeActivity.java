package com.example.michaelwang.capitaltogo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        final ImageView ivQRCode = (ImageView) findViewById(R.id.ivQRCode);
        RequestQueue queue = Volley.newRequestQueue(CodeActivity.this);
        String url = "http://c2go-api-dev.us-east-1.elasticbeanstalk.com/api/testqr";

        JsonRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String base64code = null;
                        try {
                            base64code = response.getString("QR");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("CodeActivity.java", base64code);
                        byte[] decodedString = Base64.decode(base64code, Base64.NO_PADDING);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        ivQRCode.setImageBitmap(decodedByte);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("HomeActivity", "Error on response");
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }
}
