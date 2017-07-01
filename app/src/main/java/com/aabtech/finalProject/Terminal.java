package com.aabtech.finalProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Terminal extends AppCompatActivity {


    public HashMap hm = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button AppendButton = (Button) findViewById(R.id.appendButton);
        final TextView CommandList = (TextView) findViewById(R.id.commandList);
        CommandList.setMovementMethod(new ScrollingMovementMethod());
        final EditText NewCommand = (EditText) findViewById(R.id.editText);

        AppendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommandList.setText("");
                String check = NewCommand.getText().toString().trim();


                if(check.contains("(")|| check.contains("+") || check.contains("-") || check.contains("*") || check.contains("/")){

                    CommandList.setText("This is an expression ");
                    //check = check.replaceAll(";", "");
                    String postfixStr1 = PostfixInfixCalculator.toPostfix(check);
                    int result = PostfixInfixCalculator.computePostfix(postfixStr1);
                    CommandList.setText("Expression Solved \n "+ check + " = " + result);
                }
                else if(check.startsWith("#")){
                    CommandList.setText("its a comment ");

                }else if(check.matches("(.+);")){
                    // Valid

                    if(check.startsWith("int")){
                        if(check.matches("(\\S)*int ([a-z])+(\\s)*(=)(\\s)*([0-9])+(\\s)*;")) {

                            String variableName = null;
                            int variableValue=0;

                            Pattern pattern = Pattern.compile("\\s*int\\s+([a-z]{1,})\\s*=\\s*([0-9]{1,})\\s*;");
                            Matcher matcher = pattern.matcher(check);
                            if (matcher.find())
                            {

                                variableName = matcher.group(1).toString();
                                variableValue = Integer.parseInt(matcher.group(2).toString());
                            }


                            if(variableName != null) {
                                hm.put(variableName, variableValue);
                            }else{

                                Toast.makeText(getApplicationContext(), "Variable Name Null !!! =)",
                                        Toast.LENGTH_LONG).show();
                            }


                            LinkedList ll = new LinkedList();
                            Iterator itr = hm.keySet().iterator();
                            while(itr.hasNext()) {
                                String key =  itr.next().toString();
                                ll.add(key);
                            }


                            CommandList.setText("Variable initilized Successfully  \n" + ll);



                        }

                    }else if(check.startsWith("circle")){


                        if(check.matches("(\\s)*(circle)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)*;")) {
                            CommandList.setText("its a valid circle ");
                            int a=200;
                            int b=200;
                            int c=200;
                            int d=2;
                            boolean flagCircle = true;
                            int[] values = new int[4];


                            Pattern pattern = Pattern.compile("\\s*circle\\s+([0-9]+|[a-z]+)\\s+([0-9]+|[a-z]+)\\s+([0-9]+|[a-z]+)\\s+([0-9]+|[a-z]+)\\s*;");
                            Matcher matcher = pattern.matcher(check);
                            if (matcher.find()) {

                                if(matcher.groupCount() == 4){
                                CommandList.setText("its a valid circle " + matcher.group(1).toString() + " " + matcher.group(2).toString() + " " + matcher.group(3).toString() + " " + matcher.group(4).toString());

                                for (int x = 1; x < 5; x++) {
                                    if (matcher.group(x).toString().matches("[0-9]+") == true) {
                                        // if its a simple number between 0-9
                                        values[x - 1] = Integer.parseInt(matcher.group(x).toString());
                                    } else {

                                        //its a variable
                                        if (hm.containsKey(matcher.group(x).toString())) {
                                            values[x - 1] = Integer.parseInt(hm.get(matcher.group(x).toString()).toString());
                                            String currentText = CommandList.getText().toString() + "\n" + matcher.group(x).toString() + " = " + hm.get(matcher.group(x).toString());
                                            CommandList.setText(currentText);
                                        } else {

                                            String currentText = CommandList.getText().toString() + "\n" + " But variable -> " + matcher.group(x).toString() + "  does not exist";
                                            CommandList.setText(currentText);
                                            flagCircle = false;

                                        }
                                    }
                                }


                            }else{

                                    CommandList.setText("Circle Needs 4 arguments to draw a Circle ");
                                }
                            }





                            if(flagCircle == true) {

                                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                myIntent.putExtra("key", "1"); // tells which function to perform circle/reactangle
                                myIntent.putExtra("para1", values[0]+""); //Optional parameters
                                myIntent.putExtra("para2", values[1]+""); //Optional parameters
                                myIntent.putExtra("para3", values[2]+""); //Optional parameters
                                myIntent.putExtra("para4", values[3] + ""); //Optional parameters

                                String currentText = CommandList.getText().toString() + "\n" + values[0]+" "+values[1]+" "+values[2]+" "+values[3] ;
                                CommandList.setText(currentText);

                                startActivity(myIntent);


                            }

                        }



                    }else if(check.startsWith("rect")){

                        if(check.matches("(\\s)*(rect)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)+([0-9]+|[a-z]+)(\\s)*;"))
                        {
                            CommandList.setText("its a valid rectangle  ");
                            int a=50;
                            int b=50;
                            int c=50;
                            int d=50;
                            int e=2;


                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            myIntent.putExtra("key", "2"); // tells which function to perform circle/reactangle
                            myIntent.putExtra("para1", a+""); //Optional parameters
                            myIntent.putExtra("para2", b+""); //Optional parameters
                            myIntent.putExtra("para3", c+""); //Optional parameters
                            myIntent.putExtra("para4", d + ""); //Optional parameters
                            myIntent.putExtra("para5", e + ""); //Optional parameters

                            startActivity(myIntent);



                        }
                    }


                } else{

                    CommandList.setText(" Semi colon is missing  ");
                    // invalid because of semi colon
                }



//                String currentText = CommandList.getText().toString() + "\n" + NewCommand.getText().toString();
//                CommandList.setText(currentText);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });






    }

}
