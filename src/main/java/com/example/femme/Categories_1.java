package com.example.femme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Categories_1 extends AppCompatActivity {

    private ImageView catering, clothing, sewing, tutor;
    private ImageView accessory, food, makeup, jewelry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_1);


        catering = (ImageView) findViewById(R.id.catering);
        clothing = (ImageView) findViewById(R.id.clothing);
        sewing   = (ImageView) findViewById(R.id.sewing);
        tutor    = (ImageView) findViewById(R.id.tutor);

        accessory = (ImageView) findViewById(R.id.accessory);
        food     = (ImageView) findViewById(R.id.food);
        makeup   = (ImageView) findViewById(R.id.makeup);
        jewelry  = (ImageView) findViewById(R.id.jewelry);

        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","catering");
                startActivity(intent);
            }
        });

        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","clothing");
                startActivity(intent);

            }
        });

        sewing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","sewing");
                startActivity(intent);

            }
        });

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","tutor");
                startActivity(intent);

            }
        });

        accessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","accessory");
                startActivity(intent);

            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","food");
                startActivity(intent);

            }
        });

        makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","makeup");
                startActivity(intent);

            }
        });

        jewelry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Categories_1.this, Reg_tutor.class);
                intent.putExtra("category","jewellery");
                startActivity(intent);

            }
        });

    }
}
