package com.example.airbusmetaverseinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.braintreepayments.cardform.view.CardForm;


public class Payment extends AppCompatActivity {
    CardForm cardForm;
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


    }
}