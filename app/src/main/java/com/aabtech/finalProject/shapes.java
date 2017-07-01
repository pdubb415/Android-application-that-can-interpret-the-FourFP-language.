package com.aabtech.finalProject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;


abstract class shapes extends View
{

    private float _alpha;

    public shapes(Context context) {
        super(context);
    }

    void setShapeAlpha(float alpha)
    {
        _alpha=alpha;
    }
    float getShapeAlpha()
    {
        return _alpha;
    }
    void removeShape(){
    }
    abstract String getShapeType();
    public abstract void onDraw(Canvas canvas);



}