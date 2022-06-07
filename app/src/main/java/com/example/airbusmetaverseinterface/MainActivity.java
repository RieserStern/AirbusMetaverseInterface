package com.example.airbusmetaverseinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    TextView name;
    Button mSchedule;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metaverse_main);
        mSchedule =  findViewById(R.id.schedule);
        user      =  FirebaseAuth.getInstance().getCurrentUser();
        name      =  findViewById(R.id.pass_name);
        database  =  FirebaseDatabase.getInstance();


        if(user != null){
            userID    = user.getUid();
            databaseReference = database.getReference().child("Passenger/Passenger/"+userID+"/name");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name_text = (String)snapshot.getValue();
                    name.setText(name_text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        mSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FlightBooking.class));

            }
        });

    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}