package com.ernestyes.mrsimpatia.fiverr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CursorMachine {

    public Bitmap cursor;


    public CursorMachine(Resources res, Context context, int cursorId) {
        switch (cursorId) {
            case 1:
                cursor = BitmapFactory.decodeResource(res, R.drawable.cursor);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;
            case 2:
                cursor = BitmapFactory.decodeResource(res, R.drawable.machine_5);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;
            case 3:
                cursor = BitmapFactory.decodeResource(res, R.drawable.machine_2);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;
            case 4:
                cursor = BitmapFactory.decodeResource(res, R.drawable.machine_3);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;
            case 5:
                cursor = BitmapFactory.decodeResource(res, R.drawable.machine_4);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;
            default:
                cursor = BitmapFactory.decodeResource(res, R.drawable.logo);
                cursor = Bitmap.createScaledBitmap(cursor, 140, 140, false);
                break;

        }


    }

    public Bitmap getCursor() {
        return cursor;
    }


}

