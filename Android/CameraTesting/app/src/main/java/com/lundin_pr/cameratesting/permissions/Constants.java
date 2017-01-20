package com.lundin_pr.cameratesting.permissions;

import android.Manifest;

/**
 * Created by Eirikur Lundin on 1/20/2017.
 *
 */

public class Constants {

    public static final int REQUEST_CAMERA = 2;
    public static String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA
    };

    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final int TAKE_PICTURE = 1;
    public static int PICTURE_COUNTER = 0;
}
