package com.example.chalocleancreawapplayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup_supervisor extends AppCompatActivity {


    EditText supervisorNameEd,supervisorIdEd,passwordEd, confirmPassEd;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();

        //check is user is signed in or not
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Signup_supervisor.this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_supervisor);

        mAuth = FirebaseAuth.getInstance();

        supervisorNameEd = ((EditText) findViewById(R.id.editTextSupervisorName));
        supervisorIdEd = ((EditText) findViewById(R.id.editTextSupervisorId));
        passwordEd = ((EditText) findViewById(R.id.editTextSupervisorPassword));
        confirmPassEd = ((EditText) findViewById(R.id.editTextConfirmPassword));
        progressBar = findViewById(R.id.progress_sign_up);



        // Assuming you have a Button with the ID "btnSignup" in your layout
        Button btnSignup = findViewById(R.id.btnSignup);

        // Set OnClickListener for the Signup Button
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                performValidation();




            }
        });
    }

    private void performValidation(){
        if(TextUtils.isEmpty(supervisorNameEd.getText().toString())){
            Toast.makeText(Signup_supervisor.this,"Please enter name",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(supervisorIdEd.getText().toString())){
            Toast.makeText(Signup_supervisor.this,"Please enter supervisor ID",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(confirmPassEd.getText().toString())){
            Toast.makeText(Signup_supervisor.this,"Please enter password",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(confirmPassEd.getText().toString())){
            Toast.makeText(Signup_supervisor.this,"Please confirm the password",Toast.LENGTH_SHORT).show();
        }else if (!passwordEd.getText().toString().equals(confirmPassEd.getText().toString())) {
            Toast.makeText(Signup_supervisor.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }else{
            performSignup();
        }
    }



    // Method to handle signup logic
    private void performSignup() {
        progressBar.setVisibility(View.VISIBLE);
        String supervisorId,password;


        supervisorId = String.valueOf(supervisorIdEd.getText().toString());
        password = String.valueOf(passwordEd.getText().toString());


        // Registration logic (you may want to send this information to the server)

        mAuth.createUserWithEmailAndPassword(supervisorId, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Signup_supervisor.this,"Account is Successfully created, Kindly Login",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Signup_supervisor.this,Login_page.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Signup_supervisor.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        // Assuming registration is successful, open the login page

    }

    // Method to open the Login_page activity

}