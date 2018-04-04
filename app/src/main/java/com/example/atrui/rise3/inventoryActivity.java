package com.example.atrui.rise3;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class inventoryActivity extends AppCompatActivity {
    Dialog createItemDialog;


    public void createNewItemPopup(View v){
        createItemDialog.setContentView(R.layout.inventory_popup);
        ImageButton closeDialog = (ImageButton)createItemDialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createItemDialog.dismiss();
            }
        });
        //Create new item logic
        Button createItem = (Button)createItemDialog.findViewById(R.id.createItem);
        createItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Get values from each field in inventory popup
                EditText itemNumberField = (EditText)createItemDialog.findViewById(R.id.itemNumber);
                String itemNumber = itemNumberField.getText().toString();

                EditText itemNameField = (EditText)createItemDialog.findViewById(R.id.itemName);
                String itemName = itemNameField.getText().toString();

                EditText qtyAvailableField = (EditText)createItemDialog.findViewById(R.id.qtyAvailable);
                String qtyAvailable = qtyAvailableField.getText().toString();

                EditText qtyonOrderField = (EditText)createItemDialog.findViewById(R.id.qtyonOrder);
                String qtyonOrder = qtyonOrderField.getText().toString();

                EditText itemDetailsField = (EditText)createItemDialog.findViewById(R.id.itemDetails);
                String itemDetails = itemDetailsField.getText().toString();


                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc.database.windows.net:1433;databaseName=rise;user=jtoverby@riseinc;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("INSERT INTO Inventory" +"(item_Number, item_Name, qty_Available, qty_onOrder, item_Details) VALUES" +
                            "(?,?,?,?,?)");

                    if(!itemNumber.isEmpty() && !itemName.isEmpty() && !qtyAvailable.isEmpty() && !qtyonOrder.isEmpty()){
                    if(itemNumber.length()<=10){
                        pst.setString(1, itemNumber);
                    }
                    if(itemName.length()<=30) {
                        pst.setString(2, itemName);
                    }
                    if(itemNumber.length()<=10) {
                        pst.setString(3, qtyAvailable);
                    }
                    if(itemNumber.length()<=10) {
                        pst.setString(4, qtyonOrder);
                    }
                    if(itemDetails.length()<=40) {
                        pst.setString(5, itemDetails);
                    }
                    }
                    pst.executeUpdate();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                createItemDialog.dismiss();
                displayTable();
            }
        });
        createItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        createItemDialog.show();

    }

    public void updateItemPopup(View v){
        createItemDialog.setContentView(R.layout.inventory_popup2);
        ImageButton closeDialog = (ImageButton)createItemDialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createItemDialog.dismiss();
            }
        });
        Button selectItem = (Button)createItemDialog.findViewById(R.id.select);
        selectItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Get values from each field in inventory popup
                EditText updateItemNumberField = (EditText)createItemDialog.findViewById(R.id.updateItemNumber);
                String updateItemNumber = updateItemNumberField.getText().toString();

                EditText itemNumberField = (EditText)createItemDialog.findViewById(R.id.itemNumber);
                //String itemNumber = itemNumberField.getText().toString();

                EditText itemNameField = (EditText)createItemDialog.findViewById(R.id.itemName);
                //String itemName = itemNameField.getText().toString();

                EditText qtyAvailableField = (EditText)createItemDialog.findViewById(R.id.qtyAvailable);
                //String qtyAvailable = qtyAvailableField.getText().toString();

                EditText qtyonOrderField = (EditText)createItemDialog.findViewById(R.id.qtyonOrder);
                //String qtyonOrder = qtyonOrderField.getText().toString();

                EditText itemDetailsField = (EditText)createItemDialog.findViewById(R.id.itemDetails);
                //String itemDetails = itemDetailsField.getText().toString();


                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc.database.windows.net:1433;databaseName=rise;user=jtoverby@riseinc;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("Select * from Inventory WHERE item_Number = '"+updateItemNumber+"'");
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {

                        itemNumberField.setText(rs.getString(1));
                        itemNameField.setText(rs.getString(2));
                        qtyAvailableField.setText(rs.getString(3));
                        qtyonOrderField.setText(rs.getString(4));
                        itemDetailsField.setText(rs.getString(5));

                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        });
        //Update item logic
        Button updateItem = (Button)createItemDialog.findViewById(R.id.update);
        updateItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Get values from each field in inventory popup
                EditText itemNumberField = (EditText)createItemDialog.findViewById(R.id.itemNumber);
                String itemNumber = itemNumberField.getText().toString();

                EditText itemNameField = (EditText)createItemDialog.findViewById(R.id.itemName);
                String itemName = itemNameField.getText().toString();

                EditText qtyAvailableField = (EditText)createItemDialog.findViewById(R.id.qtyAvailable);
                String qtyAvailable = qtyAvailableField.getText().toString();

                EditText qtyonOrderField = (EditText)createItemDialog.findViewById(R.id.qtyonOrder);
                String qtyonOrder = qtyonOrderField.getText().toString();

                EditText itemDetailsField = (EditText)createItemDialog.findViewById(R.id.itemDetails);
                String itemDetails = itemDetailsField.getText().toString();


                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc.database.windows.net:1433;databaseName=rise;user=jtoverby@riseinc;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("INSERT INTO Inventory" +"(item_Number, item_Name, qty_Available, qty_onOrder, item_Details) VALUES" +
                            "(?,?,?,?,?)");

                    if(!itemNumber.isEmpty() && !itemName.isEmpty() && !qtyAvailable.isEmpty() && !qtyonOrder.isEmpty()){
                        if(itemNumber.length()<=10){
                            pst.setString(1, itemNumber);
                        }
                        if(itemName.length()<=30) {
                            pst.setString(2, itemName);
                        }
                        if(itemNumber.length()<=10) {
                            pst.setString(3, qtyAvailable);
                        }
                        if(itemNumber.length()<=10) {
                            pst.setString(4, qtyonOrder);
                        }
                        if(itemDetails.length()<=40) {
                            pst.setString(5, itemDetails);
                        }
                    }
                    pst.executeUpdate();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                createItemDialog.dismiss();
                displayTable();
            }
        });
        createItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        createItemDialog.show();

    }

    void updateTable(final String col1, final String col2, final String col3, final String col4, final String col5){

        runOnUiThread(new Runnable()
        {
            public void run()
            {
                //((TableRow)findViewById(R.id.row1)).setBackgroundColor(Color.BLACK);
                ((TextView)findViewById(R.id.textView11)).setBackgroundColor(Color.parseColor("#3f51b5"));
                ((TextView)findViewById(R.id.textView11)).setTextColor(Color.WHITE);
                ((TextView)findViewById(R.id.textView12)).setBackgroundColor(Color.parseColor("#3f51b5"));
                ((TextView)findViewById(R.id.textView12)).setTextColor(Color.WHITE);
                ((TextView)findViewById(R.id.textView13)).setBackgroundColor(Color.parseColor("#3f51b5"));
                ((TextView)findViewById(R.id.textView13)).setTextColor(Color.WHITE);
                ((TextView)findViewById(R.id.textView14)).setBackgroundColor(Color.parseColor("#3f51b5"));
                ((TextView)findViewById(R.id.textView14)).setTextColor(Color.WHITE);
                ((TextView)findViewById(R.id.textView15)).setBackgroundColor(Color.parseColor("#3f51b5"));
                ((TextView)findViewById(R.id.textView15)).setTextColor(Color.WHITE);
                //Get column names from database
                ((TextView)findViewById(R.id.textView11)).setText(col1);
                ((TextView)findViewById(R.id.textView12)).setText(col2);
                ((TextView)findViewById(R.id.textView13)).setText(col3);
                ((TextView)findViewById(R.id.textView14)).setText(col4);
                ((TextView)findViewById(R.id.textView15)).setText(col5);
            }
        });
    }

    public void displayTable() {

        TableLayout t1 = (TableLayout) findViewById(R.id.t1);
        int childCount = t1.getChildCount();

        // All rows deleted, Column headers stay
        if (childCount > 1) {
            t1.removeViews(1, childCount - 1);
        }

        //Display table with all inventory
        try {
            //SQL connection
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //String employeeID = ((EditText) findViewById(R.id.employeeIDField)).getText().toString();
            //String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();
            String url = "jdbc:jtds:sqlserver://riseinc.database.windows.net:1433;databaseName=rise;user=jtoverby@riseinc;password=Awesome33!;";
            Connection connect = DriverManager.getConnection(url);
            PreparedStatement pst = connect.prepareStatement("Select * from Inventory");
            //pst.setString(1, employeeID);
            //pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            //Get column names from database
            String col1 = rsmd.getColumnName(1);
            String col2 = rsmd.getColumnName(2);
            String col3 = rsmd.getColumnName(3);
            String col4 = rsmd.getColumnName(4);
            String col5 = rsmd.getColumnName(5);


            updateTable(col1, col2, col3, col4, col5);

            //////////////////////////////////////

            /**Get column names from database
             ((TextView)findViewById(R.id.textView11)).setText(rsmd.getColumnName(1));
             ((TextView)findViewById(R.id.textView12)).setText(rsmd.getColumnName(2));
             ((TextView)findViewById(R.id.textView13)).setText(rsmd.getColumnName(3));
             ((TextView)findViewById(R.id.textView14)).setText(rsmd.getColumnName(4));
             ((TextView)findViewById(R.id.textView15)).setText(rsmd.getColumnName(5));
             **/
            //
            //TextView team1,team2,scr1,scr2;
            //TableLayout t2 = new TableLayout(this);
            //TableRow tr;
            //
            //team1 = (TextView)findViewById(R.id.tVTeam1);
            //team2 = (TextView)findViewById(R.id.tVTeam2);
            //TableLayout t1 = (TableLayout) findViewById(R.id.t1);
            //TableLayout tableLayout = new TableLayout(this);
            int count = 0;

            while (rs.next()) {
                TableRow tableRow = new TableRow(inventoryActivity.this);
                //format row
                tableRow.setBackgroundColor(Color.BLACK);
                tableRow.setPadding(0, 0, 0, 3);
                TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(0, 0, 3, 0);

                TextView c1 = new TextView(inventoryActivity.this);
                c1.setPadding(18, 18, 18, 18);
                c1.setLayoutParams(tableParams);
                c1.setText(rs.getString(1));
                tableRow.addView(c1);

                TextView c2 = new TextView(inventoryActivity.this);
                c2.setPadding(18, 18, 18, 18);
                c2.setLayoutParams(tableParams);
                c2.setText(rs.getString(2));
                tableRow.addView(c2);

                TextView c3 = new TextView(inventoryActivity.this);
                c3.setPadding(18, 18, 18, 18);
                c3.setLayoutParams(tableParams);
                c3.setText(rs.getString(3));
                tableRow.addView(c3);

                TextView c4 = new TextView(inventoryActivity.this);
                c4.setPadding(18, 18, 18, 18);
                c4.setLayoutParams(tableParams);
                c4.setText(rs.getString(4));
                tableRow.addView(c4);

                TextView c5 = new TextView(inventoryActivity.this);
                c5.setPadding(18, 18, 18, 18);
                c5.setLayoutParams(tableParams);
                c5.setText(rs.getString(5));
                tableRow.addView(c5);


                if ((count) % 2 != 0) {
                    c1.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    //c1.setTextColor(Color.WHITE);
                    c2.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    //c2.setTextColor(Color.WHITE);
                    c3.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    //c3.setTextColor(Color.WHITE);
                    c4.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    //c4.setTextColor(Color.WHITE);
                    c5.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    //c5.setTextColor(Color.WHITE);
                }
                if ((count) % 2 == 0) {
                    c1.setBackgroundColor(Color.WHITE);
                    c2.setBackgroundColor(Color.WHITE);
                    c3.setBackgroundColor(Color.WHITE);
                    c4.setBackgroundColor(Color.WHITE);
                    c5.setBackgroundColor(Color.WHITE);
                }
                count++;
                t1.addView(tableRow);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        createItemDialog = new Dialog(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        displayTable();

        //Search table logic
        ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TableLayout t1 = (TableLayout) findViewById(R.id.t1);
                int childCount = t1.getChildCount();

                // All rows deleted, Column headers stay
                if (childCount > 1) {
                    t1.removeViews(1, childCount - 1);
                }



                //t1.removeAllViews();

                String itemNum = null;
                String itemNam = null;
                String qtyAvail = null;
                String qtyonOrd = null;

                EditText searchBox = (EditText) findViewById(R.id.searchBox);
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
                String searchType = spinner1.getSelectedItem().toString();

                if ("itemNumber".equals(searchType)) {
                    itemNum = searchBox.getText().toString();
                }
                if ("itemName".equals(searchType)) {
                    itemNam = searchBox.getText().toString();
                }
                if ("qtyAvailable".equals(searchType)) {
                    qtyAvail = searchBox.getText().toString();
                }
                if ("qtyonOrder".equals(searchType)) {
                    qtyonOrd = searchBox.getText().toString();
                }

                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc.database.windows.net:1433;databaseName=rise;user=jtoverby@riseinc;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("Select * from Inventory where item_Number=? or item_Name=? or qty_Available=? or qty_onOrder=?");
                    pst.setString(1,itemNum);
                    pst.setString(2,itemNam);
                    pst.setString(3,qtyAvail);
                    pst.setString(4,qtyonOrd);

                    ResultSet rs = pst.executeQuery();

                    int count = 0;
                    while (rs.next()) {
                        TableRow tableRow = new TableRow(inventoryActivity.this);
                        //format row
                        tableRow.setBackgroundColor(Color.BLACK);
                        tableRow.setPadding(0,0,0,3);
                        TableRow.LayoutParams tableParams = new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        tableParams.setMargins(0,0,3,0);

                        TextView c1 = new TextView(inventoryActivity.this);
                        c1.setPadding(18,18,18,18);
                        c1.setLayoutParams(tableParams);
                        c1.setText(rs.getString(1));
                        tableRow.addView(c1);

                        TextView c2 = new TextView(inventoryActivity.this);
                        c2.setPadding(18,18,18,18);
                        c2.setLayoutParams(tableParams);
                        c2.setText(rs.getString(2));
                        tableRow.addView(c2);

                        TextView c3 = new TextView(inventoryActivity.this);
                        c3.setPadding(18,18,18,18);
                        c3.setLayoutParams(tableParams);
                        c3.setText(rs.getString(3));
                        tableRow.addView(c3);

                        TextView c4 = new TextView(inventoryActivity.this);
                        c4.setPadding(18,18,18,18);
                        c4.setLayoutParams(tableParams);
                        c4.setText(rs.getString(4));
                        tableRow.addView(c4);

                        TextView c5 = new TextView(inventoryActivity.this);
                        c5.setPadding(18,18,18,18);
                        c5.setLayoutParams(tableParams);
                        c5.setText(rs.getString(5));
                        tableRow.addView(c5);


                        if((count)%2!=0){
                            c1.setBackgroundColor(Color.parseColor("#d3d3d3"));
                            //c1.setTextColor(Color.WHITE);
                            c2.setBackgroundColor(Color.parseColor("#d3d3d3"));
                            //c2.setTextColor(Color.WHITE);
                            c3.setBackgroundColor(Color.parseColor("#d3d3d3"));
                            //c3.setTextColor(Color.WHITE);
                            c4.setBackgroundColor(Color.parseColor("#d3d3d3"));
                            //c4.setTextColor(Color.WHITE);
                            c5.setBackgroundColor(Color.parseColor("#d3d3d3"));
                            //c5.setTextColor(Color.WHITE);
                        }
                        if((count)%2==0){
                            c1.setBackgroundColor(Color.WHITE);
                            c2.setBackgroundColor(Color.WHITE);
                            c3.setBackgroundColor(Color.WHITE);
                            c4.setBackgroundColor(Color.WHITE);
                            c5.setBackgroundColor(Color.WHITE);
                        }
                        count++;
                        t1.addView(tableRow);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


/**


**/
    }
}

