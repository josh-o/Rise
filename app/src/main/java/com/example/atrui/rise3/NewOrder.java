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
                chooseItemSizePopup(v);
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


    public void chooseItemSizePopup(View v){

        final Dialog chooseItemSize = new Dialog(getActivity());
        chooseItemSize.setContentView(R.layout.popup_layout);
        ImageButton closeDialog = (ImageButton)chooseItemSize.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chooseItemSize.dismiss();
            }
        });
        //Create new item logic
        Button createItem = (Button)chooseItemSize.findViewById(R.id.createItem);
        createItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {



                chooseItemSize.dismiss();
                ////displayTable();
            }
        });
        chooseItemSize.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chooseItemSize.show();

    }

}
