package com.example.processor;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class ImageViewer extends AppCompatActivity
{
    private static final int PICK_IMAGE_REQUEST = 1;
    private int imageIndex = 0;
    private int rotationDegrees = 0;
    private float x1,x2;
    private float y1, y2;
    private float diffx, diffy;
    private int swipe = 100;

    final int displayWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    final int ivHeight = (int)(displayWidth * 1.25);

    private SubsamplingScaleImageView photo;
    private ImageView template;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        photo = findViewById(R.id.photo);
        template = findViewById(R.id.template);

        setContentView(R.layout.activity_image_viewer);
        selectImageFromGallery();

        template.setX(0);
        template.setY(150);
        photo.setX(0);
        photo.setY(150);
        template.requestLayout();
        template.getLayoutParams().height = ivHeight;
        photo.getLayoutParams().width = displayWidth;
        photo.requestLayout();
        photo.getLayoutParams().height = ivHeight;
        photo.getLayoutParams().width = displayWidth;
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                diffx = x2 -x1;
                diffy = y2-y1;

                //TODO: fill in these blanks...
                //Left to right
//                if (x1 < x2 && Math.abs(diffx) > swipe && Math.abs(diffy) < Math.abs(diffx))
//                {
//
//                }
//                //Right to left
//                if (x1 > x2 && Math.abs(diffx) > swipe && Math.abs(diffy) < Math.abs(diffx))
//                {
//
//                }
                //END TODO
                //Up to down
                if (y1 < y2 && Math.abs(diffy) > swipe && Math.abs(diffy) > Math.abs(diffx))
                {
                    switch (imageIndex )
                    {
                        case 0:
                            template.setImageResource(R.drawable.img1);
                            imageIndex ++;
                            break;
                        case 1:
                            template.setImageResource(R.drawable.img2);
                            imageIndex ++;
                            break;
                        case 2:
                            template.setImageResource(R.drawable.img3);
                            imageIndex ++;
                            break;
                        case 3:
                            template.setImageResource(R.drawable.img0);
                            imageIndex =0;
                            break;
                        default:
                            break;
                    }

                }
                //Down to up
                if (y1 > y2 && Math.abs(diffy) > swipe && Math.abs(diffy) > Math.abs(diffx))
                {
                    switch (imageIndex )
                    {
                        case 0:
                            template.setImageResource(R.drawable.img1);
                            imageIndex  = 3;
                            break;
                        case 3:
                            template.setImageResource(R.drawable.img2);
                            imageIndex --;
                            break;
                        case 2:
                            template.setImageResource(R.drawable.img3);
                            imageIndex --;
                            break;
                        case 1:
                            template.setImageResource(R.drawable.img0);
                            imageIndex --;
                            break;
                        default:
                            break;
                    }
                }
                break;
            }
        }
        return false;
    }
    public void rotateImage(View view)
    {
        switch (rotationDegrees){
            case 0:
                photo.setOrientation(90);
                rotationDegrees++;
                break;
            case 1:
                photo.setOrientation(180);
                rotationDegrees++;
                break;
            case 2:
                photo.setOrientation(270);
                rotationDegrees++;
                break;
            case 3:
                photo.setOrientation(0);
                rotationDegrees = 0;
                break;
            default:
                break;
        }

    }
    public void changeTemplate(View view)
    {
        switch (imageIndex )
        {
            case 0:
                template.setImageResource(R.drawable.img1);
                imageIndex ++;
                break;
            case 1:
                template.setImageResource(R.drawable.img2);
                imageIndex ++;
                break;
            case 2:
                template.setImageResource(R.drawable.img3);
                imageIndex ++;
                break;
            case 3:
                template.setImageResource(R.drawable.img0);
                imageIndex =0;
                break;
            default:
                break;
        }
    }
    public void selectImageFromGallery(){
        final Intent picker = new Intent();
        picker.setType("image/*");
        picker.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(picker, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void selectButton(View view){
        final Intent picker = new Intent();
        picker.setType("image/*");
        picker.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(picker, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        switch (aRequestCode) {
            case PICK_IMAGE_REQUEST:
                handleUserPickedImage(aData);
                break;
            default:
                break;
        }
        super.onActivityResult(aRequestCode, aResultCode, aData);
    }
    private void handleUserPickedImage(Intent aData) {
        if ((aData != null) && (aData.getData() != null)) {

            final Button imageName = findViewById(R.id.button);
            final Uri imageUri = aData.getData();
            final SubsamplingScaleImageView imageView = findViewById(R.id.photo);
            final String sImage = imageUri.toString();
            imageName.setText(String.format(getString(R.string.image_uri), sImage));
            imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
            imageView.setImage(ImageSource.uri(sImage));
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_image_chosen,
                    Toast.LENGTH_SHORT).show();
        }
    }
}

