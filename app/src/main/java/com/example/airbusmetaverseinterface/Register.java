package com.example.airbusmetaverseinterface;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText rfull, rdob, rpass, remail, rrepass;
    private Button rRegister;
    private TextView rlogin;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;
    private FirebaseFirestore fStore;
    private String userID;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metaverse_register);

        rfull = findViewById(R.id.full);
        rdob = findViewById(R.id.dob);
        rpass = findViewById(R.id.pass);
        rRegister = findViewById(R.id.create);

        rlogin = findViewById(R.id.login);

        progressBar = findViewById(R.id.progressBar2);
        remail = findViewById(R.id.email);
        rrepass = findViewById(R.id.repass);



        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Passenger");

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        rdob.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String mmddyyyy = "MMDDYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }

                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + mmddyyyy.substring(clean.length());
                    } else {

                        int day = Integer.parseInt(clean.substring(2, 4));
                        int mon = Integer.parseInt(clean.substring(0, 2));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) { mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);}


                        cal.set(Calendar.YEAR, year);


                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d", mon, day, year);


                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = Math.max(sel, 0);
                    current = clean;
                    rdob.setText(current);
                    rdob.setSelection(Math.min(sel, current.length()));


                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rfull.getText().toString().trim();
                String pass = rpass.getText().toString().trim();
                String dob = rdob.getText().toString().trim();
                String email = remail.getText().toString().trim();
                String repass = rrepass.getText().toString().trim();


                if (name.isEmpty()) {
                    rfull.setError("Full Name is required!");
                    rfull.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    rpass.setError("Password is required!");
                    rpass.requestFocus();
                    return;
                }
                if (dob.isEmpty()) {
                    rdob.setError("Date of Birth is required!");
                    rdob.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    remail.setError("Full Name is required!");
                    remail.requestFocus();
                    return;
                }
                if (repass.isEmpty()) {
                    rrepass.setError("Please confirm your password");
                    rrepass.requestFocus();
                    return;
                }
                if (!repass.equals(pass)) {
                    rrepass.setError("Password does not match!");
                    rrepass.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    remail.setError("Please provide valid email!");
                    remail.requestFocus();
                    return;
                }
                if (pass.length() < 8) {
                    rpass.setError("Mininimum password's length is 9");
                    rpass.requestFocus();
                    return;
                }



                progressBar.setVisibility(View.GONE);
                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (task.isSuccessful()) {
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register.this, "Verification Email has been Sent.", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Email not sent!" + e.getMessage());
                                }
                            });
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_LONG).show();
                            userID = fAuth.getCurrentUser().getUid();
                            writeNewUser(userID, name, email, dob);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Error due to " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        rlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
    public void writeNewUser(String userID, String name, String email, String dob){
        Passenger user = new Passenger(name, email, dob);
        mDatabase.child("Passenger").child(userID).setValue(user);
    }
}