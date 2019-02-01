package com.google.group.laagsugboapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextView register;
    EditText emailAddress, passwordType;
    Button loginNow;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase databaseConnection;
    private DatabaseReference refConnection;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgress(0);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        emailAddress = findViewById(R.id.usernameEditText);
        passwordType = findViewById(R.id.passwordEditText);
        register = findViewById(R.id.registerAccount);
        loginNow = findViewById(R.id.logInBtn);
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInNow();
            }
        });
    }
    private void signInNow()
    {
        String email = emailAddress.getText().toString();
        String password = passwordType.getText().toString();

        if(email.isEmpty() || password.isEmpty())
        {
            showMessages("Please check your email address or password.");
        }
        else
        {
            if(!(LoginActivity.this).isFinishing())
            {
                progressDialog.show();
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    finish();
                    if(!task.isSuccessful())
                    {
                        showMessages("Please check your internet connection or credentials.");
                    }
                    else {
                        FirebaseUser userHERE = FirebaseAuth.getInstance().getCurrentUser();
                        String RegisteredUserID = userHERE.getUid();
                        refConnection = FirebaseDatabase.getInstance().getReference().child("Users").child(RegisteredUserID);
                        startActivity(new Intent(LoginActivity.this, LaagSugboMainActivity.class));
                    }
                }
            });
        }
    }
    private void showMessages(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
