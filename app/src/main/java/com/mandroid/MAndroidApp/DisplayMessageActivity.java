package com.mandroid.MAndroidApp;

import android.app.Activity;
import android.app.SearchManager;
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

            googleSearch(message);
//            image = (ImageView) findViewById(R.id.imageView);
            //            image.setImageResource(R.drawable.piratey);
            //
            //            TextView mesgText = (TextView) findViewById(R.id.mesgTextView);
            //            mesgText.setText(message);
            //            mesgText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33);
            //            mesgText.setTextColor(Color.YELLOW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void googleSearch(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query); // query contains search string
        startActivity(intent);


    }
}