package com.example.femme;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFramelayout;
    private EditText email;
    private EditText fullName;
    private EditText password;

    private ImageButton closeBtn;
    private Button signUpBtn;



    private FirebaseAuth firebaseAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account);
        parentFramelayout = getActivity().findViewById(R.id.register_framelayout);
        email = view.findViewById(R.id.sign_up_email);
        fullName = view.findViewById(R.id.sign_up_fullname);
        password = view.findViewById(R.id.sign_up_password);
        signUpBtn = view.findViewById(R.id.sign_up_btn);

        firebaseAuth = FirebaseAuth.getInstance();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();

            }
        });


    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    @SuppressLint({"ResourceAsColor", "NewApi"})
    private  void checkInputs(){
        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(fullName.getText())){
                if (!TextUtils.isEmpty(password.getText()) && password.length()>=8){
                    signUpBtn.setEnabled(true);
                    signUpBtn.setTextColor(Color.rgb(255,255,255));

                }else {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.rgb(105,105,105));

                }
            }else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(105,105,105));

            }
        }else {
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.rgb(105,105,105));

        }
    }
    private  void checkEmailAndPassword(){
        if (email.getText().toString().matches(emailPattern)){



            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.rgb(105,105,105));

            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                            startActivity(mainIntent);
                            getActivity().finish();


                            }else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }else {
            email.setError("Invalid Email!");

        }

    }
}
