package com.mandroid.mapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitButton = (Button) findViewById(R.id.submit);
        final Button cancelButton = (Button) findViewById(R.id.cancel);
        final TextView mTextView = (TextView) findViewById(R.id.helloLabel);

        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                mTextView.setTextColor(Color.BLUE);

                final EditText fNameText = (EditText) findViewById(R.id.fNameText);

                String fName = fNameText.getText().toString();

                Log.i(TAG, "Hello "+fName);

                mTextView.setText("Hello "+fName);
                submitButton.setTextColor(Color.BLACK);
            }
        });

        submitButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {

                mTextView.setTextColor(Color.BLUE);
                mTextView.setText("Holly Carp that was a Long One");
                submitButton.setTextColor(Color.BLACK);
                return true;
            }
        });

        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                mTextView.setTextColor(Color.RED);
                mTextView.setText("Hello Manish");
                cancelButton.setTextColor(Color.BLACK);
            }
        });

        cancelButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {

                mTextView.setTextColor(Color.BLUE);
                mTextView.setText("Holly Carb that was a Long One");
                cancelButton.setTextColor(Color.BLACK);
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
