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


/**
 * Created by atrui on 3/7/2018.
 */

public class NewOrder extends Fragment{

    ImageButton lightRoast;
    ImageButton darkRoast;
    ImageButton decaf;
    Button checkOut;
    String itemChoice;

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
                paymentPopup(v);
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
