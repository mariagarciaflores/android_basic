package com.example.grissgarcia.happybirthdayapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by Griss Garcia on 10/07/2015.
 */
public class HappyBirthdayActivity extends Activity {

    private Drawable pinata;
    ImageButton btnPinata;
    LayoutInflater inflater;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_layout);


        pinata = getResources().getDrawable(R.drawable.icon);
        btnPinata = (ImageButton) findViewById(R.id.btn_pinata);
        btnPinata.setImageDrawable(pinata);
        btnPinata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View candyView = inflater.inflate(R.layout.candy_layout, null);

                RelativeLayout candyRelativeLayout = (RelativeLayout) candyView.findViewById(R.id.candy_relative);
                final CandyView candyView1 = new CandyView(getApplicationContext(),
                        BitmapFactory.decodeResource(getResources(), R.drawable.icon));

                candyRelativeLayout.addView(candyView1);
            }
        });
        inflater = this.getLayoutInflater();

    }

    private class CandyView extends SurfaceView implements SurfaceHolder.Callback {

        private final Bitmap candyBitmap;
        private final int candyBitHeighAndWidth, candyBitHeightAndWidthAdj;
        private final DisplayMetrics candyDisplay;
        private final int displayWhidth, displayHeight;
        private float cX, cY, cDX, cDY, cRotation;
        private final SurfaceHolder surfaceHolder;
        private final Paint cPainter = new Paint();
        private Thread drawingThread;

        private static final int MOVE_STEP = 1;
        private static final float ROT_STEP = 1.0f;

        public CandyView(Context context, Bitmap bitmap) {
            super(context);

            candyBitHeighAndWidth = (int) getResources().getDimension(
                    R.dimen.image_height_width);
            this.candyBitmap = Bitmap.createScaledBitmap(bitmap,
                    candyBitHeighAndWidth, candyBitHeighAndWidth, false);
            candyBitHeightAndWidthAdj = candyBitHeighAndWidth / 2;

            candyDisplay = new DisplayMetrics();
            HappyBirthdayActivity.this.getWindowManager().getDefaultDisplay()
                    .getMetrics(candyDisplay);

            displayWhidth = candyDisplay.widthPixels;
            displayHeight = candyDisplay.heightPixels;

            Random random = new Random();
            cX = (float) random.nextInt(displayHeight);
            cY = (float) random.nextInt(displayWhidth);

            cDX = (float) random.nextInt(displayHeight) / displayHeight;
            cDX *= random.nextInt(2) == 1 ? MOVE_STEP : -1 * MOVE_STEP;

            cDY = (float) random.nextInt(displayWhidth) / displayWhidth;
            cDY *= random.nextInt(2) == 1 ? MOVE_STEP : -1 * MOVE_STEP;

            cRotation = 1.0f;
            cPainter.setAntiAlias(true);

            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Canvas canvas = null;
                    while (!Thread.currentThread().isInterrupted() && move()) {
                        canvas = surfaceHolder.lockCanvas();
                        if (null != canvas) {
                            drawCandy(canvas);
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            });
        }

        private boolean move() {
            cX += cDX;
            cY += cDY;
            if (cX < 0 - candyBitHeighAndWidth
                    || cX > displayHeight + candyBitHeighAndWidth
                    || cY < 0 - candyBitHeighAndWidth
                    || cY > displayWhidth + candyBitHeighAndWidth) {
                return false;
            } else {
                return true;
            }
        }

        private void drawCandy(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            cRotation += ROT_STEP;
            canvas.rotate(cRotation, cY + candyBitHeightAndWidthAdj, cX
            + candyBitHeightAndWidthAdj);
            canvas.drawBitmap(candyBitmap, cY, cX, cPainter);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (null != drawingThread) {
                drawingThread.interrupt();
            }
        }
    }
}
