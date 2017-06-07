/**
 * AndroidCommander
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
