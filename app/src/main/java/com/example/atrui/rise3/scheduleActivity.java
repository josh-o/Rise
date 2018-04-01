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

    TextView textView1,textView2;
    String employeeID = "11111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //cheat and main thread network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        textView1 = (TextView) findViewById(R.id.textViewSchedule);
        textView2 = (TextView) findViewById(R.id.textViewSchedule2);

        Button viewScheduleButton = (Button) findViewById(R.id.viewSchedule);

        viewScheduleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {
                    DatePicker datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
                    int day = datePicker1.getDayOfMonth();
                    int month = datePicker1.getMonth()+1;
                    int year = datePicker1.getYear();
                    String dayS = Integer.toString(day);
                    String monthS = Integer.toString(month);
                    String yearS = Integer.toString(year);
                    String date = monthS+"/"+dayS+"/"+yearS;
                    textView1.setText("Schedule for: "+date);
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc3.database.windows.net:1433;databaseName=Schedule;user=jtoverby@riseinc3;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("Select * from Schedule where date='"+date+"' ");

                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        //textView2.setText(date);
                        textView2.setText(rs.getString(2));
                    }
                    //test 2


                } catch (ClassNotFoundException e) {
                 e.printStackTrace();
                 } catch (SQLException e) {
                 e.printStackTrace();
                 }

            }

        });
    }
}
