/**
 * UI
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

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since 22 May 2017
 */

class UI {

    private static RotateAnimation rotate = new RotateAnimation(0, 360, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
    static ImageView logo;
    static ImageView bluetooth;

    /**
     * rotateLearn
     * rotates the logoScrew in bounde mode
     */
    static void rotateLearn(){
        rotate.setDuration(2000);
        rotate.setRepeatCount(android.view.animation.Animation.INFINITE);
        rotate.setInterpolator(new BounceInterpolator());
        logo.startAnimation(rotate);
    }


    /**
     * rotateStart
     * starts rotating the logoScrew
     */
    static void rotateStart(){
        rotate.setDuration(1000);
        rotate.setRepeatCount(android.view.animation.Animation.INFINITE);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        logo.startAnimation(rotate);

    }

    /**
     * rotateStop
     * stops the logoScrew from rotating
     */
    static void rotateStop(){
        try {
            rotate.setRepeatCount(0);
        }
        catch(Exception e){
            // do nothing
        }
    }



    /**
     * setBluetoothImageConnected
     * sets the image corresponding to the connected state of device
     */
    static void setBluetoothImageConnected(){
        bluetooth.setImageResource(R.drawable.bluetooth_connected);
    }

    /**
     * setBluetoothImageDisonnected
     * sets the image corresponding to the disconnected state of device
     */
    static void setBluetoothImageDisconnected(){
        bluetooth.setImageResource(R.drawable.bluetooth);
    }
}
