package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;

public class linkedlist extends MenuPage {
    TextView qstn;
    ModelClass mc ;
    Button opt1, opt2, opt3, opt4 , bbn ,nbn;
    int index=0,correctans=0,wrongans=0;
    public static ArrayList<ModelClass> list;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkedlist);

        list = new ArrayList<>();
        list.add(new ModelClass("What member function places a new node at the end of the linked list?" , "add node", "append node" , "Display node" , "Struct node" , "add node"));
        list.add(new ModelClass("Consider a linked list of n elements. What is the time taken to insert an element after an element pointed by some pointer?" , "O(1)", "O(log2 n)" , "O(n)" , "O(nlog2 n)" , "O(n)"));

        qstn = findViewById(R.id.qstntxt);
        opt1 = findViewById(R.id.optnbtn1);
        opt2 = findViewById(R.id.optnbtn2);
        opt3 = findViewById(R.id.optnbtn3);
        opt4 = findViewById(R.id.optnbtn4);
        bbn = findViewById(R.id.backbtn);
        nbn = findViewById(R.id.Nxtbtn);
        nbn.setClickable(false);

        databaseReference = FirebaseDatabase.getInstance().getReference("LinkedLIST");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelClass modelClass = dataSnapshot.getValue(ModelClass.class);
                    list.add(modelClass);
                }
                Log.d("check", "setdata:"+list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Collections.shuffle(list);
        Log.d("checker" , ""+list);
        mc=list.get(index);


        setdata();


        bbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext() , MenuPage.class);
                startActivity(i);
            }
        });



    }

    void setdata() {
        qstn.setText(mc.getQuestion());
        opt1.setText(mc.getOpt1());
        opt2.setText(mc.getOpt2());
        opt3.setText(mc.getOpt3());
        opt4.setText(mc.getOpt4());
        Log.d("check", "setdata1:"+mc.getQuestion());

    }

    public void correct(Button btn){
        btn.setBackgroundColor(getResources().getColor(R.color.green));
        nbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctans+=1;
                index+=1;
                mc=list.get(index);
                Log.d("check", "setdata2:"+mc.getQuestion());
                setdata();
                resetclr();
                enablebtn();
            }
        });

    }

    public void wrong(Button opt1){
        opt1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        nbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongans+=1;
                if(index < 9){
                    index+=1;
                    mc=list.get(index);
                    setdata();
                    resetclr();
                    enablebtn();
                }
                else{
                    GameOver();
                }
            }
        });
    }

    private void GameOver() {
        Intent i = new Intent(linkedlist.this , gameover.class);
        i.putExtra("correct",correctans);
        i.putExtra("wrong" , wrongans);
        startActivity(i);
    }

    public void enablebtn(){
        opt1.setClickable(true);
        opt2.setClickable(true);
        opt3.setClickable(true);
        opt4.setClickable(true);
    }

    public void disablebtn(){
        opt1.setClickable(false);
        opt2.setClickable(false);
        opt3.setClickable(false);
        opt4.setClickable(false);
    }

    public void resetclr(){
        opt1.setBackgroundColor(getResources().getColor(R.color.white));
        opt2.setBackgroundColor(getResources().getColor(R.color.white));
        opt3.setBackgroundColor(getResources().getColor(R.color.white));
        opt4.setBackgroundColor(getResources().getColor(R.color.white));
    }


    public void option1(View view) {
        disablebtn();
        nbn.setClickable(true);
        if(mc.getOpt1().equals(mc.getAns()))
        {
            opt1.setBackgroundColor(getResources().getColor(R.color.green));


            if(index < 9){
                correct(opt1);

            }
            else{
                correctans+=1;
                GameOver();
            }}
        else{
            wrong(opt1);
            if(mc.getOpt2().equals(mc.getAns())){
                opt2.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt3().equals(mc.getAns())){
                opt3.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt4().equals(mc.getAns())){
                opt4.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }

    public void option2(View view) {
        disablebtn();
        nbn.setClickable(true);
        if(mc.getOpt2().equals(mc.getAns()))
        {
            opt2.setBackgroundColor(getResources().getColor(R.color.green));

            if(index < 9){
                correct(opt2);
            }
            else{
                correctans+=1;
                GameOver();
            }}
        else{
            wrong(opt2);
            if(mc.getOpt1().equals(mc.getAns())){
                opt1.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt3().equals(mc.getAns())){
                opt3.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt4().equals(mc.getAns())){
                opt4.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }

    public void option3(View view) {
        disablebtn();
        nbn.setClickable(true);
        if(mc.getOpt3().equals(mc.getAns()))
        {
            opt3.setBackgroundColor(getResources().getColor(R.color.green));


            if(index < 9){
                correct(opt3);
            }
            else{
                correctans+=1;
                GameOver();
            }}
        else{
            wrong(opt3);
            if(mc.getOpt1().equals(mc.getAns())){
                opt1.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt2().equals(mc.getAns())){
                opt2.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt4().equals(mc.getAns())){
                opt4.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }

    public void option4(View view) {
        disablebtn();
        nbn.setClickable(true);
        if(mc.getOpt4().equals(mc.getAns()))
        {
            opt4.setBackgroundColor(getResources().getColor(R.color.green));


            if(index < 9){
                correct(opt4);
            }
            else{
                correctans+=1;
                GameOver();
            }}
        else{
            wrong(opt4);
            if(mc.getOpt1().equals(mc.getAns())){
                opt1.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt3().equals(mc.getAns())){
                opt3.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(mc.getOpt2().equals(mc.getAns())){
                opt2.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }
}
