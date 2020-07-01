package com.example.femme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class update extends AppCompatActivity
{
    private Button update_btn;
    private EditText bname, cname, phone1, phone2, email, address, description;

    private String businessID = "";
    private DatabaseReference productsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        businessID = getIntent().getStringExtra("bid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Business").child(businessID);


        update_btn = findViewById(R.id.update_btn);
        bname = findViewById(R.id.business_name_update);
        cname = findViewById(R.id.contact_name_update);
        email = findViewById(R.id.email_update);
        phone1 = findViewById(R.id.phone1_update);
        phone2 = findViewById(R.id.phone2_update);
        address = findViewById(R.id.address_update);
        description = findViewById(R.id.description_update);


        displaySpecificBusinessInfo();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             applyChanges();
            }
        });


    }

    private void applyChanges()
    {
        String Bname = bname.getText().toString();
        String Cname = cname.getText().toString();
        String Phone1 = phone1.getText().toString();
        String Phone2 = phone2.getText().toString();
        String Email = email.getText().toString();
        String Address = address.getText().toString();
        String Description = description.getText().toString();

        if (Bname.equals(""))
        {
            Toast.makeText(this, "Provide Business Name", Toast.LENGTH_SHORT).show();
        }
        else  if (Cname.equals(""))
        {
            Toast.makeText(this, "Provide Contact Name", Toast.LENGTH_SHORT).show();
        }
        else  if (Phone1.equals(""))
        {
            Toast.makeText(this, "Provide Phone Number", Toast.LENGTH_SHORT).show();
        }
        else  if (Email.equals(""))
        {
            Toast.makeText(this, "Provide Business Email", Toast.LENGTH_SHORT).show();
        }
        else  if (Address.equals(""))
        {
            Toast.makeText(this, "Provide Address", Toast.LENGTH_SHORT).show();
        }
        else{

            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("bid", businessID);
            productMap.put("business_name", Bname);
            productMap.put("contact_name", Cname);
            productMap.put("phone_number1", Phone1);
            productMap.put("phone_number2", Phone2);
            productMap.put("email", Email);
            productMap.put("address", Address);
            productMap.put("discription", Description);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(update.this, "Update successful", Toast.LENGTH_SHORT).show();

                    }


                }
            });


        }

    }

    private void displaySpecificBusinessInfo()
    {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String Bname = dataSnapshot.child("business_name").getValue().toString();
                    String Cname = dataSnapshot.child("contact_name").getValue().toString();
                    String Email = dataSnapshot.child("email").getValue().toString();
                    String Phone1 = dataSnapshot.child("phone_number1").getValue().toString();
                    String Phone2 = dataSnapshot.child("phone_number2").getValue().toString();
                    String Address = dataSnapshot.child("address").getValue().toString();
                    String Description = dataSnapshot.child("discription").getValue().toString();

                    bname.setText(Bname);
                    cname.setText(Cname);
                    email.setText(Email);
                    phone1.setText(Phone1);
                    phone2.setText(Phone2);
                    address.setText(Address);
                    description.setText(Description);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
