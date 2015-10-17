package com.mandroid.MAndroidApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayMessageActivity extends Activity {

    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_message);
            // Get the message from the intent
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.MESSAGE);
            image = (ImageView) findViewById(R.id.image);
            image.setImageResource(R.drawable.icon);

//            TextView mesgText = (TextView) findViewById(R.id.mesgTextView);
            //            mesgText.setText(message);
            //            mesgText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33);
            //            mesgText.setTextColor(Color.YELLOW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}