package com.lundin_pr.cameratesting;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lundin_pr.cameratesting.permissions.Constants;
import com.lundin_pr.cameratesting.permissions.PermissionUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Button button;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.myImage);
        button = (Button) findViewById(R.id.myBtn);

        if(!PermissionUtils.isCameraGranted(this)){
            PermissionUtils.requestPermissions(this, Constants.REQUEST_CAMERA, Constants.PERMISSIONS_CAMERA);
        }

        if(!PermissionUtils.isExternalWriteGranted(this)){
            PermissionUtils.requestPermissions(this, Constants.REQUEST_EXTERNAL_STORAGE, Constants.PERMISSIONS_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "The application needs to access storage", Toast.LENGTH_SHORT).show();
                }
            case Constants.REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            takePhoto();
                        }
                    });
                } else {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PermissionUtils.requestPermissions(MainActivity.this, Constants.REQUEST_CAMERA, Constants.PERMISSIONS_CAMERA);
                        }
                    });
                }
        }
    }

    public void takePhoto() {
        Constants.PICTURE_COUNTER++;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic" + Constants.PICTURE_COUNTER + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, Constants.TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    getContentResolver().notifyChange(imageUri, null);
                    ContentResolver cr = getContentResolver();
                    try {
                        Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, imageUri);
                        image.setImageBitmap(bitmap);
                        Toast.makeText(this, "This file: " + imageUri.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }
}
