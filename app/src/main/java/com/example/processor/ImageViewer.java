package com.example.processor;

import android.content.Intent;;
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
    int p=0;
    int r=0;


    float x1,x2;
    float y1, y2;
    float diffx, diffy;
    int swipe = 100;

    int displayWidth  = Resources.getSystem().getDisplayMetrics().widthPixels;
    double h = displayWidth*1.25;
    int ivHeight = (int) h;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        selectImageFromGallery();
        SubsamplingScaleImageView photo = findViewById(R.id.photo);
        ImageView template = findViewById(R.id.template);
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


                //Left to right
                if (x1 < x2 && Math.abs(diffx) > swipe && Math.abs(diffy) < Math.abs(diffx))
                {

                }
                //Right to left
                if (x1 > x2 && Math.abs(diffx) > swipe && Math.abs(diffy) < Math.abs(diffx))
                {

                }
                //Up to down
                if (y1 < y2 && Math.abs(diffy) > swipe && Math.abs(diffy) > Math.abs(diffx))
                {
                    ImageView template = findViewById(R.id.template);
                    switch (p)
                    {
                        case 0:
                            template.setImageResource(R.drawable.img1);
                            p++;
                            break;
                        case 1:
                            template.setImageResource(R.drawable.img2);
                            p++;
                            break;
                        case 2:
                            template.setImageResource(R.drawable.img3);
                            p++;
                            break;
                        case 3:
                            template.setImageResource(R.drawable.img0);
                            p=0;
                            break;
                        default:
                            break;
                    }

                }
                //Down to up
                if (y1 > y2 && Math.abs(diffy) > swipe && Math.abs(diffy) > Math.abs(diffx))
                {
                    ImageView template = findViewById(R.id.template);
                    switch (p)
                    {
                        case 0:
                            template.setImageResource(R.drawable.img1);
                            p = 3;
                            break;
                        case 3:
                            template.setImageResource(R.drawable.img2);
                            p--;
                            break;
                        case 2:
                            template.setImageResource(R.drawable.img3);
                            p--;
                            break;
                        case 1:
                            template.setImageResource(R.drawable.img0);
                            p--;
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
        SubsamplingScaleImageView imageView = findViewById(R.id.photo);
        switch (r){
            case 0:
                imageView.setOrientation(90);
                r++;
                break;
            case 1:
                imageView.setOrientation(180);
                r++;
                break;
            case 2:
                imageView.setOrientation(270);
                r++;
                break;
            case 3:
                imageView.setOrientation(0);
                r = 0;
                break;
            default:
                break;
        }

    }
    public void changeTemplate(View view)
    {
        ImageView template = findViewById(R.id.template);
        switch (p)
        {
            case 0:
                template.setImageResource(R.drawable.img1);
                p++;
                break;
            case 1:
                template.setImageResource(R.drawable.img2);
                p++;
                break;
            case 2:
                template.setImageResource(R.drawable.img3);
                p++;
                break;
            case 3:
                template.setImageResource(R.drawable.img0);
                p=0;
                break;
            default:
                break;
        }
    }
    public void selectImageFromGallery(){
        Intent picker = new Intent();
        picker.setType("image/*");
        picker.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(picker, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void selectButton(View view){
        Intent picker = new Intent();
        picker.setType("image/*");
        picker.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(picker, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(
            int aRequestCode, int aResultCode, Intent aData
    ){
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

            Button imageName = findViewById(R.id.button);
            Uri imageUri = aData.getData();
            SubsamplingScaleImageView imageView = findViewById(R.id.photo);
            String sImage;
            sImage = imageUri.toString();
            imageName.setText("Image URI = " + sImage);
            imageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
            imageView.setImage(ImageSource.uri(sImage));
        } else {
            Toast.makeText(getApplicationContext(), "No image chosen",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

