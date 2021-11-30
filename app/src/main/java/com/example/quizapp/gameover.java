package com.example.quizapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class gameover extends linkedlist {
    TextView finscore;
    ImageView img;
    int correct , wrong ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);

        finscore=findViewById(R.id.finscore);
        img=findViewById(R.id.imageView);

        correct = getIntent().getIntExtra("correct" , 0);
        wrong = getIntent().getIntExtra("wrong" , 0);

        finscore.setText((correct)+"/"+(correct+wrong));


        if(correct>=7){
            img.setImageDrawable(getResources().getDrawable(R.drawable.congrats));
        }
        else{
            img.setImageDrawable(getResources().getDrawable(R.drawable.hardluck));
        }


    }

    public void backbutton(View view) {
        Intent i = new Intent(gameover.this , MenuPage.class);
        startActivity(i);
    }
}
