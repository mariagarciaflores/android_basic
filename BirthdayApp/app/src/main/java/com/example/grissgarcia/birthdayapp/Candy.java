package com.example.grissgarcia.birthdayapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Griss Garcia on 14/07/2015.
 */
public class Candy {

    private float pX;
    private float pY;

    private int pSpeedX;
    private int pSpeedY;

    private Bitmap bitmap;

    public Candy(Resources resources, int x, int y){
        Random random = new Random();
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.d1);
        pX = x - bitmap.getWidth() / 2;
        pY = y - bitmap.getHeight() / 2;
        pSpeedX = random.nextInt(7) - 2;
        pSpeedY = random.nextInt(7) - 2;
    }

    public void doDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap, pX, pY, null);
    }

    public void animate() {
        pX += pSpeedX;
        pY += pSpeedY;
        checkScreenEdges();
    }

    private void checkScreenEdges() {
        if (pX <= 0) {
            pSpeedX = -pSpeedX;
            pX = 0;
        } else if (pX + bitmap.getWidth() >= CandySurface.getInstance().getpWidth()) {
            pSpeedX = -pSpeedX;
            pX = CandySurface.getInstance().getpWidth() - bitmap.getWidth();
        }
        if (pY <= 0) {
            pY = 0;
            pSpeedY = -pSpeedY;
        }
        if (pY + bitmap.getHeight() >= CandySurface.getInstance().getpHeight()) {
            pSpeedY = -pSpeedY;
            pY = CandySurface.getInstance().getpHeight() - bitmap.getHeight();
        }

    }
}
