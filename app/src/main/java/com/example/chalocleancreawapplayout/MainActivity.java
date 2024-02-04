 package com.example.chalocleancreawapplayout;

 import android.content.DialogInterface;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;

 import androidx.appcompat.app.AlertDialog;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.cardview.widget.CardView;

 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.ktx.Firebase;

 public class MainActivity extends AppCompatActivity {

     FirebaseAuth mAuth;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         mAuth = FirebaseAuth.getInstance();

         // Find the CardViews by their IDs
         CardView card1 = findViewById(R.id.card1);
         CardView card2 = findViewById(R.id.card2);
         CardView card3 = findViewById(R.id.card3);
         CardView card4 = findViewById(R.id.card4);
         CardView card5 = findViewById(R.id.card5);
         CardView card6 = findViewById(R.id.card6);

         // Set OnClickListener for each CardView
         card1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openCreateEmployeeActivity();
             }
         });

         card2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openEmployeeListActivity();
             }
         });

         card3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openEmployeeAttendanceActivity();
             }
         });

         card4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openTaskListActivity();
             }
         });

         card5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openChatActivity();
             }
         });

         card6.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //openOthersActivity();
                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                 builder.setMessage("Are you sure want to logout?");
                 builder.setTitle("Alert !");
                 builder.setCancelable(false);
                 builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                     // When the user click yes button then app will close
                     mAuth.signOut();
                     Intent intent = new Intent(MainActivity.this,Role.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent);
                 });

                 builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                     // If user click no then dialog box is canceled.
                     dialog.cancel();
                 });

                 AlertDialog alertDialog = builder.create();
                 alertDialog.show();

             }
         });
     }

     // Methods to open new activities
     private void openCreateEmployeeActivity() {
         Intent intent = new Intent(this, worker_create_page.class);
         startActivity(intent);
     }

     private void openEmployeeListActivity() {
         Intent intent = new Intent(this, Two.class);
         startActivity(intent);
     }

     private void openEmployeeAttendanceActivity() {
         Intent intent = new Intent(this, Three.class);
         startActivity(intent);
     }

     private void openTaskListActivity() {
         Intent intent = new Intent(this, Four.class);
         startActivity(intent);
     }

     private void openChatActivity() {
         Intent intent = new Intent(this, Five.class);
         startActivity(intent);
     }

     private void openOthersActivity() {
         Intent intent = new Intent(this, Six.class);
         startActivity(intent);
     }
 }
