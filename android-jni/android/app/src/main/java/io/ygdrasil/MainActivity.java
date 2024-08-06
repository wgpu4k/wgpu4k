package io.ygdrasil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //JNI functions

    //func 1: returns i32 x 2
    private static native int returni32 (final int i32);
    //func 2: returns false
    private static native boolean getFileStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load rust library
        System.loadLibrary("wgpu4k");

        // func 1
        int i = returni32 (5);

        ((TextView)findViewById(R.id.func1Name)).setText("returni32 (5)");
        ((TextView)findViewById(R.id.func1Result)).setText("Result: " + i);


        // func 2
        boolean value = getFileStatus();

        ((TextView)findViewById(R.id.func2Name)).setText("getFileStatus()");
        ((TextView)findViewById(R.id.func2Result)).setText("Result: " + value);



    }
}
