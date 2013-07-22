package com.d5ve.randomac;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class RandoMAC extends Activity
{
    public final static String EXTRA_MESSAGE = "com.d5ve.randomac.MESSAGE";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EditText editText = (EditText) findViewById(R.id.new_mac);
        editText.setHint(getRandomMacAddress());

        String current_mac = getCurrentMacAddress();
        TextView currentMac = (TextView) findViewById(R.id.current_mac);
        currentMac.setTextSize(30);
        currentMac.setText( current_mac );
    }

    /** Called when the user clicks the Set MAC button */
    public void setMac(View view) {
        // Shelling out goes here
        TextView currentMac = (TextView) findViewById(R.id.current_mac);
        currentMac.setTextSize(30);
        EditText editText = (EditText) findViewById(R.id.new_mac);
        String new_mac = editText.getText().toString();
        if ( new_mac.equals("") ) {
            new_mac = editText.getHint().toString();
        }
        currentMac.setText( new_mac );
        editText.setHint( getRandomMacAddress() );
    }

    public String getCurrentMacAddress() {
        WifiManager wifiMan = (WifiManager) this.getSystemService( Context.WIFI_SERVICE );
        WifiInfo wifiInfo = wifiMan.getConnectionInfo();
        String macAddress = wifiInfo.getMacAddress();
        return macAddress;
    }

    // Not totally random - it uses an assigned OUI block
    public String getRandomMacAddress() {

        String macAddressOui = "28:37:37"; // Apple Computers

        StringBuilder s = new StringBuilder(17);
        s.append(macAddressOui);

        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            s.append(":");
            int j = r.nextInt(255);
            s.append(String.format("%02x", j));
        }

        return s.toString();
    }
}
