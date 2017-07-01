package com.aabtech.finalProject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<shapes> check_shape = new ArrayList<shapes>();
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();



        text=(TextView)findViewById(R.id.textView);
        final RelativeLayout d_canvas=(RelativeLayout)findViewById(R.id.draw_canvas);

       final ShapeFactory sh=new ShapeFactory();



        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.



        if(value.equals("1")){


            int para1 = Integer.parseInt(intent.getStringExtra("para1"));
            int para2 = Integer.parseInt(intent.getStringExtra("para2"));
            int para3 = Integer.parseInt(intent.getStringExtra("para3"));
            int para4 = Integer.parseInt(intent.getStringExtra("para4"));

            adjustShapeAlpha();
            shapes cie_dia= new Circle(getApplicationContext(), para1, para2, para3, para4);
            d_canvas.addView(cie_dia);
            check_shape.add(cie_dia);
            updateShapeCount();

        }



        if(value.equals("2")){


            int para1 = Integer.parseInt(intent.getStringExtra("para1"));
            int para2 = Integer.parseInt(intent.getStringExtra("para2"));
            int para3 = Integer.parseInt(intent.getStringExtra("para3"));
            int para4 = Integer.parseInt(intent.getStringExtra("para4"));
            int para5 = Integer.parseInt(intent.getStringExtra("para5"));

            adjustShapeAlpha();
            shapes cie_dia= new Rectangle(getApplicationContext(), para1, para2, para3, para4, para5);
            check_shape.add(cie_dia);
            d_canvas.addView(cie_dia);
            updateShapeCount();

        }






    }






    void adjustShapeAlpha()
    {
        for (int i = 0; i < check_shape.size(); i++)
        {

            float current_alpha=check_shape.get(i).getShapeAlpha();
            if(current_alpha<10)
            {
               // check_shape.get(i).removeShape();
               // check_shape.remove(check_shape.get(i));
            }
            else{
                current_alpha=current_alpha-30;
                check_shape.get(i).setShapeAlpha(current_alpha);

            }


        }




    }
    void updateShapeCount()
    {
        int rect_counter=0;
        int cir_counter=0;
        for(shapes sh:check_shape)
        {


            String a=sh.getShapeType();
            if(!(sh.getVisibility()==View.GONE)) {
                if (a.equals("Rectangle")) {
                    rect_counter++;
                } else {
                    cir_counter++;
                }
            }
        }
        text.setText("Rectangles="+rect_counter+"Circles="+cir_counter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
