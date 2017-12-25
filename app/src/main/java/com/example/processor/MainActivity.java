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

        final int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        final int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        final Runtime rt = Runtime.getRuntime();
        final long maxMemory = rt.maxMemory()/(1024*1024);
        final ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        final TextView dimensions = findViewById(R.id.dimensions);
        final TextView heap = findViewById(R.id.heap);
        final TextView shouldUse = findViewById(R.id.maxMemory);

        dimensions.setText(String.format(getString(R.string.screen_resolution, width, height)));
        heap.setText(String.format(getString(R.string.max_memory, maxMemory)));

        if(am != null) {
            final int memoryClass = am.getMemoryClass();
            shouldUse.setText(String.format(getString(R.string.suggested_memory, memoryClass)));
        }
    }

    public void chosen(View view){
        Intent chosen = new Intent(this, ImageViewer.class);
                startActivity(chosen);
    }
}
