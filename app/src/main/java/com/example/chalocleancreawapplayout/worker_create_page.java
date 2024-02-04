package com.example.chalocleancreawapplayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class worker_create_page extends AppCompatActivity {

    EditText nameEd, supervisorIdEd, phoneNoEd, passEd, confirmPassEd;
    Button createBtn;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chalo-clean-creaw-app-layout-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_create_page);

        nameEd = findViewById(R.id.editTextSupervisorName);
        supervisorIdEd = findViewById(R.id.editTextWorker_Id);
        phoneNoEd = findViewById(R.id.editTextphone_no);
        passEd = findViewById(R.id.editTextPassword);
        confirmPassEd = findViewById(R.id.editTextConfirmPassword);
        createBtn = findViewById(R.id.btnSignup);


        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final  String name = nameEd.getText().toString();
                final String supervisorId = supervisorIdEd.getText().toString();
                final String phoneNo = phoneNoEd.getText().toString();
                final String pass = passEd.getText().toString();
                final String confirmPass = passEd.getText().toString();

                if(name.isEmpty() || supervisorId.isEmpty() || phoneNo.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()){
                    Toast.makeText(worker_create_page.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(confirmPass)){
                    Toast.makeText(worker_create_page.this, "Password in not matched ", Toast.LENGTH_SHORT).show();
                }else{

                    databaseReference.child("Workers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Toast.makeText(worker_create_page.this,"hii",Toast.LENGTH_SHORT).show();
                            if(snapshot.hasChild(supervisorId)){
                                Toast.makeText(worker_create_page.this,"This worker is already registered!",Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("Workers").child(supervisorId).child("FullName").setValue(name);
                                databaseReference.child("Workers").child(supervisorId).child("MobileNo").setValue(phoneNo);
                                databaseReference.child("Workers").child(supervisorId).child("Password").setValue(pass);


                                Toast.makeText(worker_create_page.this,"Registration is successful",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(worker_create_page.this,error.getMessage(),Toast.LENGTH_SHORT);
                        }
                    });




                }
                    




            }
        });

    }
}