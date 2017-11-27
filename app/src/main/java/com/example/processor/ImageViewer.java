package com.example.processor;

import android.content.Intent;;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        selectImageFromGallery();
    }

    public void rotateImage(View view)
    {
        SubsamplingScaleImageView imageView = findViewById(R.id.photo);
        switch (r){
            case 0:
                imageView.setRotation((float) 90.0);
                r++;
                break;
            case 1:
                imageView.setRotation((float) 180.0);
                r++;
                break;
            case 2:
                imageView.setRotation((float) 270.0);
                r++;
                break;
            case 3:
                imageView.setRotation((float) 0.0);
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
            imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_270);
            imageView.setImage(ImageSource.uri(sImage));



        } else {
            Toast.makeText(getApplicationContext(), "No image chosen",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
