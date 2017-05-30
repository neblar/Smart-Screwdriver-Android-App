package com.neblar.screwd;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since 23 May 2017
 */

class BluetoothConnector extends AsyncTask<Void, Void, Void> {

    private String bluetoothName = "Screwd";
    private UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private int progress;
    private BluetoothDevice device;
    private BluetoothSocket sock;
    private Boolean state = false;
    private OutputStream out;
    private InputStream in;
    private Exception e;

    @Override
    protected Void doInBackground(Void... voids) {


        Set<BluetoothDevice> devs = BluetoothController.adapter.getBondedDevices();

        for(BluetoothDevice dev : devs){
            if(dev.getName().equals(bluetoothName)){
                device = dev;
            }
        }

        if(device==null){
            progress = 0;
            publishProgress();
            return null;
        }

        progress = 1;
        publishProgress();

        try {
            sock = device.createRfcommSocketToServiceRecord(DEFAULT_UUID);
            sock.connect();
            in = sock.getInputStream();
            out = sock.getOutputStream();
            progress = 2;
            publishProgress();
            state = true;
        }
        catch (Exception e){
            progress = 3;
            publishProgress();
            this.e=e;
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        switch(progress){
            case 0:
                Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothDeviceNotPaired));
                break;
            case 1:
                Logger.setStatus(MainActivity.activity.getResources().getString(R.string.statusBluetoothFound));
                break;
            case 2:
                Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothConnected));
                UI.setBluetoothImageConnected();
                break;
            case 3:
                Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothConnectionFailed));
                Logger.log("BT Connect Failed :: "+e);
                break;
        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        BluetoothController.socket = sock;
        BluetoothController.input = in;
        BluetoothController.output = out;
        BluetoothController.connectionState = state;
    }
}