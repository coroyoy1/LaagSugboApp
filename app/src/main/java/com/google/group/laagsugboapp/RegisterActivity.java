package com.google.group.laagsugboapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    Button addPhoto, signUp;
    ImageView imageView;

    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText fullname;
    EditText email;
    EditText address;
    EditText number;
    EditText password;
    EditText retypePassword;

    Button RegisterSignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference("users");

        fullname = (EditText)findViewById(R.id.FullnameEditText);
        email = (EditText)findViewById(R.id.EmailEditText);
        address = findViewById(R.id.RegisterAddress);
        number = findViewById(R.id.NumberEditText);
        password = findViewById(R.id.PasswordEditText);
        retypePassword = findViewById(R.id.retypePasswordEditText);
        RegisterSignUp = findViewById(R.id.RegisterSignUp);

        RegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsers();
            }
        });
    }
    private void addUsers(){
        final String fname = fullname.getText().toString().trim();
        final String add = address.getText().toString().trim();
        final String mail = email.getText().toString().trim();
        final String numbers = number.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String retype = retypePassword.getText().toString().trim();
        if(fname.isEmpty()){
            fullname.setError("Required!");
            fullname.requestFocus();
            return;
        }
        else if(mail.isEmpty()){
            email.setError("Required!");
            email.requestFocus();
            return;
        }
        else if(mail.isEmpty()){
            address.setError("Required!");
            address.requestFocus();
            return;
        }
        else if(numbers.isEmpty()){
            number.setError("Required!");
            number.requestFocus();
            return;
        }
        else if(pass.isEmpty()){
            password.setError("Required!");
            password.requestFocus();
            return;
        }
        else if(!retype.equals(pass))
        {
            password.setError("Password not match!");
            password.requestFocus();
            return;
        }
        else {
            auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String iduname = mDatabaseReference.push().getKey();
                        Users getterandsetter = new Users(fname, mail,add, numbers, pass);
                        mDatabaseReference.push().setValue(getterandsetter).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(RegisterActivity.this, "User added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(RegisterActivity.this, "Error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        fullname.getText().clear();
                        email.getText().clear();
                        address.getText().clear();
                        number.getText().clear();
                        password.getText().clear();
                        retypePassword.getText().clear();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid EMAIL and a strong password(usually not less than 6 letters)",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}
