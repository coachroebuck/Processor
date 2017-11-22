package com.example.processor;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Display functions/////////////////////////////////////////////////////////////////////////
        int height;
        int width;

        height = Resources.getSystem().getDisplayMetrics().heightPixels;
        width = Resources.getSystem().getDisplayMetrics().widthPixels;

        String sHeight = String.valueOf(height);
        String sWidth = String.valueOf(width);

        TextView dimensions = (TextView) findViewById(R.id.dimensions);
        dimensions.setText("Screen: " + sHeight + "px x " + sWidth + "px");

        //Memory functions//////////////////////////////////////////////////////////////////////////
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        maxMemory = maxMemory/(1024*1024);
        String memTotal = String.valueOf(maxMemory);
        TextView heap = (TextView) findViewById(R.id.heap);
        heap.setText("Max Memory: " + memTotal +"MB");

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memoryClass = am.getMemoryClass();
        String memClass = String.valueOf(memoryClass);
        TextView shouldUse = (TextView) findViewById(R.id.maxMemory);
        shouldUse.setText("Shouldn't use more than: " + memClass + "MB");
    }

    public void chosen(View view){
        Intent chosen = new Intent(this, ImageViewer.class);
                startActivity(chosen);
    }
}
