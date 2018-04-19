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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;


import java.sql.Statement;



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



        final Button button = findViewById(R.id.revbutt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //employee = (EditText) findViewById(R.id.tin1);
                date1= (EditText) findViewById(R.id.tin2);
                date2=(EditText) findViewById(R.id.tin3);

//                String employee1 = employee.getText().toString();
                String date11 = date1.getText().toString();
                String date22= date2.getText().toString();
                doTable(date11,date22);
                 //doTable("","");
            }
        });



    }


    public void doTable(String startdate,String enddate) {
        double totalrev=0;
        TableLayout table1 = (TableLayout) findViewById(R.id.table1); int childCount = table1.getChildCount();
        ((TextView)findViewById(R.id.tv21)).setText("OrderNumber");
        ((TextView)findViewById(R.id.tv21)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv21)).setTextColor(Color.WHITE);
/*
        ((TextView)findViewById(R.id.tv21)).setText("Employee");
        ((TextView)findViewById(R.id.tv21)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv21)).setTextColor(Color.WHITE);
*/
        ((TextView)findViewById(R.id.tv31)).setText("Order Total");
        ((TextView)findViewById(R.id.tv31)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv31)).setTextColor(Color.WHITE);

        ((TextView)findViewById(R.id.tv41)).setText("Date");
        ((TextView)findViewById(R.id.tv41)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv41)).setTextColor(Color.WHITE);
        // All rows deleted, Column headers stay
        if (childCount > 1) {
            table1.removeViews(1, childCount - 1);
        }
        try {
            //SQL connection
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
           // System.out.println("test1");
            String url = "jdbc:jtds:sqlserver://riseinc2.database.windows.net:1433;databaseName=Order;user=jtoverby@riseinc2;password=Awesome33!;";
            //System.out.println("test2");
            Connection connect = DriverManager.getConnection(url);
           /// System.out.println("test3");
            //Statement stmt = connect.createStatement( );
           PreparedStatement pst = connect.prepareStatement("SELECT * FROM Orders ");
            //PreparedStatement pst = connect.prepareStatement("SELECT * FROM Orders");
            //String sqlstr="SELECT * FROM Orders";
           // ResultSet rs= stmt.executeQuery(sqlstr);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            String temp="";
            String temp2="";
            String temp3="";
            double temp4=0;
            while (rs.next()) {
                temp=rs.getString(5);
                //String temp="02/02/2018";

                if(temp.length()==10 && temp.charAt(2)=='/'&&temp.charAt(5)=='/'){
                    temp2=""+temp.charAt(6)+temp.charAt(7)+temp.charAt(8)+temp.charAt(9)+'/'+temp.charAt(0)
                    +temp.charAt(1)+'/'+temp.charAt(3)+temp.charAt(4);
					/*
                    System.out.println("new");
                    System.out.println(temp2);
                    System.out.println(startdate);
                    System.out.println(enddate);
                    System.out.println(startdate.compareTo(enddate));
                    System.out.println(temp2.compareTo(startdate));
                    System.out.println(temp2.compareTo(enddate));
					*/
                    if( (startdate.compareTo(enddate)<=0) && (temp2.compareTo(startdate)>=0)&&(temp2.compareTo(enddate)<=0) ){
                       // System.out.println("test15");
                        TableRow tableRow = new TableRow(revenueActivity.this);
                        //format row
                        tableRow.setBackgroundColor(Color.BLACK);
                        tableRow.setPadding(0,0,0,3);
                        TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        tableParams.setMargins(0,0,3,0);

                        TextView c1 = new TextView(revenueActivity.this);
                        c1.setPadding(18,18,18,18);
                        c1.setLayoutParams(tableParams);
                        c1.setText(rs.getString(1));
                        tableRow.addView(c1);

                        TextView c2 = new TextView(revenueActivity.this);
                        c2.setPadding(18,18,18,18);
                        c2.setLayoutParams(tableParams);
                        c2.setText(rs.getString(4));
                        tableRow.addView(c2);

                        TextView c3 = new TextView(revenueActivity.this);
                        c3.setPadding(18,18,18,18);
                        c3.setLayoutParams(tableParams);
                        c3.setText(rs.getString(5));
                        tableRow.addView(c3);



                        if((count)%2!=0){
                            c1.setBackgroundColor(Color.LTGRAY);
                            c2.setBackgroundColor(Color.LTGRAY);
                            c3.setBackgroundColor(Color.LTGRAY);

                        }
                        if((count)%2==0){
                            c1.setBackgroundColor(Color.WHITE);
                            c2.setBackgroundColor(Color.WHITE);
                            c3.setBackgroundColor(Color.WHITE);
                        }
                        count++;
                        table1.addView(tableRow);
                        temp3=rs.getString(4);
                        temp4=Double.parseDouble(temp3);
                        totalrev+=temp4;

                    }

                }

            }
		TableRow tableRow = new TableRow(revenueActivity.this);


        tableRow.setPadding(0, 0, 0, 3);
        TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(0, 0, 3, 0);
       


        TextView c1 = new TextView(revenueActivity.this);
        c1.setPadding(18, 18, 18, 18);
        c1.setLayoutParams(tableParams);
        c1.setText("total revenue");
        c1.setBackgroundColor(Color.DKGRAY);
        c1.setTextColor(Color.WHITE);
        tableRow.addView(c1);
		
		TextView c2 = new TextView(revenueActivity.this);
        c2.setPadding(18, 18, 18, 18);
        c2.setLayoutParams(tableParams);
		temp=""+totalrev;
        c2.setText(temp);
        c2.setBackgroundColor(Color.DKGRAY);
        c2.setTextColor(Color.WHITE);
        tableRow.addView(c2);

        table1.addView(tableRow);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
/*

    String temp="";
        //System.out.println(empl+"  "+startdate+"  "+enddate);
        int childCount = table1.getChildCount();
        ((TextView)findViewById(R.id.tv11)).setText("OrderNumber");
        ((TextView)findViewById(R.id.tv11)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv11)).setTextColor(Color.WHITE);

        ((TextView)findViewById(R.id.tv21)).setText("Employee");
        ((TextView)findViewById(R.id.tv21)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv21)).setTextColor(Color.WHITE);

        ((TextView)findViewById(R.id.tv31)).setText("dates");
        ((TextView)findViewById(R.id.tv31)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv31)).setTextColor(Color.WHITE);

        ((TextView)findViewById(R.id.tv41)).setText("Order Total");
        ((TextView)findViewById(R.id.tv41)).setBackgroundColor(Color.DKGRAY);
        ((TextView)findViewById(R.id.tv41)).setTextColor(Color.WHITE);
        // All rows deleted, Column headers stay
        if (childCount > 1) {
            table1.removeViews(1, childCount - 1);
        }
        for(int i=0;i<length;i++){
           // System.out.println("test1");
          if((empl.compareTo("0")==0)||(emp[i].compareTo(empl)==0)){
             // System.out.println("test2");
              if(((dates2[i].compareTo(startdate))>=0)){
                   System.out.println("test3");
                 if(((dates2[i].compareTo(enddate))<=0)){
                    System.out.println("test5");

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
                        c4.setPadding(18, 18, 18, 18);
                        c4.setLayoutParams(tableParams);
                        c4.setText(Double.toString(rev[i]));
                        tableRow.addView(c4);

                     if (i % 2 == 0) {
                         c1.setBackgroundColor(Color.LTGRAY);
                         c2.setBackgroundColor(Color.LTGRAY);
                         c3.setBackgroundColor(Color.LTGRAY);
                         c4.setBackgroundColor(Color.LTGRAY);
                     }

                        table1.addView(tableRow);
                        totalrev+=rev[i];
                    // System.out.println("test6");
                    }
               }
            }

        }
        TableRow tableRow = new TableRow(revenueActivity.this);


        tableRow.setPadding(0, 0, 0, 3);
        TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(0, 0, 3, 0);
        TextView c2 = new TextView(revenueActivity.this);
        c2.setPadding(18, 18, 18, 18);
        c2.setLayoutParams(tableParams);
        c2.setText("total revenue");
        c2.setBackgroundColor(Color.DKGRAY);
        c2.setTextColor(Color.WHITE);
        tableRow.addView(c2);


        TextView c1 = new TextView(revenueActivity.this);
        c1.setPadding(18, 18, 18, 18);
        c1.setLayoutParams(tableParams);
        temp=""+totalrev;
        c1.setText(temp);
        c1.setBackgroundColor(Color.DKGRAY);
        c1.setTextColor(Color.WHITE);
        tableRow.addView(c1);


        table1.addView(tableRow);
        */

/*
        TextView c3 = new TextView(revenueActivity.this);
        c3.setPadding(18, 18, 18, 18);
        c3.setLayoutParams(tableParams);
        c3.setText(dates2[i]);
        tableRow.addView(c3);

        TextView c4 = new TextView(revenueActivity.this);
        c4.setPadding(18, 18, 18, 18);
        c4.setLayoutParams(tableParams);
        c4.setText(Double.toString(rev[i]));
        tableRow.addView(c4);
*/
    }
}
