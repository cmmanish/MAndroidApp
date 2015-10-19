package com.mandroid.MAndroidApp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";
    public final static String MESSAGE = "MESSAGE";
    private ImageView image;
    private ProgressDialog mProgressDialog;
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        //        image = (ImageView) findViewById(R.id.mainImageView);
        //        image.setImageResource(R.drawable.piratey);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void searchText(View view) {
        try {
            EditText editText = (EditText) findViewById(R.id.edit_message);
            String message = editText.getText().toString();

            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Search " + message);
            mProgressDialog.setMessage("Searching...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            message = seachGoogle(message);
            Intent intent = new Intent(this, DisplayMessageActivity.class);

            intent.putExtra(MESSAGE, message);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String seachGoogle(String query) throws Exception {

        String url = "http://www.google.com/search?q=" + query;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
        return result.toString();
    }

}