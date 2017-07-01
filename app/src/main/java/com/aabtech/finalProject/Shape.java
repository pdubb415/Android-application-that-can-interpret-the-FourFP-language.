package com.aabtech.finalProject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

abstract class Shape extends View
{

    private float _alpha;

    public Shape(Context context) {
        super(context);
    }

    void setShapeAlpha(float alpha)
    {
        _alpha=alpha;
        setAlpha(_alpha);
    }
    float getShapeAlpha()
    {
        return _alpha;
    }
    void removeShape()
    {
        setVisibility(GONE);
    }
    abstract ShapeType getShapeType();
    public abstract void onDraw(Canvas canvas);



}