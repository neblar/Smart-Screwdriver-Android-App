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
        BluetoothController.sendCommand("Feedback");

        Logger.setStatus(MainActivity.activity.getString(R.string.statusStartLearning));
        UI.rotateLearn();

    }


    /**
     * loosen
     * This function tells the screwdriver to loose the screw by one pitch
     */
    static void loosen(){

        if(!BluetoothController.isConnected()){
            return;
        }
        BluetoothController.sendCommand("Loosen");

        Logger.setStatus(MainActivity.activity.getString(R.string.statusLoosening));

    }

    /**
     * start
     * This function starts the screwDriver
     */
    static void start(){

        if(!BluetoothController.isConnected()){
            return;
        }

        BluetoothController.sendCommand("Start");

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

        BluetoothController.sendCommand("Quit");
        BluetoothController.readCommand();

        Logger.setStatus(MainActivity.activity.getString(R.string.statusStop));
        UI.rotateStop();

    }


    /**
     * tighten
     * This function tells the screwdriver to tighten the screw by one pitch
     */
    static void tighten(){

        if(!BluetoothController.isConnected()){
            return;
        }
        BluetoothController.sendCommand("Tighten");

        Logger.setStatus(MainActivity.activity.getString(R.string.statusTightening));

    }

}
