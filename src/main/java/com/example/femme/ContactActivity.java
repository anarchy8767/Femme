package com.example.femme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        TextView facebook_contact= (TextView)findViewById(R.id.facebook_contact);
        facebook_contact.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
