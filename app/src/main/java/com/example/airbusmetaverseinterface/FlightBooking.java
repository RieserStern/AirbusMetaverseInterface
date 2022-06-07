package com.example.airbusmetaverseinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class FlightBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button datepicker, seatpicker, payment;
    TextView flightdate, seatpreview;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID, from , to, plane;
    Spinner countryFrom, countryTo, flightSpinner;
    String date;
    ArrayAdapter<String> countries;
    ArrayAdapter<String> flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking);
        datepicker = findViewById(R.id.dateschedule);
        flightdate = findViewById(R.id.Dateshow);
        seatpicker = findViewById(R.id.flightseat);
        seatpreview = findViewById(R.id.seatpreview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        payment = findViewById(R.id.pay);


        countryFrom = findViewById(R.id.countryFrom);
        countryTo = findViewById(R.id.countryTo);
        flightSpinner = findViewById(R.id.flightSpinner);

        userID = user.getUid();

        countries = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.countries));
        flight = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.flight));
        countryFrom.setAdapter(countries);
        countryTo.setAdapter(countries);
        flightSpinner.setAdapter(flight);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatepicker();
            }
        });


        databaseReference = database.getReference().child("Passenger/Passenger/" + userID + "/seat");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seat_text = (String) snapshot.getValue();
                seatpreview.setText(seat_text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        seatpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SeatPicker.class));
            }
        });


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> userUpdate = new HashMap<>();
                userUpdate.put("From", countryFrom.getSelectedItem().toString());
                userUpdate.put("To", countryTo.getSelectedItem().toString());
                userUpdate.put("Plane", flightSpinner.getSelectedItem().toString());
                userUpdate.put("flightdate", date);

                database.getReference().child("Passenger/Passenger/" + userID).updateChildren(userUpdate);
                startActivity(new Intent(getApplicationContext(), Payment.class));
            }

        });
    }
    private void showDatepicker(){
        DatePickerDialog dialog = new DatePickerDialog(
                this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month+"/"+dayOfMonth+"/"+year;
        flightdate.setText(date);
    }
}



