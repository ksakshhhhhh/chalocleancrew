package com.example.chalocleancreawapplayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_page extends AppCompatActivity {

    EditText supervisorIdEd,passwordEd;
    Button loginBtn;

    FirebaseAuth mAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();

        supervisorIdEd = findViewById(R.id.editTextUserId);
        passwordEd = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progress_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performValidation();
            }
        });


        // Assuming you have a TextView with the id "textViewSignup" in your layout
        TextView textViewSignup = findViewById(R.id.textViewSignup);

        // Set OnClickListener for textViewSignup
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for textViewSignup
                openSignupActivity();
            }
        });

        // Other code for your Login_page activity...
    }

    private void performValidation(){
        if(TextUtils.isEmpty(supervisorIdEd.getText().toString())){
            Toast.makeText(Login_page.this,"Please enter supervisor id",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passwordEd.getText().toString())){
            Toast.makeText(Login_page.this,"Please enter the password",Toast.LENGTH_SHORT).show();
        }else {
            performLogin();
        }
    }

    private void performLogin(){
        progressBar.setVisibility(View.VISIBLE);
        String email = supervisorIdEd.getText().toString();
        String password = passwordEd.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login_page.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(Login_page.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login_page.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    // Method to open the Signup_supervisor activity
    private void openSignupActivity() {
        Intent intent = new Intent(this, Signup_supervisor.class);
        startActivity(intent);
    }
}
