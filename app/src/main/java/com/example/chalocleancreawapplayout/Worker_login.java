package com.example.chalocleancreawapplayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Worker_login extends AppCompatActivity {

  Button btn;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chalo-clean-creaw-app-layout-default-rtdb.asia-southeast1.firebasedatabase.app/");

  EditText userIdEd,passwordEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);

        btn = findViewById(R.id.btnLogin);

        userIdEd = findViewById(R.id.editTextUserId);
        passwordEd = findViewById(R.id.editTextPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performValidation();
            }
        });

        };

        private void performValidation(){
           if (TextUtils.isEmpty(userIdEd.getText().toString())) {
               Toast.makeText(Worker_login.this, "Please enter worker id", Toast.LENGTH_SHORT).show();
           }else if (TextUtils.isEmpty(passwordEd.getText().toString())) {
               Toast.makeText(Worker_login.this, "Please enter the password", Toast.LENGTH_SHORT).show();
           }else{
               login();
           }




    }

    public  void login(){
            String userId = userIdEd.getText().toString();
            String password = passwordEd.getText().toString();


            databaseReference.child("Workers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(userId)){
                        final String getpassword = snapshot.child(userId).child("Password").getValue(String.class);
                        if(getpassword.equals(password)){
                            Toast.makeText(Worker_login.this,"Logged in successfully!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Worker_login.this,Worker_Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Worker_login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Worker_login.this, "Worker not exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }
}