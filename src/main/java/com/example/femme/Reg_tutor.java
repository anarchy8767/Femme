package com.example.femme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Reg_tutor extends AppCompatActivity {

    private String CategoryName, b_name, c_name, p_hone1, p_hone2 , e_mail, b_email, a_ddress, saveCurrentDate, saveCurrentTime, b_discription;
    private Button reg_btn;

    private EditText bname, cname, phone1, phone2, email, bemail, address, bdiscription;

    private String businessRandomKey;
    private StorageReference ProductImageRef;
    private DatabaseReference BusinessRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tutor);

        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        BusinessRef = FirebaseDatabase.getInstance().getReference().child("Business");


        reg_btn = (Button) findViewById(R.id.register_business_btn_8);

        bname = (EditText) findViewById(R.id.business_name_8);
        cname = (EditText) findViewById(R.id.contact_name_8);
        phone1 = (EditText) findViewById(R.id.phone_number_8_1);
        phone2 = (EditText) findViewById(R.id.phone_number_8_2);
        email = (EditText) findViewById(R.id.business_email_8);
        bemail = (EditText) findViewById(R.id.business_email_81);
        address = (EditText) findViewById(R.id.address_8);
        bdiscription = (EditText) findViewById(R.id.discription_8);




        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });



    }






    private void ValidateProductData(){

        b_name = bname.getText().toString();
        c_name = cname.getText().toString();
        p_hone1 = phone1.getText().toString();
        p_hone2 = phone2.getText().toString();
        e_mail = email.getText().toString().toLowerCase();
        b_email = bemail.getText().toString().toLowerCase();
        a_ddress = address.getText().toString();
        b_discription = bdiscription.getText().toString();

       if (TextUtils.isEmpty(b_name))
        {
            Toast.makeText(this,"Provide business name.",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(p_hone1))
        {
            Toast.makeText(this,"Provide Phone number.",Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(b_email))
       {
           Toast.makeText(this,"Provide Business email.",Toast.LENGTH_SHORT).show();
       }

       else if (TextUtils.isEmpty(b_discription))
       {
           Toast.makeText(this,"Provide Business Description.",Toast.LENGTH_SHORT).show();
       }
        else {
            StoreBusinessInformation();
                    

        }


    }

    private void StoreBusinessInformation() {

        Calendar calendar = Calendar.getInstance();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:SS a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        businessRandomKey = saveCurrentDate + saveCurrentTime;



        SaveBusinessInfoToDatabase();
    }

    private void SaveBusinessInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("bid", businessRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("business_name", b_name);
        productMap.put("category", CategoryName);
        productMap.put("contact_name", c_name);
        productMap.put("phone_number1", p_hone1);
        productMap.put("phone_number2", p_hone2);
        productMap.put("email", e_mail);
        productMap.put("bemail", b_email);
        productMap.put("address", a_ddress);
        productMap.put("discription", b_discription);

        BusinessRef.child(businessRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful())
                   {
                       reg_btn.setEnabled(false);
                       reg_btn.setTextColor(Color.rgb(105,105,105));
                       Intent intent = new Intent(Reg_tutor.this, MainActivity.class);
                       startActivity(intent);
                       Toast.makeText(Reg_tutor.this, "Business is Added Successfully", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       String message = task.getException().toString();
                       Toast.makeText(Reg_tutor.this, "Error " + message, Toast.LENGTH_SHORT).show();
                   }
                    }
                });
    }


}
