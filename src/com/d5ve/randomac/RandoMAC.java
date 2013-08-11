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
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.exceptions.RootDeniedException;
import com.stericson.RootTools.execution.CommandCapture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

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

        if (RootTools.isRootAvailable()) {
            // su exists, do something
            // Test for RootTools.isBusyboxAvailable() if required.
        } else {
            // Warn the user that they need root.
        }
    }

    /** Called when the user clicks the Set MAC button */
    public void setMac(View view) {

        EditText editText = (EditText) findViewById(R.id.new_mac);
        String new_mac = editText.getText().toString();
        if ( new_mac.equals("") ) {
            new_mac = editText.getHint().toString();
        }

        StringBuilder s = new StringBuilder(128);
        s.append("busybox ifconfig wlan0 hw ether ");
        s.append(new_mac);

        WifiManager wifiMan = (WifiManager) this.getSystemService( Context.WIFI_SERVICE );
        boolean wifiWasEnabled = wifiMan.isWifiEnabled();
        if ( wifiWasEnabled ) {
            // Disable wifi whilst we edit the file.
            wifiMan.setWifiEnabled(false);
        }

        try {
            CommandCapture command = new CommandCapture(0, s.toString());
            RootTools.getShell(true).add(command).waitForFinish();
        } catch (IOException e) {
            // Handle exception
        } catch (InterruptedException e) {
            // Handle exception
        } catch (TimeoutException e) {
            // Handle exception
        } catch (RootDeniedException e) {
            // Handle exception. This one would have been raised by the developer.
        }

        if ( wifiWasEnabled ) {
            // Re-enable wifi if it was originally on.
            wifiMan.setWifiEnabled(true);
        }

        TextView currentMac = (TextView) findViewById(R.id.current_mac);
        currentMac.setTextSize(30);
        currentMac.setText( getCurrentMacAddress() );
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
