/**
 * MainActivity
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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Regular.ttf");
        setContentView(R.layout.activity_main);

        Typeface ralewayLight = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        Typeface ralewayBold = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");

        Button start            = (Button) findViewById(R.id.buttonStart);
        Button stop             = (Button) findViewById(R.id.buttonStop);
        Button learn            = (Button) findViewById(R.id.buttonLearn);
        Button loosen             = (Button) findViewById(R.id.buttonLoosen);
        Button tighten           = (Button) findViewById(R.id.buttonTighten);
        ImageView bluetooth     = (ImageView) findViewById(R.id.buttonBluetooth);
        ImageView motorControl  = (ImageView) findViewById(R.id.buttonMotorControl);
        TextView status         = (TextView) findViewById(R.id.status);
        TextView logoText       = (TextView) findViewById(R.id.logoText);
        ImageView logo          = (ImageView) findViewById(R.id.logo);

        status.setTypeface(ralewayLight);
        logoText.setTypeface(ralewayBold);

        Logger.statusText = status;
        activity = this;
        UI.bluetooth = bluetooth;
        UI.logo = logo;

        BluetoothController.init();
        Logger.setStatus(getString(R.string.statusDefault));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArduinoCommander.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArduinoCommander.stop();
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArduinoCommander.learn();
            }
        });

        loosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArduinoCommander.loosen();
            }
        });

        tighten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArduinoCommander.tighten();
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BluetoothController.toggleBluetooth();
            }
        });

        motorControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ManualMotorControl.class));
            }
        });
    }
}
