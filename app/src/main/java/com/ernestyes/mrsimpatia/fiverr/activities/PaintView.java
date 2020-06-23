package com.ernestyes.mrsimpatia.fiverr.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.CursorMachine;
import com.ernestyes.mrsimpatia.fiverr.Draw;
import com.ernestyes.mrsimpatia.fiverr.R;

import java.util.ArrayList;


public class PaintView extends View {
    public static int BRUSH_SIZE = 10;
    public static final int DEFAULT_COLOR = Color.BLACK;
    public static final int DEFAULT_BG_COLOR = Color.rgb(255, 255, 255);
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
    private CursorMachine cursorMachine;
    private ArrayList<Draw> paths = new ArrayList<>();
    private ArrayList<Draw> undo = new ArrayList<>();
    private Context context;
    private Vibrator vibrator;
    private CountDownTimer countDownTimer;
    private boolean countDownCheck = true;
    private long waitingTime = 3000;
    private ArtActivity artActivity;

    public PaintView(Context context) {
        super(context, null);
        this.context = context;
        cursorMachine = new CursorMachine(getResources(), context);
        artActivity = new ArtActivity();

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
        this.context = context;
        cursorMachine = new CursorMachine(getResources(), context);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        artActivity = new ArtActivity();
    }

    public void initialise(Bitmap bitmap) {
        mBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
        backgroundBitmap = bitmap;
        referenceBitmap = bitmap;
        cursorMachine = new CursorMachine(getResources(), context);
        artActivity = new ArtActivity();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor); // WRONG
            for (Draw draw : paths) {
                mPaint.setColor(draw.color); // WRONG
                mPaint.setStrokeWidth(draw.strokeWidth);
                mPaint.setMaskFilter(null);
                mCanvas.drawPath(draw.path, mPaint);
                mCanvas.drawBitmap(cursorMachine.getCursor(), mX, mY - 140, mPaint);

            }
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawBitmap(backgroundBitmap, 0, 0, opacityPaint);
        canvas.restore();
    }


    private void touchStart(float x, float y) {
        mPath = new Path();
        countDownCheck = false;
        countDownTimer = new CountDownTimer(waitingTime, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished;
                progress = progress/100;
                artActivity.progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                countDownCheck = true;
            }
        }.start();

        Draw draw = new Draw(currentColor, strokeWidth, mPath);
        paths.add(draw);
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(60000);
        }

    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.moveTo(mX, mY); //test
            if (countDownCheck) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            }
            mX = x;
            mY = y;
        }

    }


    private void touchUp() {
        mPath.lineTo(mX, mY);
        vibrator.cancel();
        countDownCheck = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX() - 100;
        float y = event.getY() - 100;

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


    public int getResult() {
        int matched = 0, unmatched = 0, result;

        int[][] drawingMatrix = new int[512][512];
        int[][] referenceMatrix = new int[512][512];


        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                drawingMatrix[i][j] = mBitmap.getPixel(i, j);
                referenceMatrix[i][j] = referenceBitmap.getPixel(i, j);

                if (drawingMatrix[i][j] == -1 && referenceMatrix[i][j] == 0) {
                    matched++;
                } else if (referenceMatrix[i][j] != 0 && drawingMatrix[i][j] != -1) {
                    matched++;
                } else {
                    unmatched++;
                }


//                if (drawingMatrix[i][j] == referenceMatrix[i][j]){
//                    matched++;
//                } else {
//                    unmatched++;
//                }

            }
        }


        if (unmatched <= 10000) {
            result = 100;
        } else if (unmatched <= 20000 && unmatched > 10000) {
            result = 90;
        } else if (unmatched <= 30000 && unmatched > 20000) {
            result = 80;
        } else {
            result = 0;
        }


        Toast.makeText(getContext(), "Drw: " + drawingMatrix[256][400] + " ref " + referenceMatrix[256][400], Toast.LENGTH_SHORT).show();
//        String result = "Match: "+matched+" unmatched: "+unmatched+"\nEmptyPixel "+emptyPixels+"";
        return result;

    }

}
