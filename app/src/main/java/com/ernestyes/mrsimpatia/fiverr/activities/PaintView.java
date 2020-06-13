package com.ernestyes.mrsimpatia.fiverr.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.Draw;
import com.ernestyes.mrsimpatia.fiverr.R;

import java.util.ArrayList;

public class PaintView extends View {
    public static int BRUSH_SIZE = 10;
    public static final int DEFAULT_COLOR = Color.BLACK;

    public static final int DEFAULT_BG_COLOR = Color.rgb(228, 162, 118);
    private static final float TOUCH_TOLERANCE = 4;


    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private Bitmap mBitmap, backgroundBitmap, referenceBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private Paint opacityPaint = new Paint();
    private ArrayList<Draw> paths = new ArrayList<>();
    private ArrayList<Draw> undo = new ArrayList<>();

    public PaintView(Context context) {
        super(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        opacityPaint.setAlpha(42);
    }

    public void initialise(Bitmap bitmap) {
        mBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
        backgroundBitmap = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
//        mCanvas.drawColor(backgroundColor); // WRONG
        for (Draw draw : paths) {
            mPaint.setColor(draw.color); // WRONG
            mPaint.setStrokeWidth(draw.strokeWidth);
            mPaint.setMaskFilter(null);
            mCanvas.drawPath(draw.path, mPaint);
        }
        canvas.drawBitmap(backgroundBitmap, 0,0, opacityPaint);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();

    }

    private void touchStart(float x, float y) {

        mPath = new Path();

        Draw draw = new Draw(currentColor, strokeWidth, mPath);
        paths.add(draw);

        mPath.reset();
        mPath.moveTo(x, y);

        mX = x;
        mY = y;

    }

    private void touchMove(float x, float y) {

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {

            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);

            mX = x;
            mY = y;

        }

    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;

        }

        return true;

    }

    public void clear() {
        backgroundColor = DEFAULT_BG_COLOR;
        paths.clear();
        invalidate();

    }

    public void undo() {

        if (paths.size() > 0) {

            undo.add(paths.remove(paths.size() - 1));
            invalidate(); // add

        } else {

            Toast.makeText(getContext(), "Nothing to undo", Toast.LENGTH_LONG).show();

        }

    }

    public void redo() {

        if (undo.size() > 0) {

            paths.add(undo.remove(undo.size() - 1));
            invalidate(); // add

        } else {

            Toast.makeText(getContext(), "Nothing to undo", Toast.LENGTH_LONG).show();

        }

    }

    public void setStrokeWidth(int width) {
        strokeWidth = width;
    }

    public void setColor(int color) {
        currentColor = color;
    }


    public String getResult() {
        int match = 0, unmatch = 0;

        int[][] drawingMatrix = new int[512][512];
        int[][] referenceMatrix =  new int[512][512];


        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                drawingMatrix[i][j] = mBitmap.getPixel(i, j);
                referenceMatrix[i][j] = backgroundBitmap.getPixel(i, j);



                if (drawingMatrix[i][j] == referenceMatrix[i][j]){
                    match++;
                } else {
                    unmatch++;
                }

//                if (referenceMatrix[i][j]!=0){
//                    if (drawingMatrix[i][j] == referenceMatrix[i][j]) {
//                        match++;
//                    } else {
//                        unmatch++;
//                    }
//                } else {
//                    drawingMatrix[i][j] = mBitmap.getPixel(i, j);
//                    if (drawingMatrix[i][j]==-1793418){
//                        match++;
//                    } else {
//                        unmatch++;
//                    }
//                }

            }
        }


//        if (match>unmatch){
//            return true;
//        } else {
//            return false;
//        }
        String result = "Match: "+match+" unmatched: "+unmatch+"\n DrawingMatrix: "+drawingMatrix[500][500]+" referenceMatrix: "+referenceMatrix[500][500]+"";
        return result;

    }

    private void createBitmapForBackground(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_level_1);
        bitmap = Bitmap.createScaledBitmap(bitmap, 512, 512, false);

        int[][] backgroundMatrix = new int[512][512];


        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                backgroundMatrix[i][j] = bitmap.getPixel(i, j);
                if (backgroundMatrix[i][j]==0){
                    backgroundMatrix[i][j] = 13421772;
                }
            }
        }



    }
}
