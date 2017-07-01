package com.aabtech.finalProject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Abdullah Arikat
 *  3/29/2016.

 */
public class clear_canvas extends View
{
    public clear_canvas(Context context)
    {
        super(context);
    }



    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);

    }

}
