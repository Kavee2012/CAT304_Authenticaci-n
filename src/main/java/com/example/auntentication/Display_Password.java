package com.example.auntentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.auntentication.prevelant.prevelant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;

import io.paperdb.Paper;

public class Display_Password extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
   /* ArrayList<Credentials>credentialsArrayList;
    MyAdapter myAdapter;
    DatabaseReference db;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_password);

        recyclerView = findViewById(R.id.recycler_view);
        //  recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //  db = FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/").getReference().child("Users").child(Paper.book().read(prevelant.UserEmail));

        // credentialsArrayList = new ArrayList<Credentials>();
        //  myAdapter = new MyAdapter(Display_Password.this,credentialsArrayList);

        //   FirebaseRecyclerOptions<Credentials>options = new FirebaseRecyclerOptions.Builder<Credentials>().set

        FirebaseRecyclerOptions<Credentials> options =
                new FirebaseRecyclerOptions.Builder<Credentials>()
                        .setQuery(FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/").getReference().child("Users").child(Paper.book().read(prevelant.UserEmail)).child("Platform"), Credentials.class)
                        .build();

        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);



    }




}