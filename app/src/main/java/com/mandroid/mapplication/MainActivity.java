package com.mandroid.mapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";
    //http://cg8t.com/api/v1/users/5681034041491456/1008151122.jpg?_method=head&_format=json
    private final String SRC = "http://cg8t.com/api/v1/users/5681034041491456/1008151122.jpg";
    private static ImageView pic;
    private Button submitButton, getButton;

    public static Bitmap getResponseFromURL(String url) {

        url += "?_method=head&_format=json";
   //     url = "http://cg8t.com/api/v1/users/5681034041491456/1008151122.jpg?_method=head&_format=json";
        HttpResponse httpResponse = null;
        HttpClient httpclient = new DefaultHttpClient();
        Bitmap bitmap = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpResponse = httpclient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            HttpEntity entity = httpResponse.getEntity();
            if (statusCode == HttpStatus.SC_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                StringBuffer resultJSON = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    resultJSON.append(line);
                }
                String resultJSONString = resultJSON.toString();
                Log.i(TAG, resultJSONString);
            }


        } catch (Exception ex) {

            ex.printStackTrace();

        }
        return bitmap;
    }

    public static Bitmap getBitMapFromURL(String url) {

        HttpResponse httpResponse = null;
        HttpClient httpclient = new DefaultHttpClient();
        Bitmap bitmap = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpResponse = httpclient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            HttpEntity entity = httpResponse.getEntity();
            if (statusCode == HttpStatus.SC_OK) {

                BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                InputStream input = b_entity.getContent();
                Log.i(TAG, input.toString());
                bitmap = BitmapFactory.decodeStream(input);
                pic.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButton = (Button) findViewById(R.id.getImage);
        pic = (ImageView) findViewById(R.id.imageView);

        getButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                getResponseFromURL(SRC);
                pic.setImageBitmap(getBitMapFromURL(SRC));
            }
        });

        getButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {

                getButton.setTextColor(Color.BLACK);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
