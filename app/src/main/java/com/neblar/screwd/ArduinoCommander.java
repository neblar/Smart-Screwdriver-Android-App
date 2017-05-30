package com.neblar.screwd;

/**
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since 21 May 2017
 */
class ArduinoCommander {

    /**
     * learn
     * This function starts the learning process
     */
    static void learn(){

        if(!BluetoothController.isConnected()){
            return;
        }
        BluetoothController.sendCommand("Bye");

        Logger.setStatus(MainActivity.activity.getString(R.string.statusStartLearning));
        UI.rotateLearn();

    }

    /**
     * start
     * This function starts the screwDriver
     */
    static void start(){

        if(!BluetoothController.isConnected()){
            return;
        }

        BluetoothController.sendCommand("Hello");

        Logger.setStatus(MainActivity.activity.getString(R.string.statusStart));
        UI.rotateStart();
    }

    /**
     * stop
     * This function stops the screwDriver
     */
    static void stop(){

        if(!BluetoothController.isConnected()){
            return;
        }

        BluetoothController.sendCommand("getData");
        BluetoothController.readCommand();

        Logger.setStatus(MainActivity.activity.getString(R.string.statusStop));
        UI.rotateStop();

    }

}
