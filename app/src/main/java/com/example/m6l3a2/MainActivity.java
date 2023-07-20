package com.example.m6l3a2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Image view to load image from storage
    ImageView loadimage;
    Button loadimageButton;

    private static final String FILE_NAME = "dog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  referred button an assigned function on click
        loadimageButton = findViewById(R.id.button_load);
        loadimageButton.setOnClickListener(view -> readImage());

    }

    public void save(View v) {
        //File output stream to save bitmap
        FileOutputStream fileOutputStream = null;

        // take image from drawable folder as Bitmap drawable and then convert to bitmap
        @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable imagedata = (BitmapDrawable) getDrawable(R.drawable.dog);
        Bitmap data = imagedata.getBitmap();

        try {
            // File name and mode selected
            fileOutputStream = openFileOutput(FILE_NAME+".jpg", MODE_PRIVATE);

            // compress quality
            data.compress(Bitmap.CompressFormat.JPEG,50, fileOutputStream);

            //Toast to show file location where image is saved
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME+".jpg",
                    Toast.LENGTH_LONG).show();

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    // close file output stream
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readImage()
    {
        // bitmap to store loaded image from storage
        Bitmap bitmap=null;
        //Input stream to save image
        InputStream inputStream;
        try {
                // give it file name to fetch
                inputStream = openFileInput("dog.jpg");
                // Decode input stream to bitmap
                bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // set image of image view to bitmap
        loadimage =  findViewById(R.id.imageview);
        loadimage.setImageBitmap(bitmap);
    }

}