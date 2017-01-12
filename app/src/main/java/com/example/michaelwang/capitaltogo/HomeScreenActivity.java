package com.example.michaelwang.capitaltogo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        final TextView tvHomeScreen = (TextView) findViewById(R.id.tvHomeScreen);
        final ImageView ivQRCode = (ImageView) findViewById(R.id.ivQRCode);
        Button bGetCode = (Button) findViewById(R.id.bGetCode);

        bGetCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(HomeScreenActivity.this);
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
                                Log.d("HomeScreenActivity.java", base64code);
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
        });
//
//        URL url = null;
//        try {
//            url = new URL("http://jared-api-dev.us-east-1.elasticbeanstalk.com/api/testqr");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        InputStream content = null;
//        try {
//            content = (InputStream) url.getContent();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Drawable d = Drawable.createFromStream(content , "src");
//        iv.setImageDrawable(d);
    }
}
