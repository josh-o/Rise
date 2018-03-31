package com.example.atrui.rise3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Inventory Button switches to inventory subsystem
        ImageButton inventoryButton = (ImageButton) findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, inventoryActivity.class);
                startActivity(intent);
            }
        });

        //Order Button switches to ordering subsystem
        ImageButton orderButton = (ImageButton) findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //Schedule Button switches to scheduling subsystem
        ImageButton scheduleButton = (ImageButton) findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, scheduleActivity.class);
                startActivity(intent);
            }
        });
        //Revenue Button switches to revenue subsystem
        ImageButton revenueButton = (ImageButton) findViewById(R.id.revenueButton);
        revenueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, revenueActivity.class);
                startActivity(intent);
            }
        });
    }
}
