package com.aabtech.finalProject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Abdullah on 3/28/2016.
 */
public class Circle extends shapes
{

    public int customP1,customP2,customP3,customP4;
    public Circle(Context context , int p1,int p2, int p3, int p4) {
        super(context);
        customP1 = p1;
        customP2 = p2;
        customP3 = p3;
        customP4 = p4;

    }

    @Override
    String getShapeType() {
        return "Circle";
    }
    public void onDraw(Canvas canvas)
    {

        Paint paint=new Paint();
        setShapeAlpha(100);
        paint.setColor(getrandomcolour());
        canvas.drawCircle(this.customP1,this.customP2, this.customP3, paint);

    }
    int getx(){
        int x = (int )(Math.random() * 200 + 150);
        return x;
    }

    int getradius(){

        int radius   = (int )(Math.random() * 60 + 40);
        return radius;

    }
    int getrandomcolour(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;


    }
}
