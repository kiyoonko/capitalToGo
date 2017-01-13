package com.example.michaelwang.capitaltogo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        Button bGetCode = (Button) findViewById(R.id.bGetCode);

        bGetCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(CodeActivity.this);
                String url = "http://c2go-api-dev.us-east-1.elasticbeanstalk.com/api/qr";


                //.addBinaryBody("recording", new File("sdcard/Download/test1.wav"))

                // i'll fix the Flask API later so I can just do addTextBody(key, value)
                HttpEntity entity = MultipartEntityBuilder.create()
                        .addTextBody("uid", "5877db6c1756fc834d8e9346")
                        .addTextBody("amount", "45") // 45 needs to be passed from the recording screen
                        .build();

                HttpPost request = new HttpPost(url);
                request.setEntity(entity);

                HttpClient client = new DefaultHttpClient();
                try {
                    HttpResponse response = client.execute(request);
                    JSONObject data = new JSONObject(EntityUtils.toString(response.getEntity()));
                    String base64code = data.getString("QR");
                    Log.d("HomeScreenActivity.java", base64code);
                    //Log.d("HomeScreenActivity.java", EntityUtils.toString(response.getEntity()));
                    byte[] decodedString = Base64.decode(base64code, Base64.NO_PADDING);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    ivQRCode.setImageBitmap(decodedByte);
                } catch (IOException|JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
