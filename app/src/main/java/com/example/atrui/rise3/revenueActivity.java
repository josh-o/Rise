package com.example.atrui.rise3;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.widget.Button;
import java.util.*;
import java.text.*;


public class revenueActivity extends AppCompatActivity {
    Dialog createItemDialog;
    int length=10;
    String emp[]=new String[length];
    double rev[]=new double[length];
    Date dates[]=new Date[length];
    String dates2[]=new String[length];

    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    EditText employee;
    EditText date1;
    EditText date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);
        //create sample data

        for(int i=0;i<(length/2);i++){
            emp[i]="1";
        }
        for(int i=(length/2);i<length;i++){
            emp[i]="2";
        }
        double sampleRev=10.2;
        for(int i=0;i<length;i++){
            rev[i]=sampleRev;

            sampleRev+=0.5;
        }
        dates2[0]=("2018/02/01");
        dates2[1]=("2018/02/01");
        dates2[2]=("2018/02/01");
        dates2[3]=("2018/02/02");
        dates2[4]=("2018/02/02");
        dates2[5]=("2018/02/02");
        dates2[6]=("2018/02/02");
        dates2[7]=("2018/02/02");
        dates2[8]=("2018/02/01");
        dates2[9]=("2018/02/01");
        /*
        try {
            dates[0]=df.parse("02/01/2018");
            dates[1]=df.parse("02/01/2018");
            dates[2]=df.parse("02/01/2018");
            dates[3]=df.parse("02/02/2018");
            dates[4]=df.parse("02/02/2018");
            dates[5]=df.parse("02/02/2018");
            dates[6]=df.parse("02/02/2018");
            dates[7]=df.parse("02/02/2018");
            dates[8]=df.parse("02/01/2018");
            dates[9]=df.parse("02/01/2018");

        }
        catch(ParseException e){

            e.printStackTrace();
        }
*/
        ((TextView)findViewById(R.id.tv11)).setText("OrderNumber");

        ((TextView)findViewById(R.id.tv21)).setText("Employee");

        ((TextView)findViewById(R.id.tv31)).setText("dates");
        doTable("","","");
        ((TextView)findViewById(R.id.tv41)).setText("Order Total");
       // doTable(".","","");
        /*
        final Button button = findViewById(R.id.revbutt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                employee = (EditText) findViewById(R.id.tin1);
                date1= (EditText) findViewById(R.id.tin2);
                date2=(EditText) findViewById(R.id.tin1);

                String employee1 = employee.getText().toString();
                String date11 = date1.getText().toString();
                String date22= date2.getText().toString();
                doTable(employee1,date11,date22);

            }
        });
*/


    }


    public void doTable(String empl,String startdate,String enddate) {

        TableLayout table1 = (TableLayout) findViewById(R.id.table1);
       //Date start=null;
        //Date end=null;

/*
        try {
            start = df.parse(startdate);
            end = df.parse(enddate);
        }

        catch(ParseException e){

            e.printStackTrace();
        }

*/String temp="";
        for(int i=0;i<length;i++){
          //if((empl=="0")||(emp[i]==empl)){
              // if((dates2[i].equals(startdate))||((dates2[i].compareTo(startdate))>0)){
                //  if((dates[i].equals(enddate))||((dates2[i].compareTo(enddate))<0)){
                        TableRow tableRow = new TableRow(revenueActivity.this);


                        tableRow.setPadding(0, 0, 0, 3);
                        TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        tableParams.setMargins(0, 0, 3, 0);

                        TextView c1 = new TextView(revenueActivity.this);
                        c1.setPadding(18, 18, 18, 18);
                        c1.setLayoutParams(tableParams);
                        temp=""+(i+1);
                        c1.setText(temp);
                        tableRow.addView(c1);

                        TextView c2 = new TextView(revenueActivity.this);
                        c2.setPadding(18, 18, 18, 18);
                        c2.setLayoutParams(tableParams);
                        c2.setText(emp[i]);
                        tableRow.addView(c2);

                        TextView c3 = new TextView(revenueActivity.this);
                        c3.setPadding(18, 18, 18, 18);
                        c3.setLayoutParams(tableParams);
                        c3.setText(dates2[i]);
                        tableRow.addView(c3);

                        TextView c4 = new TextView(revenueActivity.this);
                        c3.setPadding(18, 18, 18, 18);
                        c3.setLayoutParams(tableParams);
                        c3.setText(Double.toString(rev[i]));
                        tableRow.addView(c4);
                        table1.addView(tableRow);

                    }
         //       }
        //    }

      //  }


    }
}
