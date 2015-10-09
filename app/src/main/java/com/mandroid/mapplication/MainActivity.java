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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class MainActivity extends Activity {
    private static final String TAG = "Log-Messages";
    private final String SRC = "http://cg8t.com/api/v1/users/5681034041491456/1008151122.jpg?";
    private static ImageView pic;
    private Button submitButton, getButton;

    public static Bitmap getBitmapFromURL(String url) {

        HttpClient httpclient = new DefaultHttpClient();
        Bitmap bitmap = null;
        try {
            HttpGet httpGet = new HttpGet(url);

            HttpResponse response = (HttpResponse) httpclient.execute(httpGet);
            Log.i(TAG, response.toString());
            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();
            Log.i(TAG, input.toString());
            bitmap = BitmapFactory.decodeStream(input);
            pic.setImageBitmap(bitmap);

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

                pic.setImageBitmap(getBitmapFromURL(SRC));
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
