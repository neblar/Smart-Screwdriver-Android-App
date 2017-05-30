package com.neblar.screwd;

import android.util.Log;
import android.widget.TextView;

/**
 * @author Rijul Gupta (rijulg@gmail.com)
 * @since 22 May 2017
 */

class Logger {

    static TextView statusText;

    static void setStatus(String text){
        statusText.setText(text);
    }

    static void log(String text){
        Log.d("LOG",text);
    }

}
