package com.example.michaelwang.capitaltogo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CodeActivity extends AppCompatActivity {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        final String amount = extras.getString("amount");
        verifyStoragePermissions(this);


        // LOL
        //if (android.os.Build.VERSION.SDK_INT > 9) {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        //String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        //final TextView tvHomeScreen = (TextView) findViewById(R.id.tvHomeScreen);
        final ImageView ivQRCode = (ImageView) findViewById(R.id.ivQRCode);
		Button bCancel = (Button) findViewById(R.id.bCancel);
        TextView ATM = (TextView) findViewById(R.id.ATM);
        final TextView moneyText = (TextView)findViewById(R.id.moneyText);
        moneyText.setText("Your $"+amount+" is ready for pick-up");


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(CodeActivity.this);
        String url = "http://c2go-api-dev.us-east-1.elasticbeanstalk.com/api/qr";

        HashMap<String, String> postMessage = new HashMap<String, String>();
        postMessage.put("aid", getIntent().getExtras().getString("aid"));
        postMessage.put("amount", getIntent().getExtras().getString("amount"));

        JSONObject jsonBody = new JSONObject(postMessage);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (url, jsonBody, new Response.Listener<JSONObject>() {

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
                        // TODO Auto-generated method stub

                    }
                });
        queue.add(jsObjRequest);


        bCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeScreenIntent = new Intent(CodeActivity.this, TransactionSuccessActivity.class);
                CodeActivity.this.startActivity(homeScreenIntent);
            }
        });

        final Typeface custom_font_medium = Typeface.createFromAsset(getAssets(),  "fonts/Avenir-Medium.ttf");
        final Typeface custom_font_bold = Typeface.createFromAsset(getAssets(),  "fonts/AvenirNextCondensed-DemiBold.ttf");
        bCancel.setTypeface(custom_font_bold);
        ATM.setTypeface(custom_font_medium);
        moneyText.setTypeface(custom_font_medium);
    }
}
