/**
 * BluetoothController
 * This file is a part of ScrewD (Android Implementation)
 *
 * For project description read (https://bitbucket.org/rijulg/smart-screwdriver-android-app/overview)
 *
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since June 2017
 *
 * ScrewD is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ScrewD is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * For the GNU General Public License associated with ScrewD,
 * visit <http://www.gnu.org/licenses/>.
 */

package com.neblar.screwd;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Build;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since 22 May 2017
 */

class BluetoothController {

    static boolean connectionState = false;

    static BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    static BluetoothSocket socket = null;

    static InputStream input;
    static OutputStream output;


    @TargetApi(Build.VERSION_CODES.M)
    private static void checkBTPermissions(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = MainActivity.activity.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += MainActivity.activity.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            permissionCheck += MainActivity.activity.checkSelfPermission("Manifest.permission.BLUETOOTH");
            permissionCheck += MainActivity.activity.checkSelfPermission("Manifest.permission.BLUETOOTH_ADMIN");
            permissionCheck += MainActivity.activity.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {
                MainActivity.activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
            }
        }else{
            Logger.log("checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    /**
     * init
     * Initializes the Bluetooth interface for connection with Arduino
     */
    static void init(){

        if(adapter == null){
            Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothUnavailable));
            return;
        }

        if(!adapter.isEnabled()){
            Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothSwitchedOff));
        }

        checkBTPermissions();
    }

    /**
     * connect
     * Connects to the Arduino
     */
    private static void connect(){
        new BluetoothConnector().execute();
    }

    /**
     * disconnect
     * Disconnects the Arduino
     */
    private static void disconnect(){
        try {
            socket.close();
            connectionState = false;
            Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothDisconnected));
        }
        catch(Exception e){
            Logger.log("BT Disconnect Failed :: "+e);
        }
    }

    /**
     * isConnected
     * Checks if the screwdriver is connected or not
     * Wrapper function over the BluetoothController module, used to set the status as well
     * @return boolean true if bluetooth is connected, false otherwise
     */
    static boolean isConnected(){
        if(connectionState) {
                Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothConnected));
                return true;
        }
        Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothDisconnected));
        return false;

    }

    /**
     * readCommand
     * reads the command from Bluetooth stream
     * @return bool returns true if reading command was successful, false otherwise
     */
    static boolean readCommand(){
        try {
            int read;
            String msg = "";
            while(input.available()!=0){
                read = input.read();
                msg += (char) read;
                Logger.log("read: "+read+" :: msg so far: "+ msg);
            }
            return true;
        }
        catch (Exception e){
            Logger.log("Sending Command failed :: "+e);
        }
        return false;
    }

    /**
     *
     * @param command Command to send over to the Arduino
     * @return bool returns true if sending command was successful, false otherwise
     */
    static boolean sendCommand(String command){
        try {
            output.write(command.getBytes());
            output.flush();
            return true;
        }
        catch (Exception e){
            Logger.log("Sending Command failed :: "+e);
        }
        return false;
    }

    /**
     * toggleBluetooth
     * toggles the state of bluetooth connection with screwdriver
     */
    static void toggleBluetooth(){

        /**
         * Disconnect Bluetooth
         */
        if(connectionState){
            Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothDisconnecting));
            disconnect();
            if(connectionState) return;
            UI.setBluetoothImageDisconnected();
        }
        /**
         * Connect Bluetooth
         */
        else{
            Logger.setStatus(MainActivity.activity.getString(R.string.statusBluetoothConnecting));
            connect();
        }
    }


}




