package com.d5ve.randomac;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMAC extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(RandoMAC.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        //textView.setText(message);
        textView.setText( this.getMACAddress() );

        // Set the text view as the activity layout
        setContentView(textView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getMACAddress() {
        WifiManager wifiMan = (WifiManager) this.getSystemService( Context.WIFI_SERVICE );
        WifiInfo wifiInfo = wifiMan.getConnectionInfo();
        String macAddr = wifiInfo.getMacAddress();
        return macAddr;
    }

}
