package com.example.femme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        finish();
    }

    public void contact_us(View view) {

        startActivity(new Intent(getApplicationContext(),ContactActivity.class));

    }

    public void business_register(View view) {

        startActivity(new Intent(getApplicationContext(),Categories_1.class));

    }


    public void search(View view) {
        startActivity(new Intent(getApplicationContext(),Search_Business_Activity.class));

    }

    public void my_business(View view) {
        startActivity(new Intent(getApplicationContext(),Search_My_Business.class));
    }
}
