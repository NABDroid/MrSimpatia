package com.ernestyes.mrsimpatia.fiverr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CursorMachine {

    public Bitmap cursor;


    public CursorMachine(Resources res, Context context) {
        cursor = BitmapFactory.decodeResource(res, R.drawable.machine_4);
        cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
    }

    public Bitmap getCursor() {
        return cursor;
    }


}

