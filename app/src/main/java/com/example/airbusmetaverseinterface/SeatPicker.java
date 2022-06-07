package com.example.airbusmetaverseinterface;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;


public class SeatPicker extends AppCompatActivity {
    Button select, a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, e1, e2, e3, e4, e5, f1, f2, f3, f4, f5;
    TextView preview;
    String seatselect;
    FirebaseDatabase database;
    String userID;
    DatabaseReference mDatabase;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_picker);

        a1 = findViewById(R.id.A1);
        a2 = findViewById(R.id.A2);
        a3 = findViewById(R.id.A3);
        a4 = findViewById(R.id.A4);
        a5 = findViewById(R.id.A5);
        b1 = findViewById(R.id.B1);
        b2 = findViewById(R.id.B2);
        b3 = findViewById(R.id.B3);
        b4 = findViewById(R.id.B4);
        b5 = findViewById(R.id.B5);
        c1 = findViewById(R.id.C1);
        c2 = findViewById(R.id.C2);
        c3 = findViewById(R.id.C3);
        c4 = findViewById(R.id.C4);
        c5 = findViewById(R.id.C5);
        d1 = findViewById(R.id.D1);
        d2 = findViewById(R.id.D2);
        d3 = findViewById(R.id.D3);
        d4 = findViewById(R.id.D4);
        d5 = findViewById(R.id.D5);
        e1 = findViewById(R.id.E1);
        e2 = findViewById(R.id.E2);
        e3 = findViewById(R.id.E3);
        e4 = findViewById(R.id.E4);
        e5 = findViewById(R.id.E5);
        f1 = findViewById(R.id.F1);
        f2 = findViewById(R.id.F2);
        f3 = findViewById(R.id.F3);
        f4 = findViewById(R.id.F4);
        f5 = findViewById(R.id.F5);
        select = findViewById(R.id.confirmSeat);
        preview = findViewById(R.id.seatview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            userID = user.getUid();
        }
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference().child("Passenger/Passenger/"+userID);

        seatselect = " ";
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "A1";
                preview.setText(seatselect);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "A2";
                preview.setText(seatselect);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "A3";
                preview.setText(seatselect);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "A4";
                preview.setText(seatselect);
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "A5";
                preview.setText(seatselect);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "B1";
                preview.setText(seatselect);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "B2";
                preview.setText(seatselect);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "B3";
                preview.setText(seatselect);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "B4";
                preview.setText(seatselect);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "B5";
                preview.setText(seatselect);
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "C1";
                preview.setText(seatselect);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "C2";
                preview.setText(seatselect);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "C3";
                preview.setText(seatselect);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "C4";
                preview.setText(seatselect);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "C5";
                preview.setText(seatselect);
            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "D1";
                preview.setText(seatselect);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "D2";
                preview.setText(seatselect);
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "D3";
                preview.setText(seatselect);
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "D4";
                preview.setText(seatselect);
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "D5";
                preview.setText(seatselect);
            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "E1";
                preview.setText(seatselect);
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "E2";
                preview.setText(seatselect);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "E3";
                preview.setText(seatselect);
            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "E4";
                preview.setText(seatselect);
            }
        });
        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "E5";
                preview.setText(seatselect);
            }
        });
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "F1";
                preview.setText(seatselect);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "F2";
                preview.setText(seatselect);
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "F3";
                preview.setText(seatselect);
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "F4";
                preview.setText(seatselect);
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatselect = "F5";
                preview.setText(seatselect);
            }
        });



        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> userUpdate = new HashMap<>();
                userUpdate.put("seat", seatselect);
                mDatabase.updateChildren(userUpdate);
                startActivity(new Intent(getApplicationContext(), FlightBooking.class));
            }
        });
    }
}