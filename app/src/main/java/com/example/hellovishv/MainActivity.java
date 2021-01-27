package com.example.hellovishv;

import android.app.NotificationManager;
import android.os.Bundle;

import com.clevertap.android.sdk.CleverTapAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());


        clevertapDefaultInstance.createNotificationChannel(getApplicationContext(),"vishvchannel","Vishv Channel","Vishv's Notification Channel", NotificationManager.IMPORTANCE_MAX,true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Vishv, you have viewed the product.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // event with properties
                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product ID", 1);
                prodViewedAction.put("Product Image", "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg");
                prodViewedAction.put("Product Name", "CleverTap");
                prodViewedAction.put("Date", new java.util.Date());


                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

                //Update pre-defined profile properties
                profileUpdate.put("Name", "Vishv Garg");
                profileUpdate.put("Email", "vishv1995@gmail.com");
                profileUpdate.put("Identity", "9999396853");      // String or number
                profileUpdate.put("Phone", "+919999396853");   // Phone (with the country code, starting with +)
                profileUpdate.put("MSG-push", true);          // Enable push notifications

                clevertapDefaultInstance.pushProfile(profileUpdate);
                //clevertapDefaultInstance.pushNotificationClickedEvent(savedInstanceState);
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