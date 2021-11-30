package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createtest extends MainActivity{
    TextView qstn , opt1 , opt2 , opt3 , opt4;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_test);
        qstn=findViewById(R.id.inpqstn);
        opt1=findViewById(R.id.inpopt1);
        opt2=findViewById(R.id.inpopt2);
        opt3=findViewById(R.id.inpopt3);
        opt4=findViewById(R.id.inpopt4);
        db=FirebaseFirestore.getInstance();
    }

    public void backbtn(View view) {
        Intent i = new Intent(view.getContext(), MainActivity.class);
        startActivity(i);
    }

    public void submitbtn(View view) {
        String qs = qstn.getText().toString();
        String op1 = opt1.getText().toString();
        String op2= opt2.getText().toString();
        String op3 = opt3.getText().toString();
        String op4= opt4.getText().toString();
        Map<String, Object> inpDoc = new HashMap<>();
        inpDoc.put("Question : ", qs);
        inpDoc.put("option 1: ", op1);
        inpDoc.put("option 2: ", op2);
        inpDoc.put("option 3: ", op3);
        inpDoc.put("option 4: ", op4);

        db.collection("Input Tests").add(inpDoc).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(createtest.this, "success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(createtest.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
