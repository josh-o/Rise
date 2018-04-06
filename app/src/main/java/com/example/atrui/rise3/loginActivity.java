package com.example.atrui.rise3;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.os.StrictMode;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;



public class loginActivity extends AppCompatActivity{

    Dialog createItemDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        createItemDialog = new Dialog(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create new thread for network task
                new Thread() {
                    @Override
                    public void run() {

                        try {
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
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(loginActivity.this, "Logged In.", Toast.LENGTH_LONG).show();
                                    }
                                });

                            } else {
                                //run invalid login toast on mainthread
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(loginActivity.this, "Incorrect Login Credentials.", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registerPopup();
            }
        });
    }
    public void registerPopup(){
        createItemDialog.setContentView(R.layout.login_popup);
        ImageButton closeDialog = (ImageButton)createItemDialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createItemDialog.dismiss();
            }
        });
        //Register user logic
        Button registerUser = (Button)createItemDialog.findViewById(R.id.registerButtonPopup);
        registerUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                EditText employeeIDField = (EditText)createItemDialog.findViewById(R.id.employeeID);
                String employeeID = employeeIDField.getText().toString();

                EditText passwordField = (EditText)createItemDialog.findViewById(R.id.password);
                String password = passwordField.getText().toString();

                EditText f_nameField = (EditText)createItemDialog.findViewById(R.id.f_name);
                String f_name = f_nameField.getText().toString();

                EditText l_nameField = (EditText)createItemDialog.findViewById(R.id.l_name);
                String l_name = l_nameField.getText().toString();

                Spinner userType = (Spinner) createItemDialog.findViewById(R.id.userSpinner);
                String user_type = userType.getSelectedItem().toString();

                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc5.database.windows.net:1433;databaseName=Logn;user=jtoverby@riseinc5;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("INSERT INTO Login" +"(employeeID, password, f_name, l_name, user_type) VALUES" +
                            "(?,?,?,?,?)");

                    if(!employeeID.isEmpty() && !password.isEmpty() && !f_name.isEmpty() && !l_name.isEmpty() && !user_type.isEmpty()){
                        if(employeeID.length()<=20){
                            pst.setString(1, employeeID);
                        }
                        if(password.length()<=30) {
                            pst.setString(2, password);
                        }
                        if(f_name.length()<=15) {
                            pst.setString(3, f_name);
                        }
                        if(l_name.length()<=15) {
                            pst.setString(4, l_name);
                        }
                        pst.setString(5, user_type);
                    }
                    pst.executeUpdate();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                createItemDialog.dismiss();

            }
        });
        createItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        createItemDialog.show();

    }
}
