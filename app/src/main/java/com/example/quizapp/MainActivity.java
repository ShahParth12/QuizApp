package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//By Parth , Shivam and Dev
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tchrmd = findViewById(R.id.tchrmode);
        tchrmd.setVisibility(View.INVISIBLE);

        Button b = findViewById(R.id.strtbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MenuPage.class);
                startActivity(i);

            }
        });
    }

    /*public void CreateTest(View view) {

    }*/
}