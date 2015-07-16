package com.example.grissgarcia.birthdayapp;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;

/**
 * Created by Griss Garcia on 14/07/2015.
 */
public class CandySurface extends SurfaceView implements SurfaceHolder.Callback {

    private float pWidth;
    private float pHeight;
    private CandyThread thread;
    private ArrayList<Candy> candies = new ArrayList<>();
    private int candyNumbers = 0;
    private static CandySurface candySurface;

    public CandySurface(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new CandyThread(this);
        candySurface = new CandySurface(context);
    }

    public static CandySurface getInstance() {
        return candySurface;
    }

    public void doDraw(Canvas canvas) {
        synchronized (candies) {
            for (Candy candy : candies) {
                candy.doDraw(canvas);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!thread.isAlive()) {
            thread = new CandyThread(this);
            thread.setRunning(true);
            thread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        pWidth = width;
        pHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (thread.isAlive()) {
            thread.setRunning(false);
        }
    }

    public void animateCandy() {
        synchronized (candies) {
            for(Candy candy : candies) {
                candy.animate();
            }
        }
    }

    public float getpWidth() {
        return pWidth;
    }

    public float getpHeight() {
        return pHeight;
    }
}
