package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //JNI functions

    //func 1: returns i32 x 2
    private static native int returni32 (final int i32);
    //func 2: returns false
    private static native boolean getFileStatus();
    //func 3: concatenates the "string" with another String and returns the result
    private static native String returnJString(final String string);
    //func 4:  commit a String (e.g. "An island with two hills") and the function returns an array with words (e.g. ["An", "island", "with", "two", "hills"])
    private static native String[] returnJArrayfromJString(final String string);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load rust library
        System.loadLibrary("rust");

        // func 1
        int i = returni32 (5);

        ((TextView)findViewById(R.id.func1Name)).setText("returni32 (5)");
        ((TextView)findViewById(R.id.func1Result)).setText("Result: " + i);


        // func 2
        boolean value = getFileStatus();

        ((TextView)findViewById(R.id.func2Name)).setText("getFileStatus()");
        ((TextView)findViewById(R.id.func2Result)).setText("Result: " + value);


        // func 3
        String string = returnJString("myString");

        ((TextView)findViewById(R.id.func3Name)).setText("returnJString(\"myString\")");
        ((TextView)findViewById(R.id.func3Result)).setText("Result: " + string);


        // func 4
        String[] stringArray = returnJArrayfromJString("An island with two hills");
        
        ((TextView)findViewById(R.id.func4Name)).setText("returnJArrayfromJString(\"An island with two hills\")");
        ((TextView)findViewById(R.id.func4Result)).setText("Result: " + java.util.Arrays.toString(stringArray));


    }
}
