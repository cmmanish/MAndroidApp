package com.mandroid.mapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";

    private static SimpleDateFormat mmddyyyyyHHMMFormat = new SimpleDateFormat("MM-dd-yyyy-hh:mm");
    private static Calendar calendar = Calendar.getInstance();
    final TextView mTextView = (TextView) findViewById(R.id.helloTextView);

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            mTextView.setText(result);
        }
    }


    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    public String getCaptureTime() {

        final String captureDate = mmddyyyyyHHMMFormat.format(calendar.getTime());
        return captureDate;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitButton = (Button) findViewById(R.id.submit);
        final Button cancelButton = (Button) findViewById(R.id.cancel);

        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);

        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                mTextView.setTextColor(Color.BLUE);
                final EditText fNameText = (EditText) findViewById(R.id.fNameText);
                String fName = fNameText.getText().toString();
                Log.i(TAG, "Hello " + fName);
                mTextView.setText(getCaptureTime().toString() + " " + fName);

                Log.i(TAG, "Hello " + getCaptureTime().toString());

                dateTextView.setText(getCaptureTime().toString());
            }
        });

        submitButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {

                // call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("http://hmkcode.appspot.com/rest/controller/get.json");

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
