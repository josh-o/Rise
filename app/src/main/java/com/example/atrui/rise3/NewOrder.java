package com.example.atrui.rise3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;


/**
 * Created by atrui on 3/7/2018.
 */

public class NewOrder extends Fragment{

    ImageButton lightRoast;
    String lightRoast1 = "Light Roast";
    Button checkOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.neworder, container, false);
        lightRoast = (ImageButton) view.findViewById(R.id.LightRoastButton2);

    //    String modifyTest = getText().toString();
        lightRoast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView orderLine1 = (TextView) view.findViewById(R.id.orderLine1);
                orderLine1.setText(lightRoast1);
            }
        });

        checkOut = (Button) view.findViewById(R.id.checkoutButton);

        checkOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


            }
        });
        return view;


    }

}
