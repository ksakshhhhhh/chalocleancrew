package com.example.chalocleancreawapplayout;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Role extends AppCompatActivity {

    FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();

        //check is user is signed in or not
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Role.this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        mAuth = FirebaseAuth.getInstance();

        // Assuming you have an ImageView with the id "img1" in your layout
        ImageView img1 = findViewById(R.id.img1);

        // Set OnClickListener for img1
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for img1
                openNewActivity();
            }
        });

        // Assuming you have an ImageView with the id "img2" in your layout
        ImageView img2 = findViewById(R.id.img2);

        // Set OnClickListener for img2
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for img2
                openAnotherActivity();
            }
        });
    }

    // Method to open a new activity
    private void openNewActivity() {
        Intent intent = new Intent(this, Login_page.class);
        startActivity(intent);
    }

    // Method to open another activity (for img2)
    private void openAnotherActivity() {
        Intent intent = new Intent(this, Worker_login.class);
        startActivity(intent);



    }
}
