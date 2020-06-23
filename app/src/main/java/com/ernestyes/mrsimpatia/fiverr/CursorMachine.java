package com.ernestyes.mrsimpatia.fiverr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ProgressBar;


public class CursorMachine {


    public Bitmap cursor;
    private Context context;


    public CursorMachine(Resources res, Context context) {
        cursor = BitmapFactory.decodeResource(res, R.drawable.cursor);
        cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);

    }

    public Bitmap getCursor(){
        return cursor;
    }

}

