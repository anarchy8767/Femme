package com.example.femme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.femme.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Business_Detail_Activity extends AppCompatActivity
{
    private TextView businessName, contactName, email, phone, address, description;
    private String businessID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__detail_);

        businessID = getIntent().getStringExtra("bid");


        businessName = findViewById(R.id.business_name_details);
        contactName  = findViewById(R.id.contact_name_details);
        email        = findViewById(R.id.email_details);
        phone        = findViewById(R.id.phone_details);
        address      = findViewById(R.id.address_details);
        description  = findViewById(R.id.description_details);
        getProductDetails(businessID);
    }



    private void getProductDetails(String businessID)
    {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Business");
        productsRef.child(businessID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    Products products = dataSnapshot.getValue(Products.class);

                    businessName.setText(products.getBusiness_name());
                    contactName.setText("Contact Name: " + products.getContact_name());
                    email.setText("Email: " + products.getEmail());
                    phone.setText("Phone Number: " + products.getPhone_number1() + " / " + products.getPhone_number2());
                    address.setText("Address: " + products.getAddress());
                    description.setText(products.getDiscription());







                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
