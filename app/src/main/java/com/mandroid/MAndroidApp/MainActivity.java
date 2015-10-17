package com.mandroid.MAndroidApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";
    public final static String MESSAGE = "MESSAGE";
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.mainImageView);
        image.setImageResource(R.drawable.icon);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(MESSAGE, message);
        startActivity(intent);
    }
}