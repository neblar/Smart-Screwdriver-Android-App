package com.neblar.screwd;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class ManualMotorControl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_motor_control);

        final ToggleButton portXEnable = (ToggleButton) findViewById(R.id.portXEnable);
        final ToggleButton portXSleep = (ToggleButton) findViewById(R.id.portXSleep);
        final Spinner portXMode = (Spinner) findViewById(R.id.portXMode);
        final EditText portXDelay = (EditText) findViewById(R.id.portXDelay);
        final EditText portXSteps = (EditText) findViewById(R.id.portXSteps);

        final ToggleButton portYEnable = (ToggleButton) findViewById(R.id.portYEnable);
        final ToggleButton portYSleep = (ToggleButton) findViewById(R.id.portYSleep);
        final Spinner portYMode = (Spinner) findViewById(R.id.portYMode);
        final EditText portYDelay = (EditText) findViewById(R.id.portYDelay);
        final EditText portYSteps = (EditText) findViewById(R.id.portYSteps);

        Button controlButton = (Button) findViewById(R.id.motorControlButton);

        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String command = "m";
                    command += portXEnable.isChecked() ? portYEnable.isChecked() ? ","+2 : ","+0 : portYEnable.isChecked() ? ","+1 : ","+3 ;
                    command += portXSleep.isChecked() ? ","+1 : ","+0;
                    command += ","+portXMode.getSelectedItemId();
                    command += ","+Double.parseDouble(portXDelay.getText().toString());
                    command += ","+Integer.parseInt(portXSteps.getText().toString());

                    command += portYSleep.isChecked() ? ","+1 : ","+0;
                    command += ","+portYMode.getSelectedItemId();
                    command += ","+Double.parseDouble(portYDelay.getText().toString());
                    command += ","+Integer.parseInt(portYSteps.getText().toString());
                    BluetoothController.sendCommand(command);
                }
                catch (Exception e){
                    Logger.log("Error in sending manual control:: "+e);
                }
            }
        });

    }
}
