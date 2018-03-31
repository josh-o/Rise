package com.example.atrui.rise3;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sourceforge.jtds.jdbc.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;



public class loginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Create new thread for network task
                new Thread() {
                    @Override
                    public void run() {

                        try
                        {
                            //SQL connection
                            Class.forName("net.sourceforge.jtds.jdbc.Driver");
                            String employeeID = ((EditText) findViewById(R.id.employeeIDField)).getText().toString();
                            String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();
                            String url = "jdbc:jtds:sqlserver://riseinc5.database.windows.net:1433;databaseName=Logn;user=jtoverby@riseinc5;password=Awesome33!;";
                            Connection connect = DriverManager.getConnection(url);
                            PreparedStatement pst = connect.prepareStatement("Select employeeID,password from Login where employeeID=? and password=?");
                            pst.setString(1, employeeID);
                            pst.setString(2, password);
                            ResultSet rs = pst.executeQuery();
                            //If credentials match database then go to main menu
                            if (rs.next()) {

                                Intent intent = new Intent(loginActivity.this, mainMenu.class);
                                startActivity(intent);

                                //run valid login toast on mainthread
                                runOnUiThread(new Runnable()
                                {
                                    public void run()
                                    {
                                        Toast.makeText(loginActivity.this,"Logged In.", Toast.LENGTH_LONG).show();
                                    }
                                });

                            } else {
                                //run invalid login toast on mainthread
                                runOnUiThread(new Runnable()
                                {
                                    public void run()
                                    {
                                        Toast.makeText(loginActivity.this,"Incorrect Login Credentials.", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch(ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        } catch(SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });
    }
}
