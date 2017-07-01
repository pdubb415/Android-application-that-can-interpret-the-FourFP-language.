package com.aabtech.finalProject;

import android.content.Context;

/**
 * Created by Arikat on 3/28/2016.
 */
public class ShapeFactory
{
    public shapes getShape(Context context, String shape)
    {
        shapes a=null;
        if(shape=="Rectangle")
        {
            //a=new Rectangle(context);

        }else{
            //a=new Circle(context);

        }

            return a;
    }
}
