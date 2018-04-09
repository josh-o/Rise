package com.example.atrui.rise3;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * Created by atrui on 3/7/2018.
 */

public class NewOrder extends Fragment{

    ImageButton lightRoast;
    ImageButton darkRoast;
    ImageButton decaf;
    ImageButton icedCoffee;
    ImageButton caffeMisto;
    ImageButton caffeAmericano;
    ImageButton caffeLatte;
    ImageButton caffeMocha;
    ImageButton cappuccino;
    ImageButton caramelMacchiato;
    ImageButton espresso;
    ImageButton flatWhite;
    ImageButton latteMacchiato;
    Button checkOut;
    Button total;
    String itemChoice;
    String itemsOrdered = "";
    int Sum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.neworder, container, false);

        //Light Roast
        lightRoast = (ImageButton) view.findViewById(R.id.LightRoastButton2);
        lightRoast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Light Roast";
                chooseItemSizePopup();
            }
        });
        //Dark Roast
        darkRoast = (ImageButton) view.findViewById(R.id.DarkRoastButton);
        darkRoast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Dark Roast";
                chooseItemSizePopup();
            }
        });
        //Decaf
        decaf = (ImageButton) view.findViewById(R.id.DecafButton);
        decaf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Decaf";
                chooseItemSizePopup();
            }
        });
        //Iced Coffee
        icedCoffee = (ImageButton) view.findViewById(R.id.IcedCoffeeButton);
        icedCoffee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Iced Coffee";
                chooseItemSizePopup();
            }
        });
        //Caffe Misto
        caffeMisto = (ImageButton) view.findViewById(R.id.CaffeMistoButton);
        caffeMisto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Caffe Misto";
                chooseItemSizePopup();
            }
        });
        //Caffe Americano
        caffeAmericano = (ImageButton) view.findViewById(R.id.CAmericanoButton);
        caffeAmericano.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Americano";
                chooseItemSizePopup();
            }
        });
        //Caffe Latte
        caffeLatte = (ImageButton) view.findViewById(R.id.CLatteButton);
        caffeLatte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Caffe Latte";
                chooseItemSizePopup();
            }
        });
        //Caffe Mocha
        caffeMocha = (ImageButton) view.findViewById(R.id.CaffeMochaButton);
        caffeMocha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Caffe Mocha";
                chooseItemSizePopup();
            }
        });
        //Cappuccino
        cappuccino = (ImageButton) view.findViewById(R.id.CappuccinoButton);
        cappuccino.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Cappuccino";
                chooseItemSizePopup();
            }
        });
        //Caramel Macchiato
        caramelMacchiato = (ImageButton) view.findViewById(R.id.CMaccButton);
        caramelMacchiato.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "C Macchiato";
                chooseItemSizePopup();
            }
        });
        //Espresso
        espresso = (ImageButton) view.findViewById(R.id.EspressoButton);
        espresso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Espresso";
                chooseItemSizePopup();
            }
        });
        //Flat White
        flatWhite = (ImageButton) view.findViewById(R.id.FlatWhiteButton);
        flatWhite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Flat White";
                chooseItemSizePopup();
            }
        });
        //Latte Macchiato
        latteMacchiato = (ImageButton) view.findViewById(R.id.LMaccButton);
        latteMacchiato.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemChoice = "Macchiato";
                chooseItemSizePopup();
            }
        });

        total = (Button) view.findViewById(R.id.TotalButton);

        total.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView sumLine = view.findViewById(R.id.TotalNum);
                sumLine.setText("$" + String.valueOf(Sum) + ".00");

            }
        });


        checkOut = (Button) view.findViewById(R.id.checkoutButton);

        checkOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkOutPopup(v);
            }
        });

        return view;
    }


    public void chooseItemSizePopup(){
        final Dialog chooseItemSize = new Dialog(getActivity());
        final LinearLayout orderLayout = (LinearLayout) getActivity().findViewById(R.id.orderSummaryLayout);
        final LinearLayout orderPriceLayout = (LinearLayout) getActivity().findViewById(R.id.orderPrice);
        chooseItemSize.setContentView(R.layout.popup_layout);

        //Choose Size

        //Small
        Button small = (Button)chooseItemSize.findViewById(R.id.smallSize);
        small.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chooseItemSize.dismiss();
                TextView line1 = new TextView(getContext());
                line1.setText(itemChoice + " - Small");
                chooseItemSize.setContentView(R.layout.neworder);
                orderLayout.addView(line1);
                TextView price1 = new TextView(getContext());
                price1.setText("$3.00");
                orderPriceLayout.addView(price1);
                Sum += 3;
                TextView sumLine = getActivity().findViewById(R.id.TotalNum);
                sumLine.setText("$" + String.valueOf(Sum) + ".00");
                itemsOrdered = itemsOrdered + " " + itemChoice + " - Small / ";

            }
        });
        //Medium
        Button medium = (Button)chooseItemSize.findViewById(R.id.mediumSize);
        medium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chooseItemSize.dismiss();
                TextView line1 = new TextView(getContext());
                line1.setText(itemChoice + " - Medium");
                chooseItemSize.setContentView(R.layout.neworder);
                orderLayout.addView(line1);
                TextView price1 = new TextView(getContext());
                price1.setText("$4.00");
                orderPriceLayout.addView(price1);
                Sum += 4;
                TextView sumLine = getActivity().findViewById(R.id.TotalNum);
                sumLine.setText("$" + String.valueOf(Sum) + ".00");
                itemsOrdered = itemsOrdered + " " + itemChoice + " - Medium / ";
            }
        });
        //Large
        Button large = (Button)chooseItemSize.findViewById(R.id.largeSize);
        large.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                chooseItemSize.dismiss();
                TextView line1 = new TextView(getContext());
                line1.setText(itemChoice + " - Large");
                chooseItemSize.setContentView(R.layout.neworder);
                orderLayout.addView(line1);
                TextView price1 = new TextView(getContext());
                price1.setText("$5.00");
                orderPriceLayout.addView(price1);
                Sum += 5;
                TextView sumLine = getActivity().findViewById(R.id.TotalNum);
                sumLine.setText("$" + String.valueOf(Sum) + ".00");
                itemsOrdered = itemsOrdered + " " + itemChoice + " - Large / ";
            }
        });

        chooseItemSize.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chooseItemSize.show();

    }

    public void checkOutPopup(View v){
        final Dialog enterCustomerName = new Dialog(getActivity());
        enterCustomerName.setContentView(R.layout.checkout_layout);

        Button payment = (Button)enterCustomerName.findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Get Current Date
                Date currentDate = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String date = dateFormat.format(currentDate);
                //Generate order number
                Random r = new Random();
                int orderNumberInt = r.nextInt(1000000 - 100) + 100;
                String orderNumber = Integer.toString(orderNumberInt);
                //Get customer name
                EditText enterNameField = (EditText)enterCustomerName.findViewById(R.id.enterName);
                String enterName = enterNameField.getText().toString();
                //Get order price
                Double orderPrice = (double)Sum;
                //Get order items = itemsOrdered string

                try {
                    //SQL connection
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    String url = "jdbc:jtds:sqlserver://riseinc2.database.windows.net:1433;databaseName=Order;user=jtoverby@riseinc2;password=Awesome33!;";
                    Connection connect = DriverManager.getConnection(url);
                    PreparedStatement pst = connect.prepareStatement("INSERT INTO Orders" +"(order_number, customer_name, items_Ordered, order_Price, order_Date) VALUES" +
                            "(?,?,?,?,?)");

                    if(!enterName.isEmpty() && orderPrice!=null){

                            pst.setString(1, orderNumber);

                        if(enterName.length()<=30) {
                            pst.setString(2, enterName);
                        }
                        if(itemsOrdered.length()<=200) {
                            pst.setString(3, itemsOrdered);
                        }

                        pst.setDouble(4,orderPrice);
                        pst.setString(5, date);

                    }
                    pst.executeUpdate();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //Clear Total
                TextView sumLine = getActivity().findViewById(R.id.TotalNum);
                Sum = 0;
                sumLine.setText("");
                //Remove all ordered items and prices
                final LinearLayout orderLayout = (LinearLayout) getActivity().findViewById(R.id.orderSummaryLayout);
                final LinearLayout orderPriceLayout = (LinearLayout) getActivity().findViewById(R.id.orderPrice);
                orderLayout.removeAllViews();
                orderPriceLayout.removeAllViews();
                //Clear itemsordered string
                itemsOrdered = "";

                paymentPopup(v);
                enterCustomerName.dismiss();
            }
        });


        enterCustomerName.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        enterCustomerName.show();
    }

    public void paymentPopup(View v){
        final Dialog enterPayment = new Dialog(getActivity());
        enterPayment.setContentView(R.layout.payment_layout);



        enterPayment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        enterPayment.show();
    }

}
