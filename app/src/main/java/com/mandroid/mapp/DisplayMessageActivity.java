package com.mandroid.mapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class DisplayMessageActivity extends ActionBarActivity {

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