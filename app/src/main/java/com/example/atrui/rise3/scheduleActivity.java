package com.example.atrui.rise3;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class scheduleActivity extends AppCompatActivity {

    TextView empOne,empOneShift, empTwo, empTwoShift, empThree, empThreeShift,empFour, empFourShift, notesBox;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //cheat and main thread network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        empOne = (TextView) findViewById(R.id.one);
        empOneShift = (TextView) findViewById(R.id.oneShift);
        empTwo = (TextView) findViewById(R.id.two);
        empTwoShift = (TextView) findViewById(R.id.twoShift);
        empThree = (TextView) findViewById(R.id.three);
        empThreeShift = (TextView) findViewById(R.id.threeShift);
        empFour = (TextView) findViewById(R.id.four);
        empFourShift = (TextView) findViewById(R.id.fourShift);

        notesBox = (TextView) findViewById(R.id.notesBox);

        Button viewScheduleButton = (Button) findViewById(R.id.viewSchedule);

        viewScheduleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                empOne.setText("No Shifts Scheduled");
                empOneShift.setText("");
                empTwo.setText("");
                empTwoShift.setText("");
                empThree.setText("");
                empThreeShift.setText("");
                empFour.setText("");
                empFourShift.setText("");
                notesBox.setText("");

                try {
                    DatePicker calendar = (DatePicker) findViewById(R.id.calendar);

                    int day = calendar.getDayOfMonth();
                    int month = calendar.getMonth()+1;
                    int year = calendar.getYear();
                    String dayS = Integer.toString(day);
                    String monthS = Integer.toString(month);
                    String yearS = Integer.toString(year);
                    String date = monthS+"/"+dayS+"/"+yearS;

                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc3.database.windows.net:1433;databaseName=Schedule;user=jtoverby@riseinc3;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("Select * from Schedule where date='"+date+"' ");
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        empOne.setText(rs.getString(2));
                        empOneShift.setText(rs.getString(3));
                        empTwo.setText(rs.getString(4));
                        empTwoShift.setText(rs.getString(5));
                        empThree.setText(rs.getString(6));
                        empThreeShift.setText(rs.getString(7));
                        empFour.setText(rs.getString(8));
                        empFourShift.setText(rs.getString(9));
                        notesBox.setText(rs.getString(10));
                    }

                } catch (ClassNotFoundException e) {
                 e.printStackTrace();
                 } catch (SQLException e) {
                 e.printStackTrace();
                 }

            }

        });
    }
}
