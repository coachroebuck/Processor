package com.example.processor;

import android.graphics.BitmapFactory;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Los on 12/1/2017.
 */

public class BitmapCreator  {
    SubsamplingScaleImageView imageView = findViewById(R.id.photo);
    try {

        InputStream ims = getContentResolver().openInputStream(mPicPath);

        // just display image in imageview

        imageView.setImageBitmap(BitmapFactory.decodeStream(ims));

    } catch (FileNotFoundException e) {

        e.printStackTrace();

    }

    public static create(){

    }
}
