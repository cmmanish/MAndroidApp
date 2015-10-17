package com.mandroid.MAndroidApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setTextSize(40);
        textView.setText("Hello " + message);

        // Set the text view as the activity layout
        setContentView(textView);
    }
}