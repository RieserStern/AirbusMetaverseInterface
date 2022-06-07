package com.example.airbusmetaverseinterface;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import androidmads.library.qrgenearator.QRGEncoder;


public class QRCode extends AppCompatActivity {


    String userID, name, seat, from, to;
    DatabaseReference mDatabase;
    DatabaseReference cusername;
    DatabaseReference cseat;
    DatabaseReference cfrom;
    DatabaseReference cto;



    Bitmap bitmap;

    ImageView QRcode;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Passenger/Passenger/"+userID);
        cusername = mDatabase.child("name");
        cseat     = mDatabase.child("seat");
        cfrom     = mDatabase.child("from");
        cto       = mDatabase.child("to");
        QRcode    = findViewById(R.id.QRcodegen);

//        QRGEncoder qrgEncoder = new QRGEncoder();

    }
}