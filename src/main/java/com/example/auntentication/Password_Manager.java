package com.example.auntentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.auntentication.prevelant.prevelant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.paperdb.Paper;

public class Password_Manager extends AppCompatActivity {

    private Button Save_Button, View_Mutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);

        Save_Button = (Button) findViewById(R.id.Save_password);
        View_Mutton = (Button) findViewById(R.id.View_Password);

        Save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Password_Manager.this,Save_password.class );
                startActivity(intent);
            }
        });

        View_Mutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Password_Manager.this,REAL_VIEW_PASSWORD.class );
                startActivity(intent);
            }
        });

    }
}