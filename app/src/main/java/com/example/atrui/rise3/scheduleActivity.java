package com.example.atrui.rise3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Dialog createItemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        createItemDialog = new Dialog(this);

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

                displaySchedule();
            }

        });
    }
    public void editSchedulePopup(View v) {

        createItemDialog.setContentView(R.layout.schedule_popup);
        //Get values from each field in schedule popup
        EditText employee1Field = (EditText) createItemDialog.findViewById(R.id.employee1Field);
        //String employee1 = employee1Field.getText().toString();

        EditText shift1Field = (EditText) createItemDialog.findViewById(R.id.shift1Field);
        //String shift1 = shift1Field.getText().toString();

        EditText employee2Field = (EditText) createItemDialog.findViewById(R.id.employee2Field);
        //String employee2 = employee2Field.getText().toString();

        EditText shift2Field = (EditText) createItemDialog.findViewById(R.id.shift2Field);
        // String shift2 = shift2Field.getText().toString();

        EditText employee3Field = (EditText) createItemDialog.findViewById(R.id.employee3Field);
        //String employee3 = employee3Field.getText().toString();

        EditText shift3Field = (EditText) createItemDialog.findViewById(R.id.shift3Field);
        // String shift3 = shift3Field.getText().toString();

        EditText employee4Field = (EditText) createItemDialog.findViewById(R.id.employee4Field);
        //String employee4 = employee4Field.getText().toString();

        EditText shift4Field = (EditText) createItemDialog.findViewById(R.id.shift4Field);
        // String shift4 = shift4Field.getText().toString();

        EditText notes = (EditText) createItemDialog.findViewById(R.id.itemDetails);
        // String shift4 = shift4Field.getText().toString();


        try {
            DatePicker calendar = (DatePicker) findViewById(R.id.calendar);
            int day = calendar.getDayOfMonth();
            int month = calendar.getMonth() + 1;
            int year = calendar.getYear();
            String dayS = Integer.toString(day);
            String monthS = Integer.toString(month);
            String yearS = Integer.toString(year);
            String date = monthS + "/" + dayS + "/" + yearS;
            //SQL connection
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://riseinc3.database.windows.net:1433;databaseName=Schedule;user=jtoverby@riseinc3;password=Awesome33!;";
            Connection connect = DriverManager.getConnection(url);
            PreparedStatement pst = connect.prepareStatement("Select * from Schedule WHERE date = '" + date + "'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                employee1Field.setText(rs.getString(2));
                shift1Field.setText(rs.getString(3));
                employee2Field.setText(rs.getString(4));
                shift2Field.setText(rs.getString(5));
                employee3Field.setText(rs.getString(6));
                shift3Field.setText(rs.getString(7));
                employee4Field.setText(rs.getString(8));
                shift4Field.setText(rs.getString(9));
                notes.setText(rs.getString(10));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //createItemDialog.dismiss();
        /**
         //createItemDialog.setContentView(R.layout.schedule_popup);
         empOne.setText("No Shifts Scheduled");
         empOneShift.setText("");
         empTwo.setText("");
         empTwoShift.setText("");
         empThree.setText("");
         empThreeShift.setText("");
         empFour.setText("");
         empFourShift.setText("");
         notesBox.setText("");
         **/
        Button updateSchedule = (Button) createItemDialog.findViewById(R.id.update);
        updateSchedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Get values from each field in schedule popup
                EditText employee1Field = (EditText) createItemDialog.findViewById(R.id.employee1Field);
                String employee1 = employee1Field.getText().toString();

                EditText shift1Field = (EditText) createItemDialog.findViewById(R.id.shift1Field);
                String shift1 = shift1Field.getText().toString();

                EditText employee2Field = (EditText) createItemDialog.findViewById(R.id.employee2Field);
                String employee2 = employee2Field.getText().toString();

                EditText shift2Field = (EditText) createItemDialog.findViewById(R.id.shift2Field);
                String shift2 = shift2Field.getText().toString();

                EditText employee3Field = (EditText) createItemDialog.findViewById(R.id.employee3Field);
                String employee3 = employee3Field.getText().toString();

                EditText shift3Field = (EditText) createItemDialog.findViewById(R.id.shift3Field);
                String shift3 = shift3Field.getText().toString();

                EditText employee4Field = (EditText) createItemDialog.findViewById(R.id.employee4Field);
                String employee4 = employee4Field.getText().toString();

                EditText shift4Field = (EditText) createItemDialog.findViewById(R.id.shift4Field);
                String shift4 = shift4Field.getText().toString();

                EditText notesField = (EditText) createItemDialog.findViewById(R.id.itemDetails);
                String notes = notesField.getText().toString();

                try {

                    DatePicker calendar = (DatePicker) findViewById(R.id.calendar);
                    int day = calendar.getDayOfMonth();
                    int month = calendar.getMonth() + 1;
                    int year = calendar.getYear();
                    String dayS = Integer.toString(day);
                    String monthS = Integer.toString(month);
                    String yearS = Integer.toString(year);
                    String date = monthS + "/" + dayS + "/" + yearS;
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc3.database.windows.net:1433;databaseName=Schedule;user=jtoverby@riseinc3;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("UPDATE Schedule SET date=?, employee1=?, shift1=?, employee2=?, shift2=?, employee3=?, shift3=?, employee4=?, shift4=?, notes=? WHERE date='"+date+"'");


                    pst.setString(1, date);
                    pst.setString(2, employee1);
                    pst.setString(3, shift1);
                    pst.setString(4, employee2);
                    pst.setString(5, shift2);
                    pst.setString(6, employee3);
                    pst.setString(7, shift3);
                    pst.setString(8, employee4);
                    pst.setString(9, shift4);
                    pst.setString(10, notes);


                    pst.executeUpdate();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                createItemDialog.dismiss();
                displaySchedule();
            }
        });
        createItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        createItemDialog.show();


    }
    public void displaySchedule() {
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

}
